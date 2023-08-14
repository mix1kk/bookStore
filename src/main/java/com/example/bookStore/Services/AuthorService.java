package com.example.bookStore.Services;

import com.example.bookStore.Models.Author;
import com.example.bookStore.Models.Book;
import com.example.bookStore.Repositories.AuthorRepository;
import com.example.bookStore.Utils.AuthorExceptions.AuthorAlreadyExistsException;
import com.example.bookStore.Utils.AuthorExceptions.AuthorNotDeletedException;
import com.example.bookStore.Utils.AuthorExceptions.AuthorNotExistsException;
import com.example.bookStore.Utils.AuthorExceptions.AuthorNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Author> findByShortname(String shortname) {
        return authorRepository.findByShortname(shortname);
    }

    public void save(Author author) throws AuthorAlreadyExistsException {
        if (!checkIfAuthorExists(author)) {
            author.setShortname(author.getSurname() + " " + author.getName().substring(0, 1).toUpperCase() + "."
                    + ((author.getMiddlename().equals("")) ? "" : author.getMiddlename().substring(0, 1).toUpperCase() + "."));
            authorRepository.save(author);
        } else {
            throw new AuthorAlreadyExistsException("Такой автор уже существует");
        }
    }


    public void update(int authorId, Author author) throws AuthorAlreadyExistsException{
        if (!checkIfAuthorExists(author)) {
            author.setAuthor_id(authorId);
            authorRepository.save(author);
        } else {
            throw new AuthorAlreadyExistsException("Такой автор уже существует");
        }
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

    private boolean checkIfAuthorExists(Author checkingAuthor) {
        for (Author author : authorRepository.findAll()) {
            if (checkingAuthor.getSurname().equals(author.getSurname())
                    && checkingAuthor.getName().equals(author.getName())
                    && checkingAuthor.getMiddlename().equals(author.getMiddlename())) {
                return true;
            }
        }
        return false;
    }
}
