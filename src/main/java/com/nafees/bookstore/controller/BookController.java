package com.nafees.bookstore.controller;

import com.nafees.bookstore.dto.BookRequestDTO;
import com.nafees.bookstore.dto.BookResponseDTO;
import com.nafees.bookstore.dto.PagedResponse;
import com.nafees.bookstore.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
@Slf4j
@Validated
@Tag(name = "Book APIs", description = "Operations related to books")
public class BookController {

    private final BookService bookService;

    @PostMapping
    @Operation(summary = "Create a new book")
    public ResponseEntity<BookResponseDTO> create(@Valid @RequestBody BookRequestDTO request) {
        log.info("Creating book: {}", request.title());
        BookResponseDTO response = bookService.create(request);
        log.info("Book created with ID: {}", response.id());
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping
    @Operation(summary = "Get books with optional filtering and sorting")
    public ResponseEntity<PagedResponse<BookResponseDTO>> getAll(
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String genre,
            Pageable pageable
    ) {
        log.info("Fetching books with filters - author: {}, title: {}, genre: {}", author, title, genre);
        PagedResponse<BookResponseDTO> response = bookService.getAll(author, title, genre, pageable);
        log.info("Fetched {} books", response.getContent().size());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get book by ID")
    public ResponseEntity<BookResponseDTO> getById(@PathVariable Long id) {
        log.info("Fetching book by ID: {}", id);
        BookResponseDTO response = bookService.getById(id);
        log.info("Fetched book: {}", response.title());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update entire book by ID")
    public ResponseEntity<BookResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody BookRequestDTO request
    ) {
        log.info("Updating book with ID: {}", id);
        BookResponseDTO updated = bookService.update(id, request);
        log.info("Book updated with ID: {}", updated.id());
        return ResponseEntity.ok(updated);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Partially update book by ID")
    public ResponseEntity<BookResponseDTO> partialUpdate(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates
    ) {
        log.info("Partially updating book with ID {}: {}", id, updates);
        BookResponseDTO updated = bookService.partialUpdate(id, updates);
        log.info("Book partially updated with ID: {}", updated.id());
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete book by ID")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("Deleting book with ID: {}", id);
        bookService.delete(id);
        log.info("Book deleted with ID: {}", id);
        return ResponseEntity.noContent().build();
    }
}
