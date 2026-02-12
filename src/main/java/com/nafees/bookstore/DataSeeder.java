package com.nafees.bookstore;

import com.nafees.bookstore.entity.Book;
import com.nafees.bookstore.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final BookRepository repository;

    @Override
    public void run(String... args) {
        if (repository.count() == 0) {
            repository.save(Book.builder().title("1984").author("George Orwell").isbn("1234567890").year(1949).build());
            repository.save(Book.builder().title("Clean Code").author("Robert C. Martin").isbn("2345678901").year(2008).build());
            repository.save(Book.builder().title("Java Concurrency in Practice").author("Brian Goetz").isbn("3456789012").year(2006).build());
        }
    }
}
