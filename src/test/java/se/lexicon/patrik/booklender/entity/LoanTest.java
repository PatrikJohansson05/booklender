package se.lexicon.patrik.booklender.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class LoanTest {
    private Loan testLoan;
    private LibraryUser testUser;
    private LibraryUser testUser2;
    private Book testBook;
    private Book testBook2;

    @BeforeEach
    void setup(){
        testUser = new LibraryUser(LocalDate.of(2020,9,10), "TestGuy", "test@test.com");
        testUser2 = new LibraryUser(LocalDate.of(2020, 9, 11), "TestGuy2", "test2@test.com");
        testBook = new Book("testBook", 30, BigDecimal.valueOf(10), "test book");
        testBook2 = new Book("testBook2", 30, BigDecimal.valueOf(10), "test book 2");
        testLoan = new Loan(testUser, testBook, LocalDate.of(2020,9,11));
    }

    @Test
    void TestCreated(){
        assertNotNull(testLoan);
        assertNotNull(testUser);
        assertNotNull(testBook);
    }

    @Test
    void extendLoan() {
        testBook.setReserved(true);
        assertFalse(testLoan.extendLoan(30));
        assertFalse(testLoan.extendLoan(40));
        testBook.setReserved(false);
        testLoan.setLoanDate(LocalDate.now());
        assertTrue(testLoan.extendLoan(30));

    }

    @Test
    void getLoanId() {
        assertEquals(0,testLoan.getLoanId());
    }

    @Test
    void getLoanTaker() {
        assertEquals(testUser, testLoan.getLoanTaker());
    }

    @Test
    void setLoanTaker() {
        testLoan.setLoanTaker(testUser2);
        assertEquals(testUser2, testLoan.getLoanTaker());
    }

    @Test
    void getBook() {
        assertEquals(testBook, testLoan.getBook());
    }

    @Test
    void setBook() {
        testLoan.setBook(testBook2);
        assertEquals(testBook2, testLoan.getBook());
    }

    @Test
    void getLoanDate() {
        assertEquals(LocalDate.of(2020,9,11), testLoan.getLoanDate());
    }

    @Test
    void setLoanDate() {
        testLoan.setLoanDate(LocalDate.of(2020,9,15));
        assertEquals(LocalDate.of(2020,9,15), testLoan.getLoanDate());
    }

    @Test
    void isTerminated() {
        assertFalse(testLoan.isTerminated());
    }

    @Test
    void setTerminated() {
        testLoan.setTerminated(true);
        assertTrue(testLoan.isTerminated());
    }
}
