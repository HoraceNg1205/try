package library.models;

/**
 * Model class representing a User.
 * Contains logic for managing the user's account status.
 */
public class User {

    private String username;
    private boolean isActive;

    public User(String username) {
        this.username = username;
        this.isActive = true; // Users are active by default
    }

    /**
     * Deactivates the user's account.
     */
    public void deactivate() {
        this.isActive = false;
    }

    /**
     * Activates the user's account.
     */
    public void activate() {
        this.isActive = true;
    }

    // --- Getters ---

    public String getUsername() {
        return username;
    }

    public boolean isActive() {
        return isActive;
    }
}
