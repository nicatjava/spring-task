package com.example.springtask.service.impl;

import com.example.springtask.dao.entity.AuthorEntity;
import com.example.springtask.dao.entity.BookEntity;
import com.example.springtask.dao.repository.AuthorRepository;
import com.example.springtask.dto.request.AuthorRequestDto;
import com.example.springtask.dto.response.AuthorResponseDto;
import com.example.springtask.dto.response.BookResponseDto;
import com.example.springtask.exception.AuthorNotFoundException;
import com.example.springtask.service.AuthorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public List<AuthorResponseDto> getAllAuthors() {
        log.info("Getting all authors with their books");
        List<AuthorEntity> allAuthors = authorRepository.findAll();

        return allAuthors.stream()
                .map(author -> {
                    List<BookResponseDto> books = author.getBooks().stream()
                            .map(book -> BookResponseDto.builder()
                                    .id(book.getId())
                                    .name(book.getName())
                                    .count(book.getCount())
                                    .authorSurname(book.getAuthor().getSurname())
                                    .build())
                            .toList();

                    return AuthorResponseDto.builder()
                            .id(author.getId())
                            .name(author.getName())
                            .surname(author.getSurname())
                            .books(books)
                            .build();
                        }).toList();
    }

    @Override
    public AuthorResponseDto getAuthorById(Long id) {
        log.info("Getting Author by id: {}", id);
        AuthorEntity authorEntity = authorRepository.findById(id).orElseThrow(()->new AuthorNotFoundException("Author Not Found"));
        List<BookEntity> list = authorEntity.getBooks();

        List<BookResponseDto> authorBooks = list.stream()
                .map(book -> BookResponseDto.builder()
                        .id(book.getId())
                        .name(book.getName())
                        .count(book.getCount())
                        .authorSurname(book.getAuthor().getSurname())
                        .build())
                .toList();

        return AuthorResponseDto.builder()
                .id(authorEntity.getId())
                .name(authorEntity.getName())
                .surname(authorEntity.getSurname())
                .books(authorBooks)
                .build();
    }

    @Override
    public AuthorResponseDto createAuthor(AuthorRequestDto authorRequestDto) {
        log.info("Creating Author: {}", authorRequestDto);
        AuthorEntity savedAuthor = authorRepository.save(AuthorEntity.builder()
                .name(authorRequestDto.getName())
                .surname(authorRequestDto.getSurname())
                .build());
        return AuthorResponseDto.builder()
                .id(savedAuthor.getId())
                .name(savedAuthor.getName())
                .surname(savedAuthor.getSurname())
                .build();
    }

    @Override
    public AuthorResponseDto updateAuthor(Long id, AuthorRequestDto authorRequestDto) {
        log.info("Updating Author: {}", authorRequestDto);
        AuthorEntity updatedAuthor = authorRepository.findById(id).orElseThrow(()->new AuthorNotFoundException("Author Not Found"));
        updatedAuthor.setName(authorRequestDto.getName());
        updatedAuthor.setSurname(authorRequestDto.getSurname());
        authorRepository.save(updatedAuthor);
        return  AuthorResponseDto.builder()
                .id(updatedAuthor.getId())
                .name(updatedAuthor.getName())
                .surname(updatedAuthor.getSurname())
                .build();
    }

    @Override
    public AuthorResponseDto deleteAuthor(Long id) {
        log.info("Deleting Author: {}", id);
        AuthorEntity deletedAuthor = authorRepository.findById(id).orElseThrow(()->new AuthorNotFoundException("Author Not Found"));
        authorRepository.delete(deletedAuthor);
        return  AuthorResponseDto.builder()
                .id(deletedAuthor.getId())
                .name(deletedAuthor.getName())
                .surname(deletedAuthor.getSurname())
                .build();
    }
}
