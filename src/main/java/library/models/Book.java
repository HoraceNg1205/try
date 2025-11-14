package library.models;

/**
 * Model class representing a Book.
 * Contains logic for managing the book's publication status.
 */
public class Book {

    // Using an enum for status is safer than strings
    public enum Status {
        PENDING,
        APPROVED,
        REJECTED
    }

    private String title;
    private Status status;

    public Book(String title) {
        this.title = title;
        this.status = Status.PENDING; // New books are pending by default
    }

    /**
     * Approves the book for publication.
     */
    public void approve() {
        this.status = Status.APPROVED;
    }

    /**
     * Rejects the book.
     */
    public void reject() {
        this.status = Status.REJECTED;
    }

    // --- Getters ---

    public String getTitle() {
        return title;
    }

    public Status getStatus() {
        return status;
    }
}