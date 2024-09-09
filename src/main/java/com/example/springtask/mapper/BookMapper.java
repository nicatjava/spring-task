package com.example.springtask.mapper;

import com.example.springtask.dao.entity.BookEntity;
import com.example.springtask.dto.request.BookRequestDto;
import com.example.springtask.dto.response.BookResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);
    BookResponseDto toDto(BookEntity bookEntity);
    BookEntity toEntity(BookRequestDto bookRequestDto);
}
