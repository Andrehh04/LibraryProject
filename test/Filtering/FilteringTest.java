package Filtering;

import Model.Book;
import Model.Rating;
import Model.ReadingStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.LinkedList;
import java.util.List;
/**
 * Unit test for Filter module.
 * verifies filterByGenre and filterByStatus operation and edge cases.
 */
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FilteringTest {
    List<Book> books = new LinkedList<>();
    BookFilter bookFilter = new BookFilterImpl();
    @BeforeAll
    public void setUp(){
        Book.Builder b1 = new Book.Builder("La coscienza di Zeno","Italo Svevo","9788822776358");
        Book book1 = b1.setReadingStatus(ReadingStatus.READ).setGenre("Romanzo").setRating(Rating.CINQUE).build();

        Book.Builder b2 = new Book.Builder("Il piccolo principe","Antoine de Saint-Exupéry","9780544109100");
        Book book2 = b2.setReadingStatus(ReadingStatus.READ).setGenre("Novella").build();

        Book.Builder b3 = new Book.Builder("Il visconte dimezzato","Italo Calvino","9788478441716");
        Book book3 = b3.setReadingStatus(ReadingStatus.READING).setGenre("Narrativa").setRating(Rating.CINQUE).build();

        Book.Builder b4 = new Book.Builder("I promessi sposi","Alessandro Manzoni","9788838439193");
        Book book4 = b4.setGenre("Romanzo").setRating(Rating.QUATTRO).setReadingStatus(ReadingStatus.NONE).build();

        //libro senza status
        Book.Builder b5 = new Book.Builder("Il fu Mattia Pascal","Luigi Pirandello","9788822776334");
        Book book5 = b5.setGenre("Narrativa").build();


        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(book5);
    }

    @Test
    public void filterBooksTest(){
        FilterStrategy strategy1 = new FilterByGenre();
        FilterStrategy strategy2 = new FilterByStatus();
        List<Book> filtrata;

        //filtraggio per genere
        bookFilter.setStrategy(strategy1);
        filtrata = bookFilter.filter(books,"Narrativa");
        assertTrue(filtrata.size()==2 && filtrata.contains(books.get(2))  && filtrata.contains(books.get(4)));

        //filtraggio per genere che non esiste
        filtrata = bookFilter.filter(books,"genereInesistente");
        assertTrue(filtrata.isEmpty());

        //filtraggio per status
        bookFilter.setStrategy(strategy2);
        filtrata = bookFilter.filter(books,"read");
        assertEquals(2, filtrata.size());

        //filtraggio per status che non esiste
        filtrata = bookFilter.filter(books,"Non esistente");
        assertEquals(0, filtrata.size());

        //filtraggio con parametro null
        filtrata = bookFilter.filter(books, null);
        assertTrue(filtrata.isEmpty());

        //filtraggio con lista null
        filtrata = bookFilter.filter(null, "Narrativa");
        assertTrue(filtrata.isEmpty());
    }


}
