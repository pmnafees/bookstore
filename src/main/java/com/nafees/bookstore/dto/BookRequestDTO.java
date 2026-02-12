package com.nafees.bookstore.dto;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record BookRequestDTO(

        @NotBlank
        @Size(max = 200)
        String title,

        @NotBlank
        @Size(max = 120)
        String author,

        @NotBlank
        @Size(max = 20)
        String isbn,

        Integer year
) {}

