package com.example.springtask.service.impl;

import com.example.springtask.dao.entity.AuthorEntity;
import com.example.springtask.dao.entity.BookEntity;
import com.example.springtask.dao.repository.AuthorRepository;
import com.example.springtask.dao.repository.BookRepository;
import com.example.springtask.dto.request.BookRequestDto;
import com.example.springtask.dto.response.BookResponseDto;
import com.example.springtask.exception.AuthorNotFoundException;
import com.example.springtask.exception.BookNotFoundException;
import com.example.springtask.mapper.BookMapper;
import com.example.springtask.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Override
    public List<BookResponseDto> getAllBooks() {
        log.info("Getting all books");
        List<BookEntity> allBooks = bookRepository.findAll();
        return allBooks.stream()
                .map(BookMapper.INSTANCE::toDto)
                .toList();
    }

    @Override
    public List<BookResponseDto> getBooksByAuthorId(Long authorId) {
        log.info("Getting all books with author id {}", authorId);
        authorRepository.findById(authorId).orElseThrow(()->new AuthorNotFoundException("Author Not Found"));

        List<BookEntity> booksByAuthor = bookRepository.findByAuthorId(authorId)
                .orElseThrow(() -> new RuntimeException("No books found for the given author"));

        return booksByAuthor.stream()
                .map(book -> BookResponseDto.builder()
                        .id(book.getId())
                        .name(book.getName())
                        .count(book.getCount())
                        .authorSurname(book.getAuthor().getSurname())
                        .build())
                .toList();
    }

    @Override
    public BookResponseDto createBookForAuthor(Long authorId, BookRequestDto bookRequestDto) {
        log.info("Creating book with author id {}", authorId);
        AuthorEntity author = authorRepository.findById(authorId)
                .orElseThrow(()->new AuthorNotFoundException("Author Not Found"));

        BookEntity book = BookMapper.INSTANCE.toEntity(bookRequestDto);

        BookEntity savedBook = bookRepository.save(book);
        return BookMapper.INSTANCE.toDto(savedBook);
    }

    @Override
    public BookResponseDto updateBook(Long bookId,Long authorId, BookRequestDto bookRequestDto) {
        log.info("Updating book with id {}", bookId);
        BookEntity book = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException("Book Not Found"));

        AuthorEntity author = authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException("Author Not Found"));

        book.setName(bookRequestDto.getName());
        book.setCount(bookRequestDto.getCount());
        book.setAuthor(author);

        BookEntity updated = bookRepository.save(book);
        return BookResponseDto.builder()
                .id(updated.getId())
                .name(updated.getName())
                .count(updated.getCount())
                .authorSurname(updated.getAuthor().getSurname())
                .build();
    }

    @Override
    public BookResponseDto deleteBook(Long bookId) {
        log.info("Deleting book with id {}", bookId);
        BookEntity book = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException("Book Not Found"));
        bookRepository.delete(book);
        return BookResponseDto.builder()
                .id(book.getId())
                .name(book.getName())
                .count(book.getCount())
                .authorSurname(book.getAuthor().getSurname())
                .build();
    }
}
