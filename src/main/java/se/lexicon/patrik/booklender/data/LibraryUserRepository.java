package se.lexicon.patrik.booklender.data;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.patrik.booklender.entity.LibraryUser;


import java.util.List;
import java.util.Optional;

public interface LibraryUserRepository extends CrudRepository<LibraryUser, Integer> {
    Optional<LibraryUser> findByEmail(String email);
    Optional<LibraryUser> findByUserId(int userId);
    List<LibraryUser> findAll();
}
