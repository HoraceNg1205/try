package library.models;

import java.util.ArrayList;

/**
 * Model class representing a Book.
 * Contains logic for managing the book's publication status.
 */
public class Book {
    private int bookId;
    private String title;
    private String abstractText;
    private String publishDate;
    private String status; // Available, Borrowed, Reserved, Pending, Approved
    private Author author;
    private ArrayList<BorrowRecord> borrowRecords;

    public Book(int bookId, String title, String abstractText, String publishDate, String status) {
        this.bookId = bookId;
        this.title = title;
        this.abstractText = abstractText;
        this.publishDate = publishDate;
        this.status = status;
        this.borrowRecords = new ArrayList<>();
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book: " + title + " (" + status + ")";
    }
    public String getTitle() {
        return title;
    }

    public void addBorrowRecord(BorrowRecord borrowRecord) {
        this.borrowRecords.add(borrowRecord);
    }
}