package codingdojo.dto;

import codingdojo.domain.Customer;

import java.util.ArrayList;
import java.util.Collection;

/**
 * The type Customer matches.
 */
public class CustomerMatches {
    private final Collection<Customer> duplicates = new ArrayList<>();
    private String matchTerm;
    private Customer customer;

    /**
     * Gets customer.
     *
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Sets customer.
     *
     * @param customer the customer
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Has duplicates boolean.
     *
     * @return the boolean
     */
    public boolean hasDuplicates() {
        return !duplicates.isEmpty();
    }

    /**
     * Add duplicate.
     *
     * @param duplicate the duplicate
     */
    public void addDuplicate(Customer duplicate) {
        duplicates.add(duplicate);
    }

    /**
     * Gets duplicates.
     *
     * @return the duplicates
     */
    public Collection<Customer> getDuplicates() {
        return duplicates;
    }

    /**
     * Gets match term.
     *
     * @return the match term
     */
    public String getMatchTerm() {
        return matchTerm;
    }

    /**
     * Sets match term.
     *
     * @param matchTerm the match term
     */
    public void setMatchTerm(String matchTerm) {
        this.matchTerm = matchTerm;
    }
}
