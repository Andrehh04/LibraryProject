package Management;

import Model.Book;
import Model.Rating;
import Model.ReadingStatus;

import java.util.List;

public class BookManagementImpl implements BookManagement {

    @Override
    public boolean addBook(List<Book> list, Book book) {
        list.add(book);
        return true;
    }

    @Override
    public Book remove(List<Book> list, Book book) {
        if (!list.contains(book)){
            throw new IllegalArgumentException();
        }
        list.remove(book);
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
}
