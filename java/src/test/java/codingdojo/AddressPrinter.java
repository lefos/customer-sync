package codingdojo;

/**
 * The type Address printer.
 */
public class AddressPrinter {
    /**
     * Print address string.
     *
     * @param address the address
     * @return the string
     */
    public static String printAddress(Address address) {
        if (address == null) {
            return "'null'";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\'");
        sb.append(address.getStreet());
        sb.append(", ");
        sb.append(address.getPostalCode());
        sb.append(" ");
        sb.append(address.getCity());
        sb.append("\'");
        return sb.toString();
    }
}
