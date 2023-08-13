package com.example.bookStore.Services;

import com.example.bookStore.Models.Author;
import com.example.bookStore.Models.Book;
import com.example.bookStore.Repositories.BookRepository;
import com.example.bookStore.Utils.BookExceptions.BookNotDeletedException;
import com.example.bookStore.Utils.BookExceptions.BookNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public List<Book> findByBookTitle(String bookTitle) {
        return bookRepository.findByBookTitle(bookTitle);
    }

    public Optional<Book> getBookByISBN(String bookISBN) {
        return bookRepository.findByISBN(bookISBN);
    }

    public List<Book> findBookByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    public Book findById(int bookId) {
        return bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);
    }

    public void save(Book book) {
        bookRepository.save(book);
    }

    public void update(int bookId, Book book) {
        book.setBookId(bookId);
        bookRepository.save(book);
    }

    public void delete(int bookId) {
        if (!bookRepository.existsById(bookId)) {
            throw new BookNotDeletedException("Книги не существует");
        }
        bookRepository.deleteById(bookId);
        if (bookRepository.existsById(bookId)) {
            throw new BookNotDeletedException("Не удалось удалить книгу");
        }
    }
}
