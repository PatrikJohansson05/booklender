package se.lexicon.patrik.booklender.dto;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class BookDtoTest {
    private BookDto testBook;

    @BeforeEach
    public void setup(){
        testBook = new BookDto(1, "Test", true, false, 30, BigDecimal.valueOf(10), "Test");
    }

    @Test
    public void test_create_book_success(){
        int expectedMaxLoanDays = 30;
        BigDecimal expectedFinePerDay = BigDecimal.valueOf(10);
        String expectedDescription = "Test";
        String expectedTitle = "Test";

        assertEquals(1, testBook.getBookId());
        assertEquals(expectedTitle, testBook.getTitle());
        assertEquals(expectedMaxLoanDays, testBook.getMaxLoanDays());
        assertEquals(expectedFinePerDay, testBook.getFinePerDay());
        assertEquals(expectedDescription, testBook.getDescription());
        assertTrue(testBook.isAvailable());
        assertFalse(testBook.isReserved());
    }
}
