package se.lexicon.patrik.booklender.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LibraryUserTest {
    LibraryUser testUser;

    @BeforeEach
    void setUp(){
        testUser = new LibraryUser(LocalDate.of(2020,9,10), "TestGuy", "test@test.com");
    }

    @Test
    void successfully_created() {
        assertNotNull(testUser);
    }

    @Test
    void getUserId() {
        assertEquals(0, testUser.getUserId());
    }

    @Test
    void getRegDate() {
        assertEquals(LocalDate.of(2020,9,10), testUser.getRegDate());
    }

    @Test
    void setRegDate() {
        testUser.setRegDate(LocalDate.of(2020,9,30));
        assertEquals(LocalDate.of(2020,9,30), testUser.getRegDate());
    }

    @Test
    void getName() {
        assertEquals("TestGuy", testUser.getName());
    }

    @Test
    void setName() {
        testUser.setName("Test");
        assertEquals("Test", testUser.getName());
    }

    @Test
    void getEmail() {
        assertEquals("test@test.com", testUser.getEmail());
    }

    @Test
    void setEmail() {
        testUser.setEmail("test2@test.com");
        assertEquals("test2@test.com", testUser.getEmail());
    }

}
