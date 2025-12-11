package com.nafees.bookstore.controller;

import com.nafees.bookstore.model.Book;
import com.nafees.bookstore.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService svc;
    public BookController(BookService svc){ this.svc = svc; }

    @GetMapping
    public List<Book> all(@RequestParam(required = false) String author){
        if (author != null) return svc.findByAuthor(author);
        return svc.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> get(@PathVariable Long id){
        return svc.getById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Book> create(@Valid @RequestBody Book book){
        Book saved = svc.save(book);
        return ResponseEntity.created(URI.create("/api/books/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> update(@PathVariable Long id, @Valid @RequestBody Book book){
        return svc.getById(id).map(existing -> {
            existing.setTitle(book.getTitle());
            existing.setAuthor(book.getAuthor());
            existing.setPublishedOn(book.getPublishedOn());
            Book updated = svc.save(existing);
            return ResponseEntity.ok(updated);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        if (svc.getById(id).isPresent()){
            svc.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
