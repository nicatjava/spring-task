package com.example.springtask.service;

import com.example.springtask.dto.request.AuthorRequestDto;
import com.example.springtask.dto.response.AuthorResponseDto;

import java.util.List;

public interface AuthorService {
    List<AuthorResponseDto> getAllAuthors();
    AuthorResponseDto getAuthorById(Long id);
    AuthorResponseDto createAuthor(AuthorRequestDto authorRequestDto);
    AuthorResponseDto updateAuthor(Long id, AuthorRequestDto authorRequestDto);
    AuthorResponseDto deleteAuthor(Long id);
}
