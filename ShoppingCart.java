import java.util.ArrayList;
import java.util.Scanner;

// Declare the name and price
class Item {
    private String name;
    private double price;

    // constructor for Item
    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // getters
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    // display item details as a string
    @Override
    public String toString() {
        return name + " - $" + price;
    }
}


class CartItem {
    private Item item;
    private int quantity;

    // constructor for CartItem
    public CartItem(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    // getters and setters
    public Item getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // total cost for the cart item
    public double getTotalCost() {
        return item.getPrice() * quantity;
    }

    // display cart item details as a string
    @Override
    public String toString() {
        return item.getName() + " - $" + item.getPrice() + " x " + quantity + " = $" + getTotalCost();
    }
}


public class ShoppingCart {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // create the stock
        ArrayList<Item> storeItems = new ArrayList<>();
        storeItems.add(new Item("Weezer CD", 9.99));
        storeItems.add(new Item("Lady Gaga CD", 14.99));
        storeItems.add(new Item("Spiritbox CD", 12.99));
        storeItems.add(new Item("Britney Spears CD", 8.99));
        storeItems.add(new Item("Panic at the Disco CD", 11.99));
        storeItems.add(new Item("Rob Zombie CD", 10.99));
        storeItems.add(new Item("The Killers CD", 13.99));
        storeItems.add(new Item("The Living Tombstone CD", 15.99));
        storeItems.add(new Item("Poppy CD", 12.49));
        storeItems.add(new Item("CG5 CD", 9.49));

        // create shopping cart
        ArrayList<CartItem> cart = new ArrayList<>();

        // loop for store options
        while (true) {
            System.out.println("\nWelcome to the Music Store");
            System.out.println("1. View CDs in store");
            System.out.println("2. Add CD to cart");
            System.out.println("3. Remove CD from cart");
            System.out.println("4. Update CD quantity");
            System.out.println("5. View cart");
            System.out.println("6. Clear cart");
            System.out.println("7. Exit store");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> {
                    // show the list of CDs available
                    System.out.println("\nCDs available:");
                    for (Item item : storeItems) {
                        System.out.println(item);
                    }
                }
                case 2 -> {
                    // add a CD to the cart
                    System.out.print("Enter CD name to add: ");
                    String itemName = scanner.nextLine();
                    Item selectedItem = null;
                    for (Item item : storeItems) {
                        if (item.getName().equalsIgnoreCase(itemName)) {
                            selectedItem = item;
                            break; // stop searching once the item is found
                        }
                    }
                    if (selectedItem != null) {
                        System.out.print("Enter quantity: ");
                        int quantity = scanner.nextInt();
                        if (quantity > 0) {
                            boolean itemFound = false;
                            for (CartItem cartItem : cart) {
                                if (cartItem.getItem().getName().equalsIgnoreCase(itemName)) {
                                    cartItem.setQuantity(cartItem.getQuantity() + quantity);
                                    itemFound = true;
                                    break; // stop checking after updating
                                }
                            }
                            if (!itemFound) {
                                cart.add(new CartItem(selectedItem, quantity));
                            }
                            System.out.println("CD added to cart");
                        } else {
                            System.out.println("invalid quantity");
                        }
                    } else {
                        System.out.println("CD not found in the store");
                    }
                }
                case 3 -> {
                    // remove a CD from the cart
                    System.out.print("Enter CD name to remove: ");
                    String itemName = scanner.nextLine();
                    boolean removed = cart.removeIf(cartItem -> cartItem.getItem().getName().equalsIgnoreCase(itemName));
                    if (removed) {
                        System.out.println("CD removed from cart");
                    } else {
                        System.out.println("CD not found in the cart");
                    }
                }
                case 4 -> {
                    // update the quantity of a CD
                    System.out.print("Enter CD name to update: ");
                    String itemName = scanner.nextLine();
                    CartItem foundItem = null;
                    for (CartItem cartItem : cart) {
                        if (cartItem.getItem().getName().equalsIgnoreCase(itemName)) {
                            foundItem = cartItem;
                            break; // stop searching once found
                        }
                    }
                    if (foundItem != null) {
                        System.out.print("Enter new quantity: ");
                        int newQuantity = scanner.nextInt();
                        if (newQuantity > 0) {
                            foundItem.setQuantity(newQuantity);
                            System.out.println("CD quantity updated");
                        } else {
                            System.out.println("invalid quantity");
                        }
                    } else {
                        System.out.println("CD not found in the cart");
                    }
                }
                case 5 -> {
                    // show the current cart contents
                    System.out.println("\nYour cart:");
                    if (cart.isEmpty()) {
                        System.out.println("cart is empty");
                    } else {
                        double totalCost = 0;
                        for (CartItem cartItem : cart) {
                            System.out.println(cartItem);
                            totalCost += cartItem.getTotalCost();
                        }
                        System.out.println("Total cost: $" + totalCost);
                    }
                }
                case 6 -> {
                    // clear the entire cart
                    cart.clear();
                    System.out.println("cart cleared");
                }
                case 7 -> {
                    // exit the store
                    System.out.println("thanks for shopping. goodbye");
                    scanner.close();
                    return;
                }
                default -> System.out.println("invalid option. please try again");
            }
        }
    }
}
