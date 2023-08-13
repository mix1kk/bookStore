package com.example.bookStore.Repositories;

import com.example.bookStore.Models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Integer> {
}

