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

class Policy {
    private String policyNumber;
    private String policyType;
    private double premiumAmount;
    private boolean isActive;

    public Policy(String policyNumber, String policyType, double premiumAmount) {
        this.policyNumber = policyNumber;
        this.policyType = policyType;
        this.premiumAmount = premiumAmount;
        this.isActive = true;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public String getPolicyType() {
        return policyType;
    }

    public double getPremiumAmount() {
        return premiumAmount;
    }

    public boolean isActive() {
        return isActive;
    }

    public void cancelPolicy() {
        isActive = false;
    }
}

class Claim {
    private static int claimIdCounter = 1;

    private int claimId;
    private String policyNumber;
    private double claimAmount;
    private String description;

    public Claim(String policyNumber, double claimAmount, String description) {
        this.claimId = claimIdCounter++;
        this.policyNumber = policyNumber;
        this.claimAmount = claimAmount;
        this.description = description;
    }

    public int getClaimId() {
        return claimId;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public double getClaimAmount() {
        return claimAmount;
    }

    public String getDescription() {
        return description;
    }
}

class InsuranceApp {
    private Map<String, User> users = new HashMap<>();
    private Map<String, Policy> policies = new HashMap<>();
    private List<Claim> claims = new ArrayList<>();
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

    public void createPolicy(String policyNumber, String policyType, double premiumAmount) {
        if (currentUser != null) {
            Policy policy = new Policy(policyNumber, policyType, premiumAmount);
            policies.put(policyNumber, policy);
            System.out.println("Policy created successfully.");
        } else {
            System.out.println("Please login to create a policy.");
        }
    }

    public void cancelPolicy(String policyNumber) {
        Policy policy = policies.get(policyNumber);
        if (policy != null && currentUser != null && currentUser.getUsername().equals(policy.getPolicyNumber())) {
            policy.cancelPolicy();
            System.out.println("Policy canceled successfully.");
        } else {
            System.out.println("Invalid policy number or you don't have permission to cancel this policy.");
        }
    }

    public void submitClaim(String policyNumber, double claimAmount, String description) {
        Policy policy = policies.get(policyNumber);
        if (policy != null && policy.isActive()) {
            Claim claim = new Claim(policyNumber, claimAmount, description);
            claims.add(claim);
            System.out.println("Claim submitted successfully. Claim ID: " + claim.getClaimId());
        } else {
            System.out.println("Invalid policy number or the policy is not active.");
        }
    }

    public void viewClaims() {
        if (currentUser != null) {
            System.out.println("Claims for User: " + currentUser.getUsername());
            for (Claim claim : claims) {
                if (claim.getPolicyNumber().equals(currentUser.getUsername())) {
                    System.out.println("Claim ID: " + claim.getClaimId());
                    System.out.println("Policy Number: " + claim.getPolicyNumber());
                    System.out.println("Claim Amount: $" + claim.getClaimAmount());
                    System.out.println("Description: " + claim.getDescription());
                    System.out.println("---------------");
                }
            }
        } else {
            System.out.println("Please login to view claims.");
        }
    }
}

public class InsuranceAppWithScanner {
    public static void main(String[] args) {
        InsuranceApp insuranceApp = new InsuranceApp();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Register\n2. Login\n3. Logout\n4. Create Policy\n5. Cancel Policy" +
                    "\n6. Submit Claim\n7. View Claims\n8. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String regUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String regPassword = scanner.nextLine();
                    insuranceApp.registerUser(regUsername, regPassword);
                    break;
                case 2:
                    System.out.print("Enter username: ");
                    String loginUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String loginPassword = scanner.nextLine();
                    insuranceApp.loginUser(loginUsername, loginPassword);
                    break;
                case 3:
                    insuranceApp.logoutUser();
                    break;
                case 4:
                    if (insuranceApp.currentUser != null) {
                        System.out.print("Enter policy number: ");
                        String policyNumber = scanner.nextLine();
                        System.out.print("Enter policy type: ");
                        String policyType = scanner.nextLine();
                        System.out.print("Enter premium amount: ");
                        double premiumAmount = scanner.nextDouble();
                        insuranceApp.createPolicy(policyNumber, policyType, premiumAmount);
                    } else {
                        System.out.println("Please login to create a policy.");
                    }
                    break;
                case 5:
                    if (insuranceApp.currentUser != null) {
                        System.out.print("Enter policy number to cancel: ");
                        String cancelPolicyNumber = scanner.nextLine();
                        insuranceApp.cancelPolicy(cancelPolicyNumber);
                    } else {
                        System.out.println("Please login to cancel a policy.");
                    }
                    break;
                case 6:
                    if (insuranceApp.currentUser != null) {
                        System.out.print("Enter policy number for the claim: ");
                        String claimPolicyNumber = scanner.nextLine();
                        System.out.print("Enter claim amount: ");
                        double claimAmount = scanner.nextDouble();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Enter claim description: ");
                        String claimDescription = scanner.nextLine();
                        insuranceApp.submitClaim(claimPolicyNumber, claimAmount, claimDescription);
                    } else {
                        System.out.println("Please login to submit a claim.");
                    }
                    break;
                case 7:
                    insuranceApp.viewClaims();
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

