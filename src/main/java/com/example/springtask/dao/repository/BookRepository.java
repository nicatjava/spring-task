package com.example.springtask.dao.repository;

import com.example.springtask.dao.entity.BookEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

    @EntityGraph(attributePaths = "author")
    Optional<List<BookEntity>> findByAuthorId(Long authorId);
}
