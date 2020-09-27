package se.lexicon.patrik.booklender.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.patrik.booklender.entity.Book;
import se.lexicon.patrik.booklender.entity.LibraryUser;
import se.lexicon.patrik.booklender.entity.Loan;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class LoanRepositoryTest {

    Loan testLoan;
    LibraryUser testUser;
    Book testBook;
    long loanId;

    @Autowired
    LoanRepository loanRepository;

    @BeforeEach
    void setUp(){
        testUser = new LibraryUser(LocalDate.of(2020,9,10), "TestGuy", "test@test.com");
        testBook = new Book("Testing for dummies", 30, BigDecimal.valueOf(10), "Test");
        testLoan = new Loan(testUser, testBook, LocalDate.of(2020,9,11), false);
        loanId = testLoan.getLoanId();
        loanRepository.save(testLoan);
    }

    @Test
    void findByUserId(){
        List<Loan> result = loanRepository.findByUserId(testUser.getUserId());

        assertNotNull(result);
        assertEquals(1, result.size());
        assertTrue(result.contains(testLoan));
    }
}
