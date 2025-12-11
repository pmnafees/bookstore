package com.nafees.bookstore.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 200)
    private String title;

    @NotBlank
    @Size(max = 120)
    private String author;

    private LocalDate publishedOn;

    // constructors
    public Book() {}
    public Book(String title, String author, LocalDate publishedOn){
        this.title = title;
        this.author = author;
        this.publishedOn = publishedOn;
    }

    // getters & setters
    public Long getId(){ return id; }
    public void setId(Long id){ this.id = id; }

    public String getTitle(){ return title; }
    public void setTitle(String title){ this.title = title; }

    public String getAuthor(){ return author; }
    public void setAuthor(String author){ this.author = author; }

    public LocalDate getPublishedOn(){ return publishedOn; }
    public void setPublishedOn(LocalDate publishedOn){ this.publishedOn = publishedOn; }
}
