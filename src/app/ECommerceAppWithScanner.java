package app;

import java.util.*;

/*class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}*/

class Product {
    private String id;
    private String name;
    private double price;
    private int quantity;

    public Product(String id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void reduceQuantity(int amount) {
        if (amount > 0 && amount <= quantity) {
            quantity -= amount;
        }
    }
}

class ShoppingCart {
    private Map<String, Integer> items = new HashMap<>();

    public void addItem(String productId, int quantity) {
        items.put(productId, items.getOrDefault(productId, 0) + quantity);
    }

    public Map<String, Integer> getItems() {
        return Collections.unmodifiableMap(items);
    }

    public void clear() {
        items.clear();
    }
}

class Order {
    private static int orderIdCounter = 1;

    private int orderId;
    private String userId;
    private Map<String, Integer> items;
    private double totalAmount;

    public Order(String userId, Map<String, Integer> items, double totalAmount) {
        this.orderId = orderIdCounter++;
        this.userId = userId;
        this.items = new HashMap<>(items);
        this.totalAmount = totalAmount;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getUserId() {
        return userId;
    }

    public Map<String, Integer> getItems() {
        return Collections.unmodifiableMap(items);
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}

class ECommerceApp {
    private Map<String, User> users = new HashMap<>();
    private Map<String, Product> products = new HashMap<>();
    private List<Order> orders = new ArrayList<>();
    private Map<String, ShoppingCart> userCarts = new HashMap<>();
    User currentUser;

    public void registerUser(String username, String password) {
        users.put(username, new User(username, password));
        System.out.println("User registered successfully.");
    }

    public boolean loginUser(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            currentUser = user;
            System.out.println("Login successful. Welcome, " + username + "!");
            return true;
        } else {
            System.out.println("Invalid username or password. Please try again.");
            return false;
        }
    }

    public void logoutUser() {
        currentUser = null;
        System.out.println("Logout successful.");
    }

    public void addProduct(Product product) {
        products.put(product.getId(), product);
        System.out.println("Product added successfully.");
    }

    public void displayProducts() {
        System.out.println("Available Products:");
        for (Product product : products.values()) {
            System.out.println(product.getId() + ": " + product.getName() + " - $" + product.getPrice());
        }
    }

    public void addToCart(String productId, int quantity) {
        if (currentUser != null && products.containsKey(productId) && quantity > 0) {
            userCarts.computeIfAbsent(currentUser.getUsername(), k -> new ShoppingCart()).addItem(productId, quantity);
            System.out.println("Item added to the cart.");
        } else {
            System.out.println("Invalid product or quantity. Please make sure you are logged in.");
        }
    }

    public void viewCart() {
        ShoppingCart cart = userCarts.get(currentUser.getUsername());
        if (cart != null) {
            System.out.println("Shopping Cart:");
            for (Map.Entry<String, Integer> entry : cart.getItems().entrySet()) {
                Product product = products.get(entry.getKey());
                System.out.println(product.getName() + " - Quantity: " + entry.getValue());
            }
        } else {
            System.out.println("Shopping cart is empty.");
        }
    }

    public void checkout() {
        ShoppingCart cart = userCarts.get(currentUser.getUsername());
        if (cart != null && !cart.getItems().isEmpty()) {
            double totalAmount = 0;
            Map<String, Integer> items = new HashMap<>(cart.getItems());

            for (Map.Entry<String, Integer> entry : items.entrySet()) {
                Product product = products.get(entry.getKey());
                totalAmount += product.getPrice() * entry.getValue();
                product.reduceQuantity(entry.getValue());
            }

            Order order = new Order(currentUser.getUsername(), items, totalAmount);
            orders.add(order);
            userCarts.get(currentUser.getUsername()).clear();

            System.out.println("Order placed successfully. Order ID: " + order.getOrderId());
        } else {
            System.out.println("Shopping cart is empty. Add items to the cart before checking out.");
        }
    }

    public void viewOrders() {
        System.out.println("Order History for User: " + currentUser.getUsername());
        for (Order order : orders) {
            if (order.getUserId().equals(currentUser.getUsername())) {
                System.out.println("Order ID: " + order.getOrderId());
                for (Map.Entry<String, Integer> entry : order.getItems().entrySet()) {
                    Product product = products.get(entry.getKey());
                    System.out.println(product.getName() + " - Quantity: " + entry.getValue());
                }
                System.out.println("Total Amount: $" + order.getTotalAmount());
                System.out.println("---------------");
            }
        }
    }
}

public class ECommerceAppWithScanner {
    public static void main(String[] args) {
        ECommerceApp eCommerceApp = new ECommerceApp();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Register\n2. Login\n3. Logout\n4. Add Product\n5. Display Products" +
                    "\n6. Add to Cart\n7. View Cart\n8. Checkout\n9. View Orders\n10. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String regUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String regPassword = scanner.nextLine();
                    eCommerceApp.registerUser(regUsername, regPassword);
                    break;
                case 2:
                    System.out.print("Enter username: ");
                    String loginUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String loginPassword = scanner.nextLine();
                    eCommerceApp.loginUser(loginUsername, loginPassword);
                    break;
                case 3:
                    eCommerceApp.logoutUser();
                    break;
                case 4:
                    System.out.print("Enter product ID: ");
                    String productId = scanner.nextLine();
                    System.out.print("Enter product name: ");
                    String productName = scanner.nextLine();
                    System.out.print("Enter product price: ");
                    double productPrice = scanner.nextDouble();
                    System.out.print("Enter product quantity: ");
                    int productQuantity = scanner.nextInt();
                    eCommerceApp.addProduct(new Product(productId, productName, productPrice, productQuantity));
                    break;
                case 5:
                    eCommerceApp.displayProducts();
                    break;
                case 6:
                    if (eCommerceApp.currentUser != null) {
                        System.out.print("Enter product ID: ");
                        String cartProductId = scanner.nextLine();
                        System.out.print("Enter quantity: ");
                        int cartQuantity = scanner.nextInt();
                        eCommerceApp.addToCart(cartProductId, cartQuantity);
                    } else {
                        System.out.println("Please login to add items to the cart.");
                    }
                    break;
                case 7:
                    if (eCommerceApp.currentUser != null) {
                        eCommerceApp.viewCart();
                    } else {
                        System.out.println("Please login to view the cart.");
                    }
                    break;
                case 8:
                    if (eCommerceApp.currentUser != null) {
                        eCommerceApp.checkout();
                    } else {
                        System.out.println("Please login to checkout.");
                    }
                    break;
                case 9:
                    if (eCommerceApp.currentUser != null) {
                        eCommerceApp.viewOrders();
                    } else {
                        System.out.println("Please login to view orders.");
                    }
                    break;
                case 10:
                    System.out.println("Exiting the application.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
