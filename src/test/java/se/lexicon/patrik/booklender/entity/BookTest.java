package se.lexicon.patrik.booklender.entity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class BookTest {
    private Book testBook;

    @BeforeEach
    void setup(){
        testBook = new Book("Testing for dummies", 30, BigDecimal.valueOf(10), "Test");
    }
    @Test
    void BookCreated() {
        assertNotNull(testBook);
    }


    @Test
    void getBookId() {
        assertEquals(0, testBook.getBookId());
    }

    @Test
    void getTitle() {
        assertEquals("Testing for dummies", testBook.getTitle());
    }

    @Test
    void setTitle() {
        testBook.setTitle("Test");
        assertEquals("Test", testBook.getTitle());
    }

    @Test
    void isAvailable() {
        assertFalse(testBook.isAvailable());
    }

    @Test
    void setAvailable() {
        testBook.setAvailable(true);
        assertTrue(testBook.isAvailable());
    }

    @Test
    void isReserved() {
        assertFalse(testBook.isReserved());
    }

    @Test
    void setReserved() {
        testBook.setReserved(true);
        assertTrue(testBook.isReserved());
    }

    @Test
    void getMaxLoanDays() {
        assertEquals(30, testBook.getMaxLoanDays());
    }

    @Test
    void setMaxLoanDays() {
        testBook.setMaxLoanDays(35);
        assertEquals(35, testBook.getMaxLoanDays());
    }

    @Test
    void getFinePerDay() {
        assertEquals(BigDecimal.valueOf(10), testBook.getFinePerDay());
    }

    @Test
    void setFinePerDay() {
        testBook.setFinePerDay(BigDecimal.valueOf(11));
        assertEquals(BigDecimal.valueOf(11), testBook.getFinePerDay());
    }

    @Test
    void getDescription() {
        assertEquals("Test", testBook.getDescription());
    }

    @Test
    void setDescription() {
        testBook.setDescription("Test set");
        assertEquals("Test set", testBook.getDescription());
    }
}
