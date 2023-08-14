package com.example.bookStore.Models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
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
    @NotEmpty
    @Schema(description = "Имя автора", example = "Иван")
    private String name;
    @NotEmpty
    @Schema(description = "Фамилия автора", example = "Иванов")
    private String surname;
    @Schema(description = "Отчество автора", example = "Иванович")
    private String middlename;
    @Schema(description = "Короткое имя автора", example = "Иванов И.И.")
    private String shortname;

}
