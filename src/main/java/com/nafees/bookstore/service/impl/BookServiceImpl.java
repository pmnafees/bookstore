package com.nafees.bookstore.service.impl;

import com.nafees.bookstore.dto.BookRequestDTO;
import com.nafees.bookstore.dto.BookResponseDTO;
import com.nafees.bookstore.dto.PagedResponse;
import com.nafees.bookstore.entity.Book;
import com.nafees.bookstore.exception.ResourceNotFoundException;
import com.nafees.bookstore.mapper.BookMapper;
import com.nafees.bookstore.repository.BookRepository;
import com.nafees.bookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {

    private final BookRepository repository;

    @Override
    public BookResponseDTO create(BookRequestDTO request) {
        log.info("Creating book: {}", request.title());
        Book book = BookMapper.toEntity(request);
        BookResponseDTO response = BookMapper.toDTO(repository.save(book));
        log.info("Book created with ID: {}", response.id());
        return response;
    }

    @Override
    public PagedResponse<BookResponseDTO> getAll(String author, String title, String genre, Pageable pageable) {

        Specification<Book> spec = Specification.where(null);

        if (author != null && !author.isBlank())
            spec = spec.and((root, query, cb) ->
                    cb.like(cb.lower(root.get("author")), "%" + author.toLowerCase() + "%"));

        if (title != null && !title.isBlank())
            spec = spec.and((root, query, cb) ->
                    cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%"));

        if (genre != null && !genre.isBlank())
            spec = spec.and((root, query, cb) ->
                    cb.like(cb.lower(root.get("genre")), "%" + genre.toLowerCase() + "%"));

        Page<Book> page = repository.findAll(spec, pageable);

        return new PagedResponse<>(
                page.getContent().stream().map(BookMapper::toDTO).toList(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages()
        );
    }

    @Override
    public BookResponseDTO getById(Long id) {
        Book book = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + id));
        return BookMapper.toDTO(book);
    }

    @Override
    public BookResponseDTO update(Long id, BookRequestDTO request) {
        log.info("Updating book with ID: {}", id);
        Book book = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + id));
        BookMapper.updateEntity(book, request);
        BookResponseDTO response = BookMapper.toDTO(repository.save(book));
        log.info("Book updated with ID: {}", response.id());
        return response;
    }

    @Override
    public BookResponseDTO partialUpdate(Long id, Map<String, Object> updates) {
        log.info("Partially updating book with ID {}: {}", id, updates);
        Book book = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + id));

        updates.forEach((key, value) -> {
            switch (key) {
                case "title" -> book.setTitle((String) value);
                case "author" -> book.setAuthor((String) value);
                case "isbn" -> book.setIsbn((String) value);
                case "year" -> book.setYear((Integer) value);

            }
        });

        BookResponseDTO response = BookMapper.toDTO(repository.save(book));
        log.info("Book partially updated with ID: {}", response.id());
        return response;
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting book with ID: {}", id);
        Book book = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + id));
        repository.delete(book);
        log.info("Book deleted with ID: {}", id);
    }
}
