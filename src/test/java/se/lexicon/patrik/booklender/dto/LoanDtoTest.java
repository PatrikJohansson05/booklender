package se.lexicon.patrik.booklender.dto;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class LoanDtoTest {
    private LoanDto testLoan;
    private BookDto testBook;
    private LibraryUserDto testUser;

    @BeforeEach
    public void setup(){
        testBook = new BookDto(1, "Test", true, false, 30, BigDecimal.valueOf(10), "Test");
        testUser = new LibraryUserDto(1, LocalDate.of(2020,9,15), "TestGuy", "test@test.com");
        testLoan = new LoanDto(1, testUser, testBook, LocalDate.of(2020,9,15), false);
    }

    @Test
    public void test_create_loan_success() {

        LocalDate expectedLoanDate = LocalDate.of(2020,9,15);

        assertEquals(1, testLoan.getLoanId());
        assertEquals(testUser, testLoan.getLoanTaker());
        assertEquals(testBook, testLoan.getBook());
        assertEquals(expectedLoanDate, testLoan.getLoanDate());
        assertTrue(testLoan.getBook().isAvailable());
        assertFalse(testLoan.isTerminated());
    }
}
