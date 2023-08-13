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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Идентификатор книги", example = "0")
    private int book_id;
    @Schema(description = "Название книги", example = "20 лет спустя")
    private String title;
    @Schema(description = "Авторы", example = "Александр Дюма")
    private String authors;
    @Schema(description = "ISBN книги", example = "ISBN-13: 978-2-266-11156-0")
    private String ISBN;
}
