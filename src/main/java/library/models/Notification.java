package library.models;

public class Notification {
    private int notificationId;
    private String message;
    private String dateTime;
    private String status; // Unread, Cleared

    public Notification(int notificationId, String message, String dateTime, String status) {
        this.notificationId = notificationId;
        this.message = message;
        this.dateTime = dateTime;
        this.status = status;
    }

    public void markCleared() {
        this.status = "Cleared";
    }

    @Override
    public String toString() {
        return "Notification: " + message;
    }
}