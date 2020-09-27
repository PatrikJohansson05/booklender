package se.lexicon.patrik.booklender.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EntityFactory {
    protected Book createBook(String title, int maxLoanDays, BigDecimal finePerDay, String description, boolean available, boolean reserved) {
        return new Book(title, maxLoanDays, finePerDay, description);
    }

    protected LibraryUser createLibraryUser(LocalDate regDate, String name, String email) {
        return new LibraryUser(regDate, name, email);
    }

    protected Loan createLoan(LibraryUser loanTaker, Book book, LocalDate loanDate) {
        return new Loan(loanTaker, book, loanDate);
    }
}
