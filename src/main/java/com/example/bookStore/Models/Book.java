package com.example.bookStore.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "book")
@Getter
@Setter
public class Book {
    @Id
    @Column(name = "\"bookId\"")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;
    @Column(name = "\"bookTitle\"")
    private String bookTitle;
    //authors as string authors id's (format: "authorId1_authorId2_..._authorIdn")
    @Column(name = "\"authors\"")
    private String authors;
    @Column(name = "\"ISBN\"")
    private String ISBN;
}
