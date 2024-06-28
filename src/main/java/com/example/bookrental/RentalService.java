package com.example.bookrental;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.*;


@Service
public class RentalService {
    private final RestTemplate restTemplate;

    public RentalService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Book getBookById(Long id) {
        String url = "http://localhost:8080/get-book/" + id;
        ResponseEntity<Book> response = restTemplate.getForEntity(url, Book.class);
        return response.getBody();
    }

    public List<Book> getAllBooks() {
        String url = "http://localhost:8080/get-all-books";
        ResponseEntity<Book[]> response = restTemplate.getForEntity(url, Book[].class);
        return Arrays.asList(response.getBody());
    }

    public Book addBook(Book book) {
        String url = "http://localhost:8080/add-book";
        ResponseEntity<Book> response = restTemplate.postForEntity(url, book, Book.class);
        return response.getBody();
    }

    public void deleteBook(Long id) {
        String url = "http://localhost:8080/delete-book/" + id;
        restTemplate.delete(url);
    }

    public Book updateBook(Long id, Book book) {
        String url = "http://localhost:8080/update-book/" + id;
        restTemplate.put(url, book);
        return getBookById(id);
    }

}
