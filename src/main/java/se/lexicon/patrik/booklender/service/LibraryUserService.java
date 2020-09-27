package se.lexicon.patrik.booklender.service;

import se.lexicon.patrik.booklender.dto.LibraryUserDto;

import java.util.List;
import java.util.Optional;

public interface LibraryUserService {
    Optional<LibraryUserDto> findById(int userId);
    Optional<LibraryUserDto> findByEmail(String email);
    List<LibraryUserDto> findAll();
    LibraryUserDto create(LibraryUserDto libraryUserDto);
    LibraryUserDto update(LibraryUserDto libraryUserDto);
    boolean delete(int userId);
}
