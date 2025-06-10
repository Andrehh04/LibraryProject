package Researching;

import Model.Book;
import Model.Rating;
import Model.ReadingStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Unit test for Researching module.
 * verifies searchByAuthor, searchByISBN and searchByTitle operations and edge cases
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ResearchingTest {
    List<Book> books = new LinkedList<>();
    BookResearch bookResearch = new BookResearchImpl();
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
        Book book5 = b5.setGenre("Narrativa").build();


        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(book5);
    }

    @Test
    public void researchBooksTest(){
        ResearchStrategy searchAuthor = new SearchByAuthor();
        ResearchStrategy searchISBN = new SearchByISBN();
        ResearchStrategy searchTitle = new SearchByTitle();
        List<Book> trovati;

        //ricerca per autore
        bookResearch.setStrategy(searchAuthor);
        trovati=bookResearch.searchBook(books,"Luigi Pirandello");
        assertEquals(2, trovati.size());
        //ricerca per autore con autore null
        trovati=bookResearch.searchBook(books,null);
        assertEquals(0, trovati.size());
        //ricerca per autore con lista null
        trovati=bookResearch.searchBook(null,"Luigi Pirandello");
        assertEquals(0, trovati.size());

        //ricerca per ISBN
        bookResearch.setStrategy(searchISBN);
        trovati=bookResearch.searchBook(books,"9788822776358"); //ISBN de "La coscienza di Zeno"
        assertEquals(1, trovati.size());
        //ricerca per ISBN con ISBN null
        trovati=bookResearch.searchBook(books,null);
        assertEquals(0, trovati.size());
        //ricerca per ISBN con lista null
        trovati=bookResearch.searchBook(null,"9788822776358");
        assertEquals(0, trovati.size());
        //ricerca per ISBN inesistente
        trovati=bookResearch.searchBook(books,"123");
        assertEquals(0, trovati.size());

        //ricerca per titolo
        bookResearch.setStrategy(searchTitle);
        trovati=bookResearch.searchBook(books,"la coscienza di zeno");
        assertEquals(1, trovati.size());
        //ricerca per ISBN con ISBN null
        trovati=bookResearch.searchBook(books,null);
        assertEquals(0, trovati.size());
        //ricerca per ISBN con lista null
        trovati=bookResearch.searchBook(null,"il visconte dimezzato");
        assertEquals(0, trovati.size());
        //ricerca per titolo inesistente
        trovati=bookResearch.searchBook(books,"titoloInesistente");
        assertEquals(0, trovati.size());


    }
}
