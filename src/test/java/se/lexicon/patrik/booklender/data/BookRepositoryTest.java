package se.lexicon.patrik.booklender.data;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.patrik.booklender.entity.Book;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class BookRepositoryTest {
    Book testBook;
    Book testBook2;


    @Autowired
    BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        testBook = new Book("Testing for dummies", 30, BigDecimal.valueOf(10), "Test");
        testBook2 = new Book("testBook2", 30, BigDecimal.valueOf(10), "test book 2");

        bookRepository.save(testBook);
        bookRepository.save(testBook2);
    }

    @Test
    void findAllByReserved() {
        assertEquals(2, bookRepository.findByReserved(false).size());
        assertEquals(0, bookRepository.findByReserved(true).size());
        assertTrue(bookRepository.findByReserved(false).contains(testBook));
        assertTrue(bookRepository.findByReserved(false).contains(testBook2));

        testBook.setReserved(true);
        bookRepository.save(testBook);

        assertEquals(1, bookRepository.findByReserved(false).size());
        assertEquals(1, bookRepository.findByReserved(true).size());
        assertTrue(bookRepository.findByReserved(true).contains(testBook));
        assertFalse(bookRepository.findByReserved(true).contains(testBook2));
        assertTrue(bookRepository.findByReserved(false).contains(testBook2));
        assertFalse(bookRepository.findByReserved(false).contains(testBook));
    }

    @Test
    void findAllByAvailable() {
        assertEquals(2, bookRepository.findByAvailable(false).size());
        assertEquals(0, bookRepository.findByAvailable(true).size());
        assertTrue(bookRepository.findByAvailable(false).contains(testBook));
        assertTrue(bookRepository.findByAvailable(false).contains(testBook2));

        testBook.setAvailable(true);
        testBook2.setAvailable(true);
        bookRepository.save(testBook);
        bookRepository.save(testBook2);

        assertEquals(0, bookRepository.findByAvailable(false).size());
        assertEquals(2, bookRepository.findByAvailable(true).size());
        assertTrue(bookRepository.findByAvailable(true).contains(testBook));
        assertTrue(bookRepository.findByAvailable(true).contains(testBook2));
        assertFalse(bookRepository.findByAvailable(false).contains(testBook2));
        assertFalse(bookRepository.findByAvailable(false).contains(testBook));
    }
}
