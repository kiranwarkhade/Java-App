package DesignPattern;

import java.util.HashMap;
import java.util.Map;

import java.util.HashMap;
import java.util.Map;

// Prototype interface
interface AccountPrototype {
    AccountPrototype clone();
    void deposit(double amount);
    void withdraw(double amount);
    void printDetails();
}

// Concrete prototype
class SavingsAccount implements AccountPrototype {
    private String accountHolder;
    private double balance;

    public SavingsAccount(String accountHolder, double balance) {
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    @Override
    public AccountPrototype clone() {
        return new SavingsAccount(this.accountHolder, this.balance);
    }

    @Override
    public void deposit(double amount) {
        this.balance += amount;
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= balance) {
            this.balance -= amount;
        } else {
            System.out.println("Insufficient funds");
        }
    }

    @Override
    public void printDetails() {
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Balance: $" + balance);
    }
}

// Account manager
class AccountManager {
    private Map<String, AccountPrototype> accountPrototypes = new HashMap<>();

    public void addPrototype(String accountType, AccountPrototype prototype) {
        accountPrototypes.put(accountType, prototype);
    }

    public AccountPrototype createAccount(String accountType, String accountHolder, double initialBalance) {
        if (accountPrototypes.containsKey(accountType)) {
            AccountPrototype prototype = accountPrototypes.get(accountType);
            AccountPrototype newAccount = prototype.clone();
            ((SavingsAccount) newAccount).deposit(initialBalance);
            ((SavingsAccount) newAccount).printDetails();
            return newAccount;
        } else {
            System.out.println("Account type '" + accountType + "' not supported.");
            return null;
        }
    }
}

public class BankingAppExample {
    public static void main(String[] args) {
        // Create account manager
        AccountManager accountManager = new AccountManager();

        // Create and add prototypes to the manager
        SavingsAccount savingsPrototype = new SavingsAccount("John Doe", 0);
        accountManager.addPrototype("Savings", savingsPrototype);

        // Create new accounts using the manager
        AccountPrototype newSavingsAccount = accountManager.createAccount("Savings", "Alice Smith", 1000);
        if (newSavingsAccount != null) {
            ((SavingsAccount) newSavingsAccount).withdraw(500);
            ((SavingsAccount) newSavingsAccount).printDetails();
        }
    }
}

























/*
 * // Prototype interface interface Prototype { Prototype clone(); }
 * 
 * // Concrete prototype class ConcretePrototype implements Prototype { private
 * String property;
 * 
 * public ConcretePrototype(String property) { this.property = property; }
 * 
 * @Override public Prototype clone() { return new
 * ConcretePrototype(this.property); }
 * 
 * public String getProperty() { return property; }
 * 
 * public void setProperty(String property) { this.property = property; } }
 * 
 * // Prototype manager class PrototypeManager { private Map<String, Prototype>
 * prototypes = new HashMap<>();
 * 
 * public void addPrototype(String key, Prototype prototype) {
 * prototypes.put(key, prototype); }
 * 
 * public Prototype getPrototype(String key) { if (prototypes.containsKey(key))
 * { return prototypes.get(key).clone(); } else {
 * System.out.println("Prototype with key '" + key + "' does not exist.");
 * return null; } } }
 * 
 * public class PrototypePatternExample { public static void main(String[] args)
 * { // Create prototype manager PrototypeManager prototypeManager = new
 * PrototypeManager();
 * 
 * // Create and add prototypes to the manager ConcretePrototype prototype1 =
 * new ConcretePrototype("Prototype 1"); prototypeManager.addPrototype("key1",
 * prototype1);
 * 
 * ConcretePrototype prototype2 = new ConcretePrototype("Prototype 2");
 * prototypeManager.addPrototype("key2", prototype2);
 * 
 * // Clone prototypes using the manager Prototype clonedPrototype1 =
 * prototypeManager.getPrototype("key1"); if (clonedPrototype1 != null) {
 * System.out.println("Cloned Prototype 1: " + ((ConcretePrototype)
 * clonedPrototype1).getProperty()); }
 * 
 * Prototype clonedPrototype2 = prototypeManager.getPrototype("key2"); if
 * (clonedPrototype2 != null) { System.out.println("Cloned Prototype 2: " +
 * ((ConcretePrototype) clonedPrototype2).getProperty()); } } }
 * 
 */

