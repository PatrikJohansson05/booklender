package se.lexicon.patrik.booklender.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.patrik.booklender.dto.BookDto;
import se.lexicon.patrik.booklender.service.BookService;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    BookService bookService;


    @GetMapping("/findId/{bookId}")
    public ResponseEntity<Optional<BookDto>> findById(@PathVariable Integer bookId) {
        return ResponseEntity.ok(bookService.findById(bookId));
    }

    @GetMapping("/find")
    public ResponseEntity<Object> find(
            @RequestParam(value = "type", defaultValue = "all") String type,
            @RequestParam(value = "value", defaultValue = "all") String value,
            @RequestParam(value = "status", defaultValue = "all") String status) {

        switch (type) {
            case "bookId":
                return ResponseEntity.ok(bookService.findById(Integer.parseInt(value)));
            case "reserved":
                return ResponseEntity.ok(bookService.findByReserved(Boolean.parseBoolean(status)));
            case "available":
                return ResponseEntity.ok(bookService.findByAvailable(Boolean.parseBoolean(status)));
            case "title":
                return ResponseEntity.ok(bookService.findByTitle(value));
            case "all":
                return ResponseEntity.ok(bookService.findAll());
            default:
                throw new IllegalArgumentException("Not a Valid type: [ " + type + " ]");
        }
    }

    @PostMapping("/add")
    public ResponseEntity<BookDto> create(@Valid @RequestBody BookDto bookDto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.create(bookDto));

    }

    @PutMapping("/update")
    public ResponseEntity<BookDto> update(@Valid @RequestBody BookDto bookDto) {

        return ResponseEntity.ok(bookService.update(bookDto));
    }

    @DeleteMapping("/delete/{bookId}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer bookId) {

        return ResponseEntity.ok(bookService.delete(bookId));
    }
}