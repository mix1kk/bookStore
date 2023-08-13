package com.example.bookStore.Repositories;

import com.example.bookStore.Models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByBookTitle(String bookTitle);

    Optional<Book> findByISBN(String bookISBN);

    List<Book> findByAuthor(String bookAuthor);
}
