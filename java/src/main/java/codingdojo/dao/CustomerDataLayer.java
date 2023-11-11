package codingdojo.dao;

import codingdojo.domain.ShoppingList;
import codingdojo.domain.Customer;

/**
 * The interface Customer data layer.
 */
public interface CustomerDataLayer {

    /**
     * Update customer record.
     *
     * @param customer the customer
     * @return the customer
     */
    Customer updateCustomerRecord(Customer customer);

    /**
     * Create customer record.
     *
     * @param customer the customer
     * @return the customer
     */
    Customer createCustomerRecord(Customer customer);

    /**
     * Update shopping list.
     *
     * @param consumerShoppingList the consumer shopping list
     */
    void updateShoppingList(ShoppingList consumerShoppingList);

    /**
     * Find customer by external id .
     *
     * @param externalId the external id
     * @return the customer
     */
    Customer findByExternalId(String externalId);

    /**
     * Find customer by master external id.
     *
     * @param externalId the external id
     * @return the customer
     */
    Customer findByMasterExternalId(String externalId);

    /**
     * Find customer by company number.
     *
     * @param companyNumber the company number
     * @return the customer
     */
    Customer findByCompanyNumber(String companyNumber);
}
