package Management;
import Model.*;
import java.util.List;
public interface BookManagement {
    void addBook(Book book);
    Book remove(Book book);
    void modifyGenre(Book book, String genre);
    void modifyRating(Book book, Rating rating);
    void modifyStatus(Book book, ReadingStatus status);
    List<Book> getBooks();

}
