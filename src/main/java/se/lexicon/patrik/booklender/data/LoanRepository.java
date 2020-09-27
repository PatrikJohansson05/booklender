package se.lexicon.patrik.booklender.data;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.patrik.booklender.entity.Loan;

import java.util.List;
import java.util.Optional;

public interface LoanRepository extends CrudRepository<Loan, Long> {
    List<Loan> findByUserId (Integer userId);
    List<Loan> findByBookId (Integer bookId);
    List<Loan> findByTerminated (boolean terminatedStatus);
    Optional<Loan> findByLoanId (long loanId);
    List<Loan> findAll();
}
