package codingdojo;

import codingdojo.domain.CustomerType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The type Customer.
 */
public class Customer {
    private String externalId;
    private String masterExternalId;
    private Address address;
    private String preferredStore;
    private List<ShoppingList> shoppingLists = new ArrayList<>();
    private String internalId;
    private String name;
    private CustomerType customerType;
    private String companyNumber;
    private int bonusPoints;

    /**
     * Gets master external id.
     *
     * @return the master external id
     */
    public String getMasterExternalId() {
        return masterExternalId;
    }

    /**
     * Sets master external id.
     *
     * @param masterExternalId the master external id
     */
    public void setMasterExternalId(String masterExternalId) {
        this.masterExternalId = masterExternalId;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Gets internal id.
     *
     * @return the internal id
     */
    public String getInternalId() {
        return internalId;
    }

    /**
     * Sets internal id.
     *
     * @param internalId the internal id
     */
    public void setInternalId(String internalId) {
        this.internalId = internalId;
    }

    /**
     * Gets preferred store.
     *
     * @return the preferred store
     */
    public String getPreferredStore() {
        return preferredStore;
    }

    /**
     * Sets preferred store.
     *
     * @param preferredStore the preferred store
     */
    public void setPreferredStore(String preferredStore) {
        this.preferredStore = preferredStore;
    }

    /**
     * Gets customer type.
     *
     * @return the customer type
     */
    public CustomerType getCustomerType() {
        return customerType;
    }

    /**
     * Sets customer type.
     *
     * @param customerType the customer type
     */
    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    /**
     * Gets shopping lists.
     *
     * @return the shopping lists
     */
    public List<ShoppingList> getShoppingLists() {
        return shoppingLists;
    }

    /**
     * Sets shopping lists.
     *
     * @param shoppingLists the shopping lists
     */
    public void setShoppingLists(List<ShoppingList> shoppingLists) {
        this.shoppingLists = shoppingLists;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets company number.
     *
     * @return the company number
     */
    public String getCompanyNumber() {
        return companyNumber;
    }

    /**
     * Sets company number.
     *
     * @param companyNumber the company number
     */
    public void setCompanyNumber(String companyNumber) {
        this.companyNumber = companyNumber;
    }

    /**
     * Gets external id.
     *
     * @return the external id
     */
    public String getExternalId() {
        return externalId;
    }

    /**
     * Sets external id.
     *
     * @param externalId the external id
     */
    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    /**
     * Add shopping list.
     *
     * @param consumerShoppingList the consumer shopping list
     */
    public void addShoppingList(ShoppingList consumerShoppingList) {
        ArrayList<ShoppingList> newList = new ArrayList<>(this.shoppingLists);
        newList.add(consumerShoppingList);
        this.setShoppingLists(newList);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(externalId, customer.externalId) &&
                Objects.equals(masterExternalId, customer.masterExternalId) &&
                Objects.equals(companyNumber, customer.companyNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(externalId, masterExternalId, companyNumber);
    }

    /**
     * Gets bonus points.
     *
     * @return the bonus points
     */
    public int getBonusPoints() {
        return bonusPoints;
    }

    /**
     * Sets bonus points.
     *
     * @param bonusPoints the bonus points
     */
    public void setBonusPoints(int bonusPoints) {
        this.bonusPoints = bonusPoints;
    }
}
