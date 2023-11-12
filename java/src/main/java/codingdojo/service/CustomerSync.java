package codingdojo.service;

import codingdojo.domain.ShoppingList;
import codingdojo.dao.CustomerDataLayer;
import codingdojo.domain.Customer;
import codingdojo.domain.CustomerType;
import codingdojo.dto.CustomerMatches;
import codingdojo.dto.ExternalCustomer;
import codingdojo.exception.ConflictException;
import com.spun.util.StringUtils;

import java.util.List;

/**
 * The type Customer sync.
 */
public class CustomerSync {

    /**
     * The constant EXISTING_CUSTOMER_MESSAGE.
     */
    public static final String EXISTING_CUSTOMER_MESSAGE = "Existing customer for externalCustomer ";
    private final CustomerDataAccess customerDataAccess;

    /**
     * Instantiates a new Customer sync.
     *
     * @param customerDataLayer the customer data layer
     */
    public CustomerSync(CustomerDataLayer customerDataLayer) {
        this(new CustomerDataAccess(customerDataLayer));
    }

    /**
     * Instantiates a new Customer sync.
     *
     * @param db the db
     */
    public CustomerSync(CustomerDataAccess db) {
        this.customerDataAccess = db;
    }

    /**
     * Sync existing db customer with the one provided externally.
     *
     * @param externalCustomer the external customer
     * @return the boolean
     */
    public boolean syncWithDataLayer(ExternalCustomer externalCustomer) {
        boolean created;
        String ecExternalId = externalCustomer.getExternalId();
        CustomerMatches customerMatches = customerDataAccess.loadCustomerMatchesByExternalId(ecExternalId);
        if (externalCustomer.isCompany()) {
            created = handleCompanyCustomer(externalCustomer, customerMatches);
        } else {
            created = handleIndividualCustomer(externalCustomer, customerMatches);
        }
        return created;
    }

    private void updateRelations(ExternalCustomer externalCustomer, Customer customer) {
        List<ShoppingList> consumerShoppingLists = externalCustomer.getShoppingLists();
        for (ShoppingList consumerShoppingList : consumerShoppingLists) {
            this.customerDataAccess.updateShoppingList(customer, consumerShoppingList);
        }
    }

    private Customer updateCustomer(Customer customer) {
        return this.customerDataAccess.updateCustomerRecord(customer);
    }

    private void updateDuplicate(ExternalCustomer externalCustomer, Customer duplicate) {
        if (duplicate == null) {
            duplicate = new Customer();
            duplicate.setExternalId(externalCustomer.getExternalId());
            duplicate.setMasterExternalId(externalCustomer.getExternalId());
        }

        duplicate.setName(externalCustomer.getName());

        if (duplicate.getInternalId() == null) {
            createCustomer(duplicate);
        } else {
            updateCustomer(duplicate);
        }
    }

    private void updatePreferredStore(ExternalCustomer externalCustomer, Customer customer) {
        customer.setPreferredStore(externalCustomer.getPreferredStore());
    }

    private Customer createCustomer(Customer customer) {
        return this.customerDataAccess.createCustomerRecord(customer);
    }

    private void updateContactInfo(ExternalCustomer externalCustomer, Customer customer) {
        customer.setAddress(externalCustomer.getPostalAddress());
    }

    private boolean handleCompanyCustomer(ExternalCustomer externalCustomer, CustomerMatches customerMatches) {
        boolean created = false;
        Customer dbCustomer;
        String ecExternalId = externalCustomer.getExternalId();
        String ecCompanyNumber = externalCustomer.getCompanyNumber();
        if (customerMatches.getCustomer() != null) {
            processCompanyCustomerFoundWithTheSameExternalId(ecExternalId, ecCompanyNumber, customerMatches);
            //get the customer from matches object to check if it is set to null when there is difference in company number
            dbCustomer = customerMatches.getCustomer();
        } else {
            if (!StringUtils.isEmpty(ecCompanyNumber)) {
                customerMatches = customerDataAccess.loadCustomerByCompanyNumber(ecCompanyNumber);
            }
            dbCustomer = customerMatches.getCustomer();
            if (dbCustomer != null) {
                processCompanyCustomerFoundWithTheSameCompanyNumber(dbCustomer, ecExternalId, ecCompanyNumber);
            }
        }
        if (dbCustomer == null) {
            dbCustomer = createCompanyCustomer(externalCustomer);
            created = true;
        } else {
            //in case the existing db customer has not set any or has invalid name, the name is being updated
            dbCustomer.setName(externalCustomer.getName());
            dbCustomer = updateCustomer(dbCustomer);
        }
        updateContactInfo(externalCustomer, dbCustomer);
        if (customerMatches.hasDuplicates()) {
            for (Customer duplicate : customerMatches.getDuplicates()) {
                updateDuplicate(externalCustomer, duplicate);
            }
        }
        updateRelations(externalCustomer, dbCustomer);
        updatePreferredStore(externalCustomer, dbCustomer);
        return created;
    }

    private boolean handleIndividualCustomer(ExternalCustomer externalCustomer, CustomerMatches customerMatches) {
        Customer dbCustomer = customerMatches.getCustomer();
        boolean created = false;
        if (dbCustomer == null) {
            dbCustomer = createIndividualCustomer(externalCustomer);
            created = true;
        } else {
            verifyCustomerIsIndividual(dbCustomer.getCustomerType(), externalCustomer.getExternalId());
            dbCustomer.setName(externalCustomer.getName());
            dbCustomer.setBonusPoints(externalCustomer.getBonusPoints());
            updateCustomer(dbCustomer);
        }
        updateContactInfo(externalCustomer, dbCustomer);
        //individual has no duplicates based on the existing functionality
        updateRelations(externalCustomer, dbCustomer);
        updatePreferredStore(externalCustomer, dbCustomer);
        return created;
    }

    private Customer createIndividualCustomer(ExternalCustomer externalCustomer) {
        Customer dbCustomer = getBasicCustomer(externalCustomer);
        dbCustomer.setBonusPoints(externalCustomer.getBonusPoints());
        dbCustomer.setCustomerType(CustomerType.PERSON);
        dbCustomer = createCustomer(dbCustomer);
        return dbCustomer;
    }

    private Customer createCompanyCustomer(ExternalCustomer externalCustomer) {
        Customer dbCustomer = getBasicCustomer(externalCustomer);
        dbCustomer.setCompanyNumber(externalCustomer.getCompanyNumber());
        dbCustomer.setCustomerType(CustomerType.COMPANY);
        dbCustomer = createCustomer(dbCustomer);
        return dbCustomer;
    }

    private Customer getBasicCustomer(ExternalCustomer externalCustomer) {
        Customer dbCustomer = new Customer();
        dbCustomer.setName(externalCustomer.getName());
        dbCustomer.setExternalId(externalCustomer.getExternalId());
        dbCustomer.setMasterExternalId(externalCustomer.getExternalId());
        return dbCustomer;
    }

    private void processCompanyCustomerFoundWithTheSameExternalId(String ecExternalId, String ecCompanyNumber, CustomerMatches customerMatches) {
        Customer dbCustomer = customerMatches.getCustomer();
        verifyCustomerIsCompany(dbCustomer.getCustomerType(), ecExternalId);

        Customer matchByMasterId = customerDataAccess.loadCustomerByMasterExternalId(ecExternalId);
        if (matchByMasterId != null) {
            customerMatches.addDuplicate(matchByMasterId);
        }
        String dbcCompanyNumber = dbCustomer.getCompanyNumber();
        //if the company number provided externally is different from the that of the existing
        // db persisted record, the existing record is modified and is treated as duplicate of
        // the new one which will be created based on the external customer object
        if (!ecCompanyNumber.equals(dbcCompanyNumber)) {
            dbCustomer.setMasterExternalId(null);
            customerMatches.addDuplicate(dbCustomer);
            customerMatches.setCustomer(null);
            customerMatches.setMatchTerm(null);
        }
    }

    private void processCompanyCustomerFoundWithTheSameCompanyNumber(Customer dbCustomer, String ecExternalId, String ecCompanyNumber) {
        verifyCustomerIsCompany(dbCustomer.getCustomerType(), ecExternalId);
        String dbExternalId = dbCustomer.getExternalId();
        if (dbExternalId != null && !ecExternalId.equals(dbExternalId)) {
            throw new ConflictException(EXISTING_CUSTOMER_MESSAGE + ecCompanyNumber + " doesn't match external id " + ecExternalId + " instead found " + dbExternalId);
        }
        dbCustomer.setExternalId(ecExternalId);
        dbCustomer.setMasterExternalId(ecExternalId);
    }

    private void verifyCustomerIsCompany(CustomerType type, String externalId) {
        if (!CustomerType.COMPANY.equals(type)) {
            throw new ConflictException(EXISTING_CUSTOMER_MESSAGE + externalId + " already exists and is not a company");
        }
    }

    private void verifyCustomerIsIndividual(CustomerType type, String externalId) {
        if (!CustomerType.PERSON.equals(type)) {
            throw new ConflictException(EXISTING_CUSTOMER_MESSAGE + externalId + " already exists and is not a person");
        }
    }
}
