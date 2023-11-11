package codingdojo;

import codingdojo.domain.ShoppingList;

import java.util.List;

/**
 * The type Shopping list printer.
 */
public class ShoppingListPrinter {
    /**
     * Print shopping lists string.
     *
     * @param shoppingLists the shopping lists
     * @param indent        the indent
     * @return the string
     */
    public static String printShoppingLists(List<ShoppingList> shoppingLists, String indent) {
        if (shoppingLists.isEmpty()) {
            return "[]";
        }
        if (shoppingLists.size() == 1) {
            ShoppingList shoppingList = shoppingLists.get(0);
            return "[" + printShoppingList(shoppingList) + "]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (ShoppingList shoppingList : shoppingLists) {
            sb.append("\n    " + indent);
            sb.append(printShoppingList(shoppingList));

        }
        sb.append("\n" + indent + "]");
        return sb.toString();
    }

    private static String printShoppingList(ShoppingList shoppingList) {
        return shoppingList.getProducts().toString();
    }
}
