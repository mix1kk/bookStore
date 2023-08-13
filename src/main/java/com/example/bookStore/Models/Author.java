package com.example.bookStore.Models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "author")
@Getter
@Setter
@Schema(description = "Сущность автора")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Идентификатор автора", example = "0")
    private int author_id;
    @Schema(description = "Имя автора", example = "Иван")
    private String author_name;
    @Schema(description = "Фамилия автора", example = "Иванов")
    private String author_surname;
    @Schema(description = "Отчество автора", example = "Иванович")
    private String author_middlename;

//    //books as string books id's (format: "bookId1_bookId2_..._bookIdn")
//    @Column(name = "\"books\"")
//    private String books;
}
