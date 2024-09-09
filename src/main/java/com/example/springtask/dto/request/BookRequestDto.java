package com.example.springtask.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookRequestDto {
    @NotBlank
    @Size(min = 3, max = 20)
    private String name;
    @Min(value = 1)
    @Max(value = 100)
    private Integer count;
}