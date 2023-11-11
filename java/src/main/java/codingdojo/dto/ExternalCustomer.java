package codingdojo.dto;

import codingdojo.domain.Address;
import codingdojo.domain.ShoppingList;

import java.util.List;

/**
 * The type External customer.
 */
public class ExternalCustomer {
    private Address address;
    private String name;
    private String preferredStore;
    private List<ShoppingList> shoppingLists;
    private String externalId;
    private String companyNumber;
    private int bonusPoints;

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
     * Is company boolean.
     *
     * @return the boolean
     */
    public boolean isCompany() {
        return companyNumber != null;
    }

    /**
     * Gets postal address.
     *
     * @return the postal address
     */
    public Address getPostalAddress() {
        return address;
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
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(Address address) {
        this.address = address;
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
