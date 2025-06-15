package Persistence;

import Model.Rating;
import Model.ReadingStatus;

public class BookDTO {
    private String title;
    private String author;
    private String isbn;
    private String genre;
    private Rating rating;
    private ReadingStatus readingStatus;

    public BookDTO() {}

    //metodi setter

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public void setReadingStatus(ReadingStatus readingStatus) {
        this.readingStatus = readingStatus;
    }

    //metodi getter
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getGenre() {
        return genre;
    }

    public Rating getRating() {
        return rating;
    }

    public ReadingStatus getReadingStatus() {
        return readingStatus;
    }
}


