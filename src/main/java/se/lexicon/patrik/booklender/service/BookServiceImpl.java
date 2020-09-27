package se.lexicon.patrik.booklender.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.patrik.booklender.data.BookRepository;
import se.lexicon.patrik.booklender.dto.BookDto;
import se.lexicon.patrik.booklender.service.ConversionService;
import se.lexicon.patrik.booklender.entity.Book;

import java.util.List;
import java.util.Optional;

public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;
    private ConversionService converter;


    @Autowired
    public BookServiceImpl(BookRepository bookRepo, ConversionService converter) {
        this.bookRepository = bookRepo;
        this.converter = converter;
    }

    @Override
    public List<BookDto> findByReserved(boolean reserved) {

        return converter.booksToDtos(bookRepository.findByReserved(reserved));
    }


    @Override
    public List<BookDto> findByAvailable(boolean available) {

        return converter.booksToDtos(bookRepository.findByAvailable(available));
    }

    @Override
    public List<BookDto> findByTitle(String title) {

        return converter.booksToDtos(bookRepository.findByTitle(title));
    }

    @Override
    public Optional<BookDto> findById(int bookId) {

        return Optional.of(converter.bookToDto(bookRepository.findById(bookId).get()));
    }

    @Override
    public List<BookDto> findAll() {
        return converter.booksToDtos(bookRepository.findAll());
    }

    @Override
    public BookDto create(BookDto bookDto) throws IllegalArgumentException{
        if(bookDto.getBookId() != 0) {
            throw new IllegalArgumentException("Invalid Book ID need to  be 0");
        }

        Book book = converter.dtoToBook(bookDto);

        book = bookRepository.save(book);

        return converter.bookToDto(book);

    }

    @Override
    public BookDto update(BookDto bookDto) throws IllegalArgumentException{

        if(bookDto.getBookId() == 0) {
            throw new IllegalArgumentException("Book has invalid id: " + bookDto.getBookId());
        }

        Book book = bookRepository.findByBookId(bookDto.getBookId()).orElseThrow(IllegalArgumentException::new);

        if (bookDto.isAvailable()) {
            book.setAvailable(true);
        }

        if(bookDto.isReserved()) {
            book.setReserved(true);
        }
        book.setTitle(bookDto.getTitle());
        book.setFinePerDay(bookDto.getFinePerDay());
        book.setMaxLoanDays(bookDto.getMaxLoanDays());
        book.setDescription(bookDto.getDescription());
        bookRepository.save(book);

        return bookDto;

    }

    @Override
    public boolean delete(int bookId) {

        Book book = bookRepository.findById(bookId).orElseThrow(IllegalArgumentException::new);

        bookRepository.delete(book);

        return !bookRepository.existsById(bookId);

    }

}
