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

class SearchResult {
    private String title;
    private String url;

    public SearchResult(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }
}

class SearchEngineApp {
    private Map<String, User> users = new HashMap<>();
    private Map<String, List<SearchResult>> searchHistory = new HashMap<>();
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

    public List<SearchResult> searchWeb(String query) {
        List<SearchResult> results = new ArrayList<>();
        // Simulate searching the web (in a real application, you would use a search engine API)
        results.add(new SearchResult(" 1 ", "https://www.google.com/"));
        results.add(new SearchResult(" 2 ", "https://www.youtube.com/"));
        results.add(new SearchResult("Result 3", "https://www.amazon.com"));

        // Store the search history for the current user
        if (currentUser != null) {
            searchHistory.computeIfAbsent(currentUser.getUsername(), k -> new ArrayList<>()).addAll(results);
        }

        return results;
    }

    public void bookmarkResult(SearchResult result) {
        if (currentUser != null) {
            System.out.println("Result bookmarked: " + result.getTitle() + " - " + result.getUrl());
            // Simulate bookmarking the result (in a real application, you would store bookmarks in a database)
        } else {
            System.out.println("Please login to bookmark a result.");
        }
    }

    public void viewSearchHistory() {
        if (currentUser != null) {
            List<SearchResult> history = searchHistory.getOrDefault(currentUser.getUsername(), new ArrayList<>());
            System.out.println("Search History for User: " + currentUser.getUsername());
            for (SearchResult result : history) {
                System.out.println(result.getTitle() + " - " + result.getUrl());
            }
        } else {
            System.out.println("Please login to view search history.");
        }
    }
}

public class SearchEngineAppWithScanner {
    public static void main(String[] args) {
        SearchEngineApp searchEngineApp = new SearchEngineApp();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Register\n2. Login\n3. Logout\n4. Search Web\n5. Bookmark Result" +
                    "\n6. View Search History\n7. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String regUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String regPassword = scanner.nextLine();
                    searchEngineApp.registerUser(regUsername, regPassword);
                    break;
                case 2:
                    System.out.print("Enter username: ");
                    String loginUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String loginPassword = scanner.nextLine();
                    searchEngineApp.loginUser(loginUsername, loginPassword);
                    break;
                case 3:
                    searchEngineApp.logoutUser();
                    break;
                case 4:
                    if (searchEngineApp.currentUser != null) {
                        System.out.print("Enter search query: ");
                        String query = scanner.nextLine();
                        List<SearchResult> results = searchEngineApp.searchWeb(query);
                        System.out.println("Search Results:");
                        for (int i = 0; i < results.size(); i++) {
                            System.out.println((i + 1) + ". " + results.get(i).getTitle() + " - " + results.get(i).getUrl());
                        }
                    } else {
                        System.out.println("Please login to search the web.");
                    }
                    break;
                case 5:
                    if (searchEngineApp.currentUser != null) {
                        System.out.print("Enter the number of the result to bookmark: ");
                        int resultNumber = scanner.nextInt();
                        if (resultNumber > 0 && resultNumber <= 3) {
                            SearchResult result = new SearchResult("Result " + resultNumber, "https://www.result" + resultNumber + ".com");
                            searchEngineApp.bookmarkResult(result);
                        } else {
                            System.out.println("Invalid result number.");
                        }
                    } else {
                        System.out.println("Please login to bookmark a result.");
                    }
                    break;
                case 6:
                    searchEngineApp.viewSearchHistory();
                    break;
                case 7:
                    System.out.println("Exiting the application.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

