package se.lexicon.patrik.booklender.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long loanId;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private LibraryUser loanTaker;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Book book;
    private LocalDate loanDate;
    private boolean terminated;
    private LocalDate newLoanDate;

    public Loan(long loanId, LibraryUser loanTaker, Book book, LocalDate loanDate){
        this(loanTaker, book, loanDate);
        this.loanId = loanId;
    }

    public Loan(LibraryUser loanTaker, Book book, LocalDate loanDate) {
        this.loanTaker = loanTaker;
        this.book = book;
        this.loanDate = loanDate;
        this.terminated = false;
    }

    public Loan() {}

    public long getLoanId() {
        return loanId;
    }

    public LibraryUser getLoanTaker() {
        return loanTaker;
    }

    public void setLoanTaker(LibraryUser loanTaker) {
        this.loanTaker = loanTaker;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
        setNewLoanDate(this.loanDate);
    }

    public void returnedBook() {
        this.book.setAvailable(true);
        this.terminated = true;
    }

    public boolean isTerminated() {
        return terminated;
    }

    public void setTerminated(boolean terminated) {
        this.terminated = terminated;
    }

    public LocalDate getNewLoanDate() {
        return newLoanDate;
    }

    public void setNewLoanDate(LocalDate newLoanDate) {
        this.newLoanDate = newLoanDate;
    }

    public boolean isOverdue(){

        return LocalDate.now().isAfter(newLoanDate.plusDays(book.getMaxLoanDays()));
    }

    public BigDecimal getFine() {
        Period period = Period.between(loanDate.plusDays(book.getMaxLoanDays()), LocalDate.now());

        int daysOverdue = period.getDays();

        BigDecimal fine = BigDecimal.ZERO;
        if(daysOverdue > 0) {
            fine = BigDecimal.valueOf(daysOverdue * book.getFinePerDay().longValue());
        }
        return fine;
    }

    public boolean extendLoan(int days) throws RuntimeException {
        boolean isExtended = false;
        if (days <= book.getMaxLoanDays()) {
            if (!book.isReserved() && !isOverdue()) {
                newLoanDate = LocalDate.now();
                isExtended = true;
            }
        }
        return isExtended;
    }

    @Override
    public int hashCode() {
        return Objects.hash(book, loanDate, loanId, loanTaker, terminated);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Loan other = (Loan) obj;
        return Objects.equals(book, other.book) && Objects.equals(loanDate, other.loanDate) && loanId == other.loanId
                && Objects.equals(loanTaker, other.loanTaker) && terminated == other.terminated;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Loan [loanId=");
        builder.append(loanId);
        builder.append(", loanTaker=");
        builder.append(loanTaker);
        builder.append(", book=");
        builder.append(book);
        builder.append(", loanDate=");
        builder.append(loanDate);
        builder.append(", terminated=");
        builder.append(terminated);
        builder.append("]");
        return builder.toString();
    }
}
