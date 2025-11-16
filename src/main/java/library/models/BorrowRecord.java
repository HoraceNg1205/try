package library.models;

public class BorrowRecord {
    private int recordId;
    private Student borrower;
    private Book book;
    private String borrowDate;
    private String dueDate;
    private String returnDate;
    private String status; // Active, Returned, Overdue, Reserved

    public BorrowRecord(int recordId, Student borrower, Book book, String borrowDate, String dueDate, String status) {
        this.recordId = recordId;
        this.borrower = borrower;
        this.book = book;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.status = status;
    }

    // Getters
    public int getRecordId() { return recordId; }
    public Student getBorrower() { return borrower; }
    public Book getBook() { return book; }
    public String getBorrowDate() { return borrowDate; }
    public String getDueDate() { return dueDate; }
    public String getReturnDate() { return returnDate; }
    public String getStatus() { return status; }

    public void markReturned(String returnDate) {
        this.returnDate = returnDate;
        this.status = "Returned";
    }
}