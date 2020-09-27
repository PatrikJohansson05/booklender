package se.lexicon.patrik.booklender.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LibraryUserDtoTest {

    private LibraryUserDto testUser;

    @BeforeEach
    public void setup() {
        testUser = new LibraryUserDto(1, LocalDate.of(2020,9,15), "TestGuy", "test@test.com");
    }

    @Test
    public void test_create_LibraryUser_success() {

        LocalDate expectedRegDate = LocalDate.of(2020,9,15);
        String expectedName = "TestGuy";
        String expectedEmail = "test@test.com";


        assertEquals(1,testUser.getUserId());
        assertEquals(expectedRegDate, testUser.getRegDate());
        assertEquals(expectedName, testUser.getName());
        assertEquals(expectedEmail, testUser.getEmail());
    }
}
