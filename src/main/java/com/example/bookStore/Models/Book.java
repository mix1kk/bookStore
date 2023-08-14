package com.example.bookStore.Models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Objects;

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
    @NotEmpty(message = "Поле не должно быть пустым")
    @Schema(description = "Название книги", example = "20 лет спустя")
    private String title;
    @Pattern(regexp = "[A-ZА-Я][a-zа-я]*\\s([A-ZА-Я]\\.)+([,]\\s[A-ZА-Я][a-zа-я]*\\s([A-ZА-Я]\\.)+)*",
            message = "Неправильный формат: 'Фамилия И.О., Фамилия И.О.'")
    @Schema(description = "Авторы", example = "Дюма.А.")
    private String authors;
    @NotEmpty(message = "Поле не должно быть пустым")
    @Schema(description = "ISBN книги", example = "978-2-266-11156-0")
    private String ISBN;

}
