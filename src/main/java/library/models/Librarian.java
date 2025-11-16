package library.models;

public class Librarian extends User {
    private int librarianId;

    public Librarian(int userId, String username, String password, String fullName, int librarianId) {
        super(userId, username, password, fullName);
        this.librarianId = librarianId;
    }

    public void sendNotification(Notification notification, Student user) {
        user.receiveNotification(notification);
    }
}