package com.example.springtask.controller;

import com.example.springtask.dto.request.BookRequestDto;
import com.example.springtask.dto.response.BookResponseDto;
import com.example.springtask.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
@Validated
public class BookController {
    private final BookService bookService;

    @GetMapping
    public List<BookResponseDto> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{authorId}")
    public List<BookResponseDto> getBooksByAuthorId(@PathVariable Long authorId) {
        return bookService.getBooksByAuthorId(authorId);
    }

    @PostMapping("/{authorId}")
    public BookResponseDto createBook(@PathVariable Long authorId,@Valid @RequestBody BookRequestDto bookRequestDto) {
        return bookService.createBookForAuthor(authorId, bookRequestDto);
    }

    @PutMapping("/{bookId}/{authorId}")
    public BookResponseDto updateBook(@PathVariable Long bookId, @PathVariable Long authorId,
                                      @Valid @RequestBody BookRequestDto bookRequestDto) {
        return bookService.updateBook(bookId, authorId, bookRequestDto);
    }

    @DeleteMapping("/{bookId}")
    public BookResponseDto deleteBook(@PathVariable Long bookId) {
        return bookService.deleteBook(bookId);
    }
}