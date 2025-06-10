package Ordering;
import Model.Book;
import Model.Rating;
import Model.ReadingStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for Ordering module.
 * verified OrderByAuthor, OrderByGenre, OrderByStatus and OrderByTitle operations and edge cases
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OrderingTest {
    List<Book> books = new LinkedList<>();
    BookOrder bookOrder = new BookOrderImpl();
    @BeforeAll
    public void setUp(){
        Book.Builder b1 = new Book.Builder("La coscienza di Zeno","Italo Svevo","9788822776358");
        Book book1 = b1.setReadingStatus(ReadingStatus.READ).setGenre("Romanzo").setRating(Rating.CINQUE).build();

        Book.Builder b2 = new Book.Builder("Uno, nessuno e centomila","Luigi Pirandello","9788822776365");
        Book book2 = b2.setReadingStatus(ReadingStatus.READ).setGenre("Novella").build();

        Book.Builder b3 = new Book.Builder("Il visconte dimezzato","Italo Calvino","9788478441716");
        Book book3 = b3.setReadingStatus(ReadingStatus.READING).setGenre("Narrativa").setRating(Rating.CINQUE).build();

        Book.Builder b4 = new Book.Builder("I promessi sposi","Alessandro Manzoni","9788838439193");
        Book book4 = b4.setGenre("Romanzo").setRating(Rating.QUATTRO).setReadingStatus(ReadingStatus.NONE).build();

        Book.Builder b5 = new Book.Builder("Il fu Mattia Pascal","Luigi Pirandello","9788822776334");
        Book book5 = b5.setGenre("Narrativa").setReadingStatus(ReadingStatus.TOREAD).build();


        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(book5);
    }

    @Test
    public void orderBooksTest(){
        List<Book> ordinati;
        OrderStrategy orderAuthor = new OrderByAuthor();
        OrderStrategy orderGenre = new OrderByGenre();
        OrderStrategy orderStatus = new OrderByStatus();
        OrderStrategy orderTitle = new OrderByTitle();

        //ordinamento per autore
        bookOrder.setStrategy(orderAuthor);
        ordinati=bookOrder.Order(books);
        assertEquals("Alessandro Manzoni",ordinati.getFirst().getAuthor());
        assertEquals("Luigi Pirandello",ordinati.getLast().getAuthor());
        //ordinamento per autore con lista null
        ordinati=bookOrder.Order(null);
        assertEquals(0,ordinati.size());

        //ordinamento per genere
        bookOrder.setStrategy(orderGenre);
        ordinati=bookOrder.Order(books);
        assertEquals("Narrativa",ordinati.getFirst().getGenre());
        assertEquals("Romanzo",ordinati.getLast().getGenre());
        //ordinamento per autore con lista null
        ordinati=bookOrder.Order(null);
        assertEquals(0,ordinati.size());

        //ordinamento per status
        bookOrder.setStrategy(orderStatus);
        ordinati=bookOrder.Order(books);
        assertEquals(ReadingStatus.NONE,ordinati.getFirst().getReadingStatus());
        assertEquals(ReadingStatus.TOREAD,ordinati.getLast().getReadingStatus());
        //ordinamento per autore con lista null
        ordinati=bookOrder.Order(null);
        assertEquals(0,ordinati.size());

        //ordinamento per titolo
        bookOrder.setStrategy(orderTitle);
        ordinati=bookOrder.Order(books);
        assertEquals("I promessi sposi",ordinati.getFirst().getTitle());
        assertEquals("Uno, nessuno e centomila",ordinati.getLast().getTitle());
        //ordinamento per autore con lista null
        ordinati=bookOrder.Order(null);
        assertEquals(0,ordinati.size());
    }
}
