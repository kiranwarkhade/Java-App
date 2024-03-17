package app;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class User {
    private String username;
    private String password;
    private List<Video> uploadedVideos;

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

    public List<Video> getUploadedVideos() {
        return uploadedVideos;
    }

	
	  public void uploadVideo(Video video)
	  { 
		  uploadedVideos.add(video);
	  System.out.println("Video uploaded successfully."); }
	 


}

public class RegistrationLoginApp {
    private static final String USERS_FILE = "users.txt";
    private static final Map<String, User> registeredUsers = new HashMap<>();

    public static void main(String[] args) {
        loadUsers();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Register\n2. Login\n3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    registerUser(scanner);
                    break;
                case 2:
                    loginUser(scanner);
                    break;
                case 3:
                    saveUsers();
                    System.out.println("Exiting the application.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void registerUser(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        if (registeredUsers.containsKey(username)) {
            System.out.println("Username already exists. Choose another username.");
            return;
        }

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User newUser = new User(username, password);
        registeredUsers.put(username, newUser);
        System.out.println("Registration successful. You can now login.");
    }

    private static void loginUser(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (registeredUsers.containsKey(username) && registeredUsers.get(username).getPassword().equals(password)) {
            System.out.println("Login successful. Welcome, " + username + "!");
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }
    }

    private static void loadUsers() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USERS_FILE))) {
            registeredUsers.putAll((Map<String, User>) ois.readObject());
        } catch (FileNotFoundException e) {
            System.out.println("No existing users file found. Starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void saveUsers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USERS_FILE))) {
            oos.writeObject(registeredUsers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
