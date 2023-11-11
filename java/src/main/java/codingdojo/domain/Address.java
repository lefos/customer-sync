package codingdojo.domain;

/**
 * The type Address.
 */
public class Address {
    private String street;
    private String city;
    private String postalCode;

    /**
     * Instantiates a new Address.
     *
     * @param street     the street
     * @param city       the city
     * @param postalCode the postal code
     */
    public Address(String street, String city, String postalCode) {
        setStreet(street);
        setCity(city);
        setPostalCode(postalCode);
    }

    /**
     * Gets street.
     *
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets street.
     *
     * @param street the street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Gets postal code.
     *
     * @return the postal code
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Sets postal code.
     *
     * @param postalCode the postal code
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Gets city.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets city.
     *
     * @param city the city
     */
    public void setCity(String city) {
        this.city = city;
    }
}
