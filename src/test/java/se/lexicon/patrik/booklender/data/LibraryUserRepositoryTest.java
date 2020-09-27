package se.lexicon.patrik.booklender.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.patrik.booklender.entity.LibraryUser;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class LibraryUserRepositoryTest {

    LibraryUser testUser;
    LibraryUser testUser2;

    @Autowired
    LibraryUserRepository libraryUserRepository;

    @BeforeEach
    void setUp(){
        testUser = new LibraryUser(LocalDate.of(2020,9,10), "TestGuy", "test@test.com");
        testUser2 = new LibraryUser(LocalDate.of(2020,9,10), "TestGuy2", "test2@test.com");
        libraryUserRepository.save(testUser);
        libraryUserRepository.save(testUser2);
    }

    @Test
    void findByEmail(){
        LibraryUser email1 = libraryUserRepository.findByEmail("test@test.com");
        LibraryUser email2 = libraryUserRepository.findByEmail("test2@test.com");
        LibraryUser email3 = libraryUserRepository.findByEmail("test3@test.com");
        assertNotNull(email1);
        assertNotNull(email2);
        assertEquals(email1, testUser);
        assertEquals(email2, testUser2);
        assertTrue(email1.getName().equals("TestGuy"));
        assertEquals(email2.getRegDate(),LocalDate.of(2020,9,10));
        assertNull(email3);
    }
}
