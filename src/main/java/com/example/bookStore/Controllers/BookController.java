package com.example.bookStore.Controllers;

import com.example.bookStore.Models.Author;
import com.example.bookStore.Models.Book;
import com.example.bookStore.Services.BookService;
import com.example.bookStore.Utils.AuthorExceptions.AuthorErrorResponse;
import com.example.bookStore.Utils.AuthorExceptions.AuthorNotCreatedException;
import com.example.bookStore.Utils.AuthorExceptions.AuthorNotDeletedException;
import com.example.bookStore.Utils.AuthorExceptions.AuthorNotUpdatedException;
import com.example.bookStore.Utils.BookExceptions.BookErrorResponse;
import com.example.bookStore.Utils.BookExceptions.BookNotCreatedException;
import com.example.bookStore.Utils.BookExceptions.BookNotDeletedException;
import com.example.bookStore.Utils.BookExceptions.BookNotUpdatedException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@Tag(name = "Контроллер книг", description = "Позволяет добавлять, удалять, редактировать книги")
@RequestMapping()
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @Operation(summary = "Получить список всех книг")
    @GetMapping("/books")
    public List<Book> allBooks() {
        return new ArrayList<>(bookService.findAll());
    }

    @Operation(summary = "Получить книгу по id")
    @GetMapping("/book/{id}")
    public Book singleBook(@PathVariable("id") int id) {
        return bookService.findById(id);
    }

    @Operation(summary = "Добавить новую книгу")
    @PostMapping("/books")
    public ResponseEntity<HttpStatus> createAuthor(@RequestBody @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("<br>");
            }
            throw new BookNotCreatedException(errorMsg.toString());
        }
        bookService.save(book);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Редактировать книгу")
    @PatchMapping("/book/{id}")
    public ResponseEntity<HttpStatus> updateAuthor(@RequestBody @Valid Book book, BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("<br>");
            }

            throw new BookNotUpdatedException(errorMsg.toString());
        }

        bookService.update(id, book);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Удалить книгу")
    @DeleteMapping("/book/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") int id) {
        bookService.delete(id);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler
    private ResponseEntity<BookErrorResponse> handleException(BookNotUpdatedException e) {
        BookErrorResponse response = new BookErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<BookErrorResponse> handleException(BookNotDeletedException e) {
        BookErrorResponse response = new BookErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<BookErrorResponse> handleException(BookNotCreatedException e) {
        BookErrorResponse response = new BookErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
