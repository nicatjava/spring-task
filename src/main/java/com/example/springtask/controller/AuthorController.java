package com.example.springtask.controller;

import com.example.springtask.dto.request.AuthorRequestDto;
import com.example.springtask.dto.response.AuthorResponseDto;
import com.example.springtask.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/authors")
@RequiredArgsConstructor
@Validated
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public List<AuthorResponseDto> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/{id}")
    public AuthorResponseDto getAuthorById(@PathVariable Long id) {
        return authorService.getAuthorById(id);
    }

    @PostMapping
    public AuthorResponseDto createAuthor(@Valid @RequestBody AuthorRequestDto authorRequestDto) {
        return authorService.createAuthor(authorRequestDto);
    }

    @PutMapping("/{id}")
    public AuthorResponseDto updateAuthor(@PathVariable Long id, @Valid @RequestBody AuthorRequestDto authorRequestDto) {
        return authorService.updateAuthor(id, authorRequestDto);
    }

    @DeleteMapping("/{id}")
    public AuthorResponseDto deleteAuthor(@PathVariable Long id) {
        return authorService.deleteAuthor(id);
    }
}
