package codingdojo.service;

import codingdojo.domain.ShoppingList;
import codingdojo.dao.CustomerDataLayer;
import codingdojo.domain.Customer;
import codingdojo.dto.CustomerMatches;

/**
 * The type Customer data access.
 */
public class CustomerDataAccess {

    /**
     * The constant EXTERNAL_ID_TERM.
     */
    public static final String EXTERNAL_ID_TERM = "ExternalId";
    /**
     * The constant COMPANY_NUMBER_TERM.
     */
    public static final String COMPANY_NUMBER_TERM = "CompanyNumber";
    private final CustomerDataLayer customerDataLayer;

    /**
     * Instantiates a new Customer data access.
     *
     * @param customerDataLayer the customer data layer
     */
    public CustomerDataAccess(CustomerDataLayer customerDataLayer) {
        this.customerDataLayer = customerDataLayer;
    }

    /**
     * Load customer matches by customer external id.
     *
     * @param externalId the external id
     * @return the customer matches
     */
    public CustomerMatches loadCustomerMatchesByExternalId(String externalId) {
        CustomerMatches matches = new CustomerMatches();
        Customer matchByExternalId = this.customerDataLayer.findByExternalId(externalId);
        if (matchByExternalId != null) {
            matches.setCustomer(matchByExternalId);
            //the match term seems not to be used in the current implementation
            // and so it could be removed from CustomerMatches class.
            // However, it is kept for future implementation
            matches.setMatchTerm(EXTERNAL_ID_TERM);
        }
        return matches;
    }

    /**
     * Load customer by master external id.
     *
     * @param externalId the external id
     * @return the customer
     */
    public Customer loadCustomerByMasterExternalId(String externalId) {
        return this.customerDataLayer.findByMasterExternalId(externalId);
    }

    /**
     * Load customer matches by company number.
     *
     * @param companyNumber the company number
     * @return the customer matches
     */
    public CustomerMatches loadCustomerByCompanyNumber(String companyNumber) {
        CustomerMatches matches = new CustomerMatches();
        Customer matchByCompanyNumber = this.customerDataLayer.findByCompanyNumber(companyNumber);
        if (matchByCompanyNumber != null) {
            matches.setCustomer(matchByCompanyNumber);
            matches.setMatchTerm(COMPANY_NUMBER_TERM);
        }
        return matches;
    }


    /**
     * Update customer record.
     *
     * @param customer the customer
     * @return the customer
     */
    public Customer updateCustomerRecord(Customer customer) {
        return customerDataLayer.updateCustomerRecord(customer);
    }

    /**
     * Create customer record.
     *
     * @param customer the customer
     * @return the customer
     */
    public Customer createCustomerRecord(Customer customer) {
        return customerDataLayer.createCustomerRecord(customer);
    }

    /**
     * Update shopping list.
     *
     * @param customer             the customer
     * @param consumerShoppingList the consumer shopping list
     */
    public void updateShoppingList(Customer customer, ShoppingList consumerShoppingList) {
        customer.addShoppingList(consumerShoppingList);
        consumerShoppingList.setCustomer(customer);
        customerDataLayer.updateShoppingList(consumerShoppingList);
        customerDataLayer.updateCustomerRecord(customer);
    }
}
