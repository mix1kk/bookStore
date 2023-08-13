package com.example.bookStore.Controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@Tag(name = "Контроллер книг", description = "Позволяет добавлять, удалять, редактировать книги")
@RequestMapping()
public class BookController {
}
