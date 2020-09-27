package se.lexicon.patrik.booklender.service;

import se.lexicon.patrik.booklender.dto.LoanDto;

import java.util.List;
import java.util.Optional;

public interface LoanService {
    Optional<LoanDto> findById(long loanId);
    List<LoanDto> findByBookId(int bookId);
    List<LoanDto> findByUserId(int userId);
    List<LoanDto> findByTerminated(boolean terminated);
    List<LoanDto> findAll();
    LoanDto create(LoanDto loanDto);
    LoanDto update(LoanDto loanDto);
    boolean delete(long loanId);
}
