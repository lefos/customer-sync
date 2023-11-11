package codingdojo;

import java.util.Arrays;
import java.util.List;

/**
 * The type Shopping list.
 */
public class ShoppingList {
    private final List<String> products;

    private Customer customer;

    /**
     * Instantiates a new Shopping list.
     *
     * @param products the products
     */
    public ShoppingList(String... products) {
        this.products = Arrays.asList(products);
    }

    /**
     * Gets products.
     *
     * @return the products
     */
    public List<String> getProducts() {
        return products;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
