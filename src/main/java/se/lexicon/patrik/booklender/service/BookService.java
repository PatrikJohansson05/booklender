package se.lexicon.patrik.booklender.service;

import se.lexicon.patrik.booklender.dto.BookDto;

import java.util.List;
import java.util.Optional;


public interface BookService {
    List<BookDto>findByReserved(boolean reserved);
    List<BookDto>findByAvailable(boolean available);
    List<BookDto> findByTitle(String title);
    Optional<BookDto> findById(int bookId);
    List<BookDto> findAll();
    BookDto create(BookDto bookDto);
    BookDto update(BookDto bookDto);
    boolean delete(int bookId);
}
