package com.example.bookStore.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "author")
@Getter
@Setter
public class Author {
    @Id
    @Column(name = "\"authorId\"")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int authorId;
    @Column(name = "\"authorName\"")
    private String authorName;
    //books as string books id's (format: "bookId1_bookId2_..._bookIdn")
    @Column(name = "\"books\"")
    private String books;
}
