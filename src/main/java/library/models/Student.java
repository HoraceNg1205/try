package library.models;

import java.util.ArrayList;
import java.util.List;

public class Student extends User {
    private int studentId;
    private List<BorrowRecord> borrowRecords;
    private List<Notification> notifications;

    public Student(int userId, String username, String password, String fullName, int studentId) {
        super(userId, username, password, fullName);
        this.studentId = studentId;
        this.borrowRecords = new ArrayList<>();
        this.notifications = new ArrayList<>();
    }

    public void borrowBook(Book book, int durationDays) {
        // placeholder for borrowing logic
    }

    public void receiveNotification(Notification notification) {
        notifications.add(notification);
    }
}