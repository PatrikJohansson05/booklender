package se.lexicon.patrik.booklender.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.patrik.booklender.dto.LibraryUserDto;
import se.lexicon.patrik.booklender.service.LibraryUserService;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "/api/users")
public class LibraryUserController {

    private LibraryUserService userService;


    @GetMapping
    public ResponseEntity<String> testMessageController(){
        return ResponseEntity.ok("LibraryUser Controller");
    }


    @GetMapping("/findid/{userId}")
    public ResponseEntity<Optional<LibraryUserDto>> findById(@Valid @PathVariable Integer userId) {

        Optional<LibraryUserDto> userDto = userService.findById(userId);

        if ( userDto == null ) {
            throw new RuntimeException("Not found");
        }

        return ResponseEntity.ok(userDto);

    }

    @GetMapping("/findemail/{email}")
    public ResponseEntity<Optional<LibraryUserDto>> findByEmail(@Valid @PathVariable String email) {

        return ResponseEntity.ok(userService.findByEmail(email));

    }

    @GetMapping("/findall")
    public ResponseEntity<List<LibraryUserDto>> findAll() {

        return ResponseEntity.ok(userService.findAll());

    }

    @PostMapping("/add")
    public ResponseEntity<LibraryUserDto> create(@Valid @RequestBody LibraryUserDto userDto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(userDto));
    }

    @PutMapping("/update")
    public ResponseEntity<LibraryUserDto> update(@Valid @RequestBody LibraryUserDto userDto) {

        return ResponseEntity.ok(userService.update(userDto));
    }
}
