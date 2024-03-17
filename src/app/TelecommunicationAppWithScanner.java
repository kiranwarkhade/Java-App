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

class PhoneNumber {
    private String phoneNumber;
    private boolean isActive;

    public PhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        this.isActive = true;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean isActive() {
        return isActive;
    }

    public void deactivate() {
        isActive = false;
    }
}

class Call {
    private static int callIdCounter = 1;

    private int callId;
    private String callerPhoneNumber;
    private String receiverPhoneNumber;
    private int durationMinutes;

    public Call(String callerPhoneNumber, String receiverPhoneNumber, int durationMinutes) {
        this.callId = callIdCounter++;
        this.callerPhoneNumber = callerPhoneNumber;
        this.receiverPhoneNumber = receiverPhoneNumber;
        this.durationMinutes = durationMinutes;
    }

    public int getCallId() {
        return callId;
    }

    public String getCallerPhoneNumber() {
        return callerPhoneNumber;
    }

    public String getReceiverPhoneNumber() {
        return receiverPhoneNumber;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }
}

class TelecommunicationApp {
    private Map<String, User> users = new HashMap<>();
    private Map<String, PhoneNumber> phoneNumbers = new HashMap<>();
    private List<Call> callHistory = new ArrayList<>();
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

    public void addPhoneNumber(String phoneNumber) {
        if (currentUser != null) {
            phoneNumbers.put(phoneNumber, new PhoneNumber(phoneNumber));
            System.out.println("Phone number added successfully.");
        } else {
            System.out.println("Please login to add a phone number.");
        }
    }

    public void deactivatePhoneNumber(String phoneNumber) {
        PhoneNumber number = phoneNumbers.get(phoneNumber);
        if (number != null && currentUser != null && currentUser.getUsername().equals(number.getPhoneNumber())) {
            number.deactivate();
            System.out.println("Phone number deactivated successfully.");
        } else {
            System.out.println("Invalid phone number or you don't have permission to deactivate this number.");
        }
    }

    public void makeCall(String callerPhoneNumber, String receiverPhoneNumber, int durationMinutes) {
        if (currentUser != null && phoneNumbers.containsKey(callerPhoneNumber) && phoneNumbers.containsKey(receiverPhoneNumber)) {
            Call call = new Call(callerPhoneNumber, receiverPhoneNumber, durationMinutes);
            callHistory.add(call);
            System.out.println("Call made successfully. Call ID: " + call.getCallId());
        } else {
            System.out.println("Invalid phone numbers or you don't have permission to make this call.");
        }
    }

    public void viewCallHistory() {
        if (currentUser != null) {
            System.out.println("Call History for User: " + currentUser.getUsername());
            for (Call call : callHistory) {
                if (call.getCallerPhoneNumber().equals(currentUser.getUsername()) || call.getReceiverPhoneNumber().equals(currentUser.getUsername())) {
                    System.out.println("Call ID: " + call.getCallId());
                    System.out.println("Caller: " + call.getCallerPhoneNumber());
                    System.out.println("Receiver: " + call.getReceiverPhoneNumber());
                    System.out.println("Duration (minutes): " + call.getDurationMinutes());
                    System.out.println("---------------");
                }
            }
        } else {
            System.out.println("Please login to view call history.");
        }
    }
}

public class TelecommunicationAppWithScanner {
    public static void main(String[] args) {
        TelecommunicationApp telecommunicationApp = new TelecommunicationApp();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Register\n2. Login\n3. Logout\n4. Add Phone Number\n5. Deactivate Phone Number" +
                    "\n6. Make Call\n7. View Call History\n8. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String regUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String regPassword = scanner.nextLine();
                    telecommunicationApp.registerUser(regUsername, regPassword);
                    break;
                case 2:
                    System.out.print("Enter username: ");
                    String loginUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String loginPassword = scanner.nextLine();
                    telecommunicationApp.loginUser(loginUsername, loginPassword);
                    break;
                case 3:
                    telecommunicationApp.logoutUser();
                    break;
                case 4:
                    if (telecommunicationApp.currentUser != null) {
                        System.out.print("Enter phone number to add: ");
                        String addPhoneNumber = scanner.nextLine();
                        telecommunicationApp.addPhoneNumber(addPhoneNumber);
                    } else {
                        System.out.println("Please login to add a phone number.");
                    }
                    break;
                case 5:
                    if (telecommunicationApp.currentUser != null) {
                        System.out.print("Enter phone number to deactivate: ");
                        String deactivatePhoneNumber = scanner.nextLine();
                        telecommunicationApp.deactivatePhoneNumber(deactivatePhoneNumber);
                    } else {
                        System.out.println("Please login to deactivate a phone number.");
                    }
                    break;
                case 6:
                    if (telecommunicationApp.currentUser != null) {
                        System.out.print("Enter caller's phone number: ");
                        String callerPhoneNumber = scanner.nextLine();
                        System.out.print("Enter receiver's phone number: ");
                        String receiverPhoneNumber = scanner.nextLine();
                        System.out.print("Enter call duration (minutes): ");
                        int durationMinutes = scanner.nextInt();
                        telecommunicationApp.makeCall(callerPhoneNumber, receiverPhoneNumber, durationMinutes);
                    } else {
                        System.out.println("Please login to make a call.");
                    }
                    break;
                case 7:
                    telecommunicationApp.viewCallHistory();
                    break;
                case 8:
                    System.out.println("Exiting the application.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

