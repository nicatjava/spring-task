package com.example.springtask.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class AuthorResponseDto {
    private Long id;
    private String name;
    private String surname;
    private List<BookResponseDto> books;
}
