package Management;
import Model.*;
import java.util.List;
public interface BookManagement {
    boolean addBook(List<Book> list, Book book);
    Book remove(List<Book> list, Book book);
    void modifyGenre(Book book, String genre);
    void modifyRating(Book book, Rating rating);
    void modifyStatus(Book book, ReadingStatus status);

}
