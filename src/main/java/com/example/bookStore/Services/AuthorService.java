package com.example.bookStore.Services;

import com.example.bookStore.Models.Author;
import com.example.bookStore.Repositories.AuthorRepository;
import com.example.bookStore.Utils.AuthorExceptions.AuthorNotDeletedException;
import com.example.bookStore.Utils.AuthorExceptions.AuthorNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class AuthorService {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public Author findById(int authorId) {
        return authorRepository.findById(authorId).orElseThrow(AuthorNotFoundException::new);
    }

    public void save(Author author) {
        authorRepository.save(author);
    }

    public void update(int authorId, Author author) {
        author.setAuthorId(authorId);
        authorRepository.save(author);
    }

    public void delete(int authorId) {
        if (!authorRepository.existsById(authorId)) {
            throw new AuthorNotDeletedException("Автора не существует");
        }
        authorRepository.deleteById(authorId);
        if (authorRepository.existsById(authorId)) {
            throw new AuthorNotDeletedException("Не удалось удалить автора");
        }
    }
}
