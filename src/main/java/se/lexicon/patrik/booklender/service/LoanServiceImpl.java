package se.lexicon.patrik.booklender.service;

import org.springframework.beans.factory.annotation.Autowired;
import se.lexicon.patrik.booklender.data.BookRepository;
import se.lexicon.patrik.booklender.data.LibraryUserRepository;
import se.lexicon.patrik.booklender.data.LoanRepository;
import se.lexicon.patrik.booklender.dto.LoanDto;
import se.lexicon.patrik.booklender.entity.Loan;

import java.util.List;
import java.util.Optional;

public class LoanServiceImpl implements LoanService {

    private LoanRepository loanRepository;

    private LibraryUserRepository userRepository;

    private BookRepository bookRepository;

    private ConversionService converter;


    @Autowired
    public LoanServiceImpl(LoanRepository loanRepo, LibraryUserRepository userRepo, BookRepository bookRepo, ConversionService converter) {
        this.loanRepository = loanRepo;
        this.userRepository = userRepo;
        this.bookRepository = bookRepo;
        this.converter = converter;
    }



    @Override
    public Optional<LoanDto> findById(long loanId) {

        return Optional.of(converter.loanToDto(loanRepository.findByLoanId(loanId).get()));
    }

    @Override
    public List<LoanDto> findByBookId(int bookId) {

        return converter.loansToDtos(loanRepository.findByBookId(bookId));
    }

    @Override
    public List<LoanDto> findByUserId(int userId) {

        return converter.loansToDtos(loanRepository.findByUserId(userId));

    }

    @Override
    public List<LoanDto> findByTerminated(boolean terminated) {

        return converter.loansToDtos(loanRepository.findByTerminated(terminated));
    }

    @Override
    public List<LoanDto> findAll() {

        return converter.loansToDtos(loanRepository.findAll());
    }

    @Override
    public LoanDto create(LoanDto loanDto) {
        if(loanDto.getLoanId() != 0) {
            throw new IllegalArgumentException();
        }

        Loan loanEntity = converter.dtoToLoan(loanDto);


        loanEntity.setLoanTaker(userRepository.findById(loanEntity.getLoanTaker().getUserId()).get());

        loanEntity.setBook(bookRepository.findByBookId(loanEntity.getBook().getBookId()).get());



        loanEntity = loanRepository.save(loanEntity);


        return converter.loanToDto(loanEntity);
    }

    @Override
    public LoanDto update(LoanDto loanDto) throws IllegalArgumentException{

        if(loanDto.getLoanId() == 0) {
            throw new IllegalArgumentException("Loan has invalid id: " + loanDto.getLoanId());
        }
        Loan loan = loanRepository.findByLoanId(loanDto.getLoanId()).orElseThrow(IllegalArgumentException::new);

        if (loanDto.isTerminated()) {
            loan.returnedBook();
        }

        loanRepository.save(loan);


        return loanDto;
    }

    @Override
    public boolean delete(long loanId) {

        Loan loan = loanRepository.findByLoanId(loanId).orElseThrow(IllegalArgumentException::new);

        loanRepository.delete(loan);

        return !loanRepository.existsById(loanId);


    }
}
