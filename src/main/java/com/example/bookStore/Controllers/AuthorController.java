package com.example.bookStore.Controllers;

import com.example.bookStore.Models.Author;
import com.example.bookStore.Services.AuthorService;
import com.example.bookStore.Utils.AuthorExceptions.*;
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
import java.util.stream.Collectors;

@RestController
@Tag(name = "Контроллер авторов", description = "Позволяет добавлять, удалять, редактировать авторов")
@RequestMapping()
public class AuthorController {
    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Operation(summary = "Получить список всех авторов")
    @GetMapping("/authors")
    public List<Author> allAuthors() {
        return new ArrayList<>(authorService.findAll());
    }

    @Operation(summary = "Получить автора по id")
    @GetMapping("/author/{id}")
    public Author singleAuthor(@PathVariable("id") int id) {
        return authorService.findById(id);
    }

    @Operation(summary = "Создать нового автора")
    @PostMapping("/authors")
    public ResponseEntity<HttpStatus> createAuthor(@RequestBody @Valid Author author, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("<br>");
            }
            throw new AuthorNotCreatedException(errorMsg.toString());
        }
        try {
            authorService.save(author);
        } catch (AuthorAlreadyExistsException e) {
            throw new AuthorNotCreatedException(e.getMessage());
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Редактировать автора")
    @PatchMapping("/author/{id}")
    public ResponseEntity<HttpStatus> updateAuthor(@RequestBody @Valid Author author, BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("<br>");
            }
            throw new AuthorNotUpdatedException(errorMsg.toString());
        }
        if (id != 0 && authorService.findById(id) == null) {
            throw new AuthorNotFoundException();
        }
        try {
            authorService.update(id, author);
        } catch (AuthorAlreadyExistsException e) {
            throw new AuthorNotUpdatedException(e.getMessage());
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Удалить автора")
    @DeleteMapping("/author/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") int authorId) {
        authorService.delete(authorId);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }//todo: delete all author books if delete author

    @ExceptionHandler
    private ResponseEntity<AuthorErrorResponse> handleException(AuthorNotUpdatedException e) {
        AuthorErrorResponse response = new AuthorErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<AuthorErrorResponse> handleException(AuthorNotDeletedException e) {
        AuthorErrorResponse response = new AuthorErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<AuthorErrorResponse> handleException(AuthorNotCreatedException e) {
        AuthorErrorResponse response = new AuthorErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
