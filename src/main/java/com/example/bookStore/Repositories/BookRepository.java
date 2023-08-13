package com.example.bookStore.Repositories;

import com.example.bookStore.Models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    Optional<Book> findByISBN(String ISBN);

    List<Book> findByAuthors(String authors);

    List<Book> findByTitle(String bookTitle);
}
