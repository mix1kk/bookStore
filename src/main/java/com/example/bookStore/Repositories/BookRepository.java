package com.example.bookStore.Repositories;

import com.example.bookStore.Models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Integer> {
}
