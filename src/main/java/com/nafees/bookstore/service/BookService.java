package com.nafees.bookstore.service;

import com.nafees.bookstore.model.Book;
import com.nafees.bookstore.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookService {
    private final BookRepository repo;

    public BookService(BookRepository repo){
        this.repo = repo;
    }

    public Book save(Book b){ return repo.save(b); }
    public List<Book> listAll(){ return repo.findAll(); }
    public Optional<Book> getById(Long id){ return repo.findById(id); }
    public void delete(Long id){ repo.deleteById(id); }
    public List<Book> findByAuthor(String authorPart){ return repo.findByAuthorContainingIgnoreCase(authorPart); }
}
