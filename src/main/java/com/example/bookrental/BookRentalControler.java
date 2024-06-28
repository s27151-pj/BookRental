package com.example.bookrental;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@Tag(name = "Book Rental")
@RequestMapping("/rentals")
public class BookRentalControler {
    private RentalService rentalService;

    public BookRentalControler(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping("/get-all-books")
    @Operation(summary = "View a list of available books", description = "Get a list of all available books", responses = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list")
    })
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = rentalService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @PostMapping("/add-book")
    @Operation(summary = "Add a new book", description = "Add a new book to the list", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully added book")
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Book.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<Book> addBook(
            @Parameter(description = "Book object to be added", required = true) @RequestBody Book book) {
        Book savedBook = rentalService.addBook(book);
        return ResponseEntity.ok(savedBook);
    }

    @GetMapping("/get-book/{id}")
    @Operation(summary = "Get a book by ID", description = "Retrieve a book by its ID", responses = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved book"),
            @ApiResponse(responseCode = "404", description = "Book not found", content = @Content)
    })
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = rentalService.getBookById(id);
        return ResponseEntity.ok(book);
    }

    @DeleteMapping("/delete-book/{id}")
    @Operation(summary = "Delete a book by ID", description = "Delete a book by its ID", responses = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted book"),
            @ApiResponse(responseCode = "404", description = "Book not found", content = @Content)
    })
    public ResponseEntity<Void> deleteBook(
            @Parameter(description = "ID of the book to delete", required = true) @PathVariable Long id) {
        rentalService.deleteBook(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update-book/{id}")
    @Operation(summary = "Update a book by ID", description = "Update the details of a book by its ID", responses = {
            @ApiResponse(responseCode = "200", description = "Successfully updated book"),
            @ApiResponse(responseCode = "404", description = "Book not found", content = @Content)
    })
    public ResponseEntity<Book> updateBook(
            @Parameter(description = "ID of the book to update", required = true) @PathVariable Long id,
            @Parameter(description = "Updated book object", required = true) @RequestBody Book updateBook) {
        Book book = rentalService.updateBook(id, updateBook);
        return ResponseEntity.ok(book);
    }
}
