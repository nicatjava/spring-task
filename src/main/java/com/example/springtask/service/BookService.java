package com.example.springtask.service;

import com.example.springtask.dto.request.BookRequestDto;
import com.example.springtask.dto.response.BookResponseDto;

import java.util.List;

public interface BookService {
    List<BookResponseDto> getAllBooks();
    List<BookResponseDto> getBooksByAuthorId(Long authorId);
    BookResponseDto createBookForAuthor(Long authorId, BookRequestDto bookRequestDto);
    BookResponseDto updateBook(Long bookId,Long authorId, BookRequestDto bookRequestDto);
    BookResponseDto deleteBook(Long bookId);
}