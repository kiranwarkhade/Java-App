package app;

import java.util.*;

/*class User1 {
    private String username;
    private String password;
    private List<Video> uploadedVideos;

    public User1(String username, String password) {
        this.username = username;
        this.password = password;
        this.uploadedVideos = new ArrayList<>();
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
	 
	
}*/

class Video {
    private String title;
    private String url;
    private int likes;
    private List<String> comments;

    public Video(String title, String url) {
        this.title = title;
        this.url = url;
        this.likes = 0;
        this.comments = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public int getLikes() {
        return likes;
    }

    public List<String> getComments() {
        return comments;
    }

    public void like() {
        likes++;
        System.out.println("Video liked. Likes: " + likes);
    }

    public void addComment(String comment) {
        comments.add(comment);
        System.out.println("Comment added successfully.");
    }
}

class YouTubeApp {
    private Map<String, User> users = new HashMap<>();
    private List<Video> videos = new ArrayList<>();
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

    public void uploadVideo(String title, String url) {
        if (currentUser != null) {
            Video video = new Video(title, url);
            videos.add(video);
            currentUser.uploadVideo(video);
        } else {
            System.out.println("Please login to upload a video.");
        }
    }

    public void viewVideos() {
        if (!videos.isEmpty()) {
            System.out.println("Available Videos:");
            for (int i = 0; i < videos.size(); i++) {
                System.out.println((i + 1) + ". " + videos.get(i).getTitle() + " - " + videos.get(i).getUrl());
            }
        } else {
            System.out.println("No videos available.");
        }
    }

    public void likeVideo(int videoIndex) {
        if (currentUser != null && videoIndex > 0 && videoIndex <= videos.size()) {
            Video video = videos.get(videoIndex - 1);
            video.like();
        } else {
            System.out.println("Invalid video index or not logged in.");
        }
    }

    public void commentOnVideo(int videoIndex, String comment) {
        if (currentUser != null && videoIndex > 0 && videoIndex <= videos.size()) {
            Video video = videos.get(videoIndex - 1);
            video.addComment(comment);
        } else {
            System.out.println("Invalid video index or not logged in.");
        }
    }
}

public class YouTubeAppWithScanner {
    public static void main(String[] args) {
        YouTubeApp youTubeApp = new YouTubeApp();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Register\n2. Login\n3. Logout\n4. Upload Video\n5. View Videos" +
                    "\n6. Like Video\n7. Comment on Video\n8. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String regUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String regPassword = scanner.nextLine();
                    youTubeApp.registerUser(regUsername, regPassword);
                    break;
                case 2:
                    System.out.print("Enter username: ");
                    String loginUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String loginPassword = scanner.nextLine();
                    youTubeApp.loginUser(loginUsername, loginPassword);
                    break;
                case 3:
                    youTubeApp.logoutUser();
                    break;
                case 4:
                	
                    if (youTubeApp.currentUser != null) {
                        System.out.print("Enter video title: ");
                        String videoTitle = scanner.nextLine();
                        System.out.print("Enter video URL: ");
                        String videoUrl = scanner.nextLine();
                        youTubeApp.uploadVideo(videoTitle, videoUrl);
                    } else {
                        System.out.println("Please login to upload a video.");
                    }
                    break;
                case 5:
                    youTubeApp.viewVideos();
                    break;
                case 6:
                    if (youTubeApp.currentUser != null) {
                        System.out.print("Enter the number of the video to like: ");
                        int videoIndexToLike = scanner.nextInt();
                        youTubeApp.likeVideo(videoIndexToLike);
                    } else {
                        System.out.println("Please login to like a video.");
                    }
                    break;
                case 7:
                    if (youTubeApp.currentUser != null) {
                        System.out.print("Enter the number of the video to comment on: ");
                        int videoIndexToComment = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Enter your comment: ");
                        String comment = scanner.nextLine();
                        youTubeApp.commentOnVideo(videoIndexToComment, comment);
                    } else {
                        System.out.println("Please login to comment on a video.");
                    }
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
