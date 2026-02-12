package com.nafees.bookstore.service;

import com.nafees.bookstore.dto.BookRequestDTO;
import com.nafees.bookstore.dto.BookResponseDTO;
import com.nafees.bookstore.dto.PagedResponse;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface BookService {

    BookResponseDTO create(BookRequestDTO request);

    PagedResponse<BookResponseDTO> getAll(String author, String title, String genre, Pageable pageable);

    BookResponseDTO getById(Long id);

    BookResponseDTO update(Long id, BookRequestDTO request);

    BookResponseDTO partialUpdate(Long id, Map<String, Object> updates);

    void delete(Long id);
}
