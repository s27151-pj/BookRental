package com.example.bookrental;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Details about the book")
public class Book {
    @Schema(description = "The unique ID of the book", example = "1")
    private long id;
    @Schema(description = "The name of the movie", example = "The Hobbit")
    private String title;
    @Schema(description = "The category of the movie", example = "FANTASY")
    private Category category;
    @Schema(description = "The sum of pages of the book", example = "310")
    private long pages;

    public Book() {}
    public Book(long id, String title, Category category, long pages) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.pages = pages;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public long getPages() {
        return pages;
    }

    public void setPages(long pages) {
        this.pages = pages;
    }
}

