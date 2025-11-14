package library.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit Test Case for User status logic.
 * This file fulfills the COMP3111 Lab 7 requirements.
 */
class UserTest {

    private User testUser;

    /**
     * Set up a fresh User object before each test.
     */
    @BeforeEach
    void setUp() {
        testUser = new User("testUser");
    }

    /**
     * Test 1: Checks if a new User is 'active' by default.
     */
    @Test
    void testNewUserIsActive() {
        assertTrue(testUser.isActive(), "New user should be active by default.");
    }

    /**
     * Test 2: Tests the deactivate() method on a User.
     * This corresponds to the Librarian 'Manage Profile' use case.
     */
    @Test
    void testUserDeactivation() {
        testUser.deactivate();
        assertFalse(testUser.isActive(), "User should be inactive after deactivation.");
    }
}