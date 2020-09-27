package se.lexicon.patrik.booklender.data;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.patrik.booklender.entity.Book;


import java.util.List;
import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Integer> {
    List<Book> findByReserved (boolean reserved);
    List<Book> findByAvailable (boolean available);
    List<Book> findByTitle (String title);
    Optional<Book> findByBookId (int bookId);
    List<Book> findAll();
}
