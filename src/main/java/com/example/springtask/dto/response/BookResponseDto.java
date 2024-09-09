package com.example.springtask.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BookResponseDto {
    private Long id;
    private String name;
    private Integer count;
    private String authorSurname;
}
