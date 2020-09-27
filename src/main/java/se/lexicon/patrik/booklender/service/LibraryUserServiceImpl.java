package se.lexicon.patrik.booklender.service;

import org.springframework.beans.factory.annotation.Autowired;
import se.lexicon.patrik.booklender.data.LibraryUserRepository;
import se.lexicon.patrik.booklender.dto.LibraryUserDto;
import se.lexicon.patrik.booklender.entity.LibraryUser;

import java.util.List;
import java.util.Optional;

public class LibraryUserServiceImpl implements LibraryUserService {

    private LibraryUserRepository userRepository;
    private ConversionService converter;

    @Autowired
    public LibraryUserServiceImpl(LibraryUserRepository userRepo, ConversionService converter) {
        this.userRepository = userRepo;
        this.converter = converter;
    }

    @Override
    public Optional<LibraryUserDto> findById(int userId) {

        return Optional.of(converter.userToDto(userRepository.findById(userId).get()));
    }

    @Override
    public Optional<LibraryUserDto> findByEmail(String email) {

        return Optional.of(converter.userToDto(userRepository.findByEmail(email).get()));
    }

    @Override
    public List<LibraryUserDto> findAll() {

        return converter.usersToDtos(userRepository.findAll());
    }

    @Override
    public LibraryUserDto create(LibraryUserDto libraryUserDto) throws IllegalArgumentException{

        if(libraryUserDto.getUserId() != 0) {
            throw new IllegalArgumentException("Invalid UserID nedd to be 0");
        }

        LibraryUser user = converter.dtoToUser(libraryUserDto);

        user = userRepository.save(user);

        return converter.userToDto(user);


    }

    @Override
    public LibraryUserDto update(LibraryUserDto libraryUserDto) throws IllegalArgumentException {

        if(libraryUserDto.getUserId() == 0) {
            throw new IllegalArgumentException("User has invalid id: " + libraryUserDto.getUserId());
        }

        LibraryUser user = userRepository.findByUserId(libraryUserDto.getUserId()).orElseThrow(IllegalArgumentException::new);

        user.setEmail(libraryUserDto.getEmail());

        user.setName(libraryUserDto.getName());

        userRepository.save(user);


        return libraryUserDto;


    }

    @Override
    public boolean delete(int userId) {

        LibraryUser user = userRepository.findById(userId).orElseThrow(IllegalArgumentException::new);

        userRepository.delete(user);

        return !userRepository.existsById(userId);
    }
}
