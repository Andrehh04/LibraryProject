package Management;

import Model.Book;
import Model.Rating;
import Model.ReadingStatus;

import java.util.LinkedList;
import java.util.List;

public class BookManagementImpl implements BookManagement {

    private final LinkedList<Book> books = new LinkedList<>();
    @Override
    public void addBook(Book book) {
        books.add(book);
    }

    @Override
    public Book remove(Book book) {
        if (!books.contains(book)){
            throw new IllegalArgumentException();
        }
        books.remove(book);
        return book;
    }

    @Override
    public void modifyGenre(Book book, String genre) {
        book.setGenre(genre);
    }

    @Override
    public void modifyRating(Book book, Rating rating) {
        book.setRating(rating);
    }

    @Override
    public void modifyStatus(Book book, ReadingStatus status) {
        book.setreadingStatus(status);
    }

    public LinkedList<Book> getBooks() {
        return books;
    }
}
