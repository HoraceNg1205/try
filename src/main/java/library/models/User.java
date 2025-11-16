package library.models;

/**
 * Model class representing a User.
 * Contains logic for managing the user's account status.
 */
public abstract class User {
    protected int userId;
    protected String username;
    protected String password;
    protected String fullName;

    public User(int userId, String username, String password, String fullName) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public int getUserId() { return userId; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    @Override
    public String toString() {
        return "User: " + username;
    }
}