package com.nafees.bookstore.mapper;

import com.nafees.bookstore.dto.BookRequestDTO;
import com.nafees.bookstore.dto.BookResponseDTO;
import com.nafees.bookstore.entity.Book;

public class BookMapper {

    public static Book toEntity(BookRequestDTO dto) {
        return Book.builder()
                .title(dto.title())
                .author(dto.author())
                .isbn(dto.isbn())
                .year(dto.year())
                .build();
    }

    public static void updateEntity(Book book, BookRequestDTO dto) {
        book.setTitle(dto.title());
        book.setAuthor(dto.author());
        book.setIsbn(dto.isbn());
        book.setYear(dto.year());

    }

    public static BookResponseDTO toDTO(Book book) {
        return new BookResponseDTO(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getIsbn(),
                book.getYear()

        );
    }
}
