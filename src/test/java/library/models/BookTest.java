package library.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit Test Case for Book status logic.
 * This file fulfills the COMP3111 Lab 7 requirements.
 */
class BookTest {

    private Book testBook;

    /**
     * Set up a fresh Book object before each test.
     */
    @BeforeEach
    void setUp() {
        testBook = new Book("Test Driven Development");
    }

    /**
     * Test 1: Checks if a new Book is 'PENDING' by default.
     * This corresponds to the Author 'Publish New Book' use case.
     */
    @Test
    void testNewBookIsPending() {
        assertEquals(Book.Status.PENDING, testBook.getStatus(), "New book should be PENDING by default.");
    }

    /**
     * Test 2: Tests the approve() method on a Book.
     * This corresponds to the Librarian 'Manage Books' use case.
     */
    @Test
    void testBookApproval() {
        testBook.approve();
        assertEquals(Book.Status.APPROVED, testBook.getStatus(), "Book status should be APPROVED after approval.");
    }
}