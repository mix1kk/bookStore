package com.example.bookStore.Models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "book")
@Getter
@Setter
@Schema(description = "Сущность книги")
public class Book {
    @Id
    @Column(name = "\"bookId\"")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Идентификатор книги", example = "0")
    private int bookId;
    @Schema(description = "Название книги", example = "20 лет спустя")
    @Column(name = "\"bookTitle\"")
    private String bookTitle;
    //authors as string authors id's (format: "authorId1_authorId2_..._authorIdn")
    @Column(name = "\"authors\"")
    @Schema(description = "Авторы", example = "")
    private String authors;
    @Column(name = "\"ISBN\"")
    @Schema(description = "ISBN книги", example = "John Doe")
    private String ISBN;
}
