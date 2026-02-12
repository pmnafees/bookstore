package com.nafees.bookstore.dto;

public record BookResponseDTO(
        Long id,
        String title,
        String author,
        String isbn,
        Integer year
) {}
