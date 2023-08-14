package com.example.bookStore.Repositories;

import com.example.bookStore.Models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query(value = "SELECT * FROM Book t WHERE t.ISBN LIKE %?1%", nativeQuery = true)
    List<Book> findByISBN(String ISBN);

    @Query(value = "SELECT * FROM Book t WHERE t.authors LIKE %?1%", nativeQuery = true)
    List<Book> findByAuthors(String authors);

    @Query(value = "SELECT * FROM Book t WHERE t.title LIKE %?1%", nativeQuery = true)
    List<Book> findByTitle(String bookTitle);
}
