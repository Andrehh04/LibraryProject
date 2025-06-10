package Facade;

import Model.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration test suite for the LibraryImpl.
 * Covers book management (add, remove, modify, undo/redo operations),
 * search, filter, and order functionalities.
 */

//utilizzo la seguente annotazione per far si che tutti i test condividano la stessa istanza
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LibraryFacadeTest {
    LibraryImpl library = LibraryImpl.INSTANCE;

    //Inizializziamo la libreria utilizzando l'annotazione @BeforeAll
    @BeforeAll
    public void setUpLibrary() {
        Book.Builder b1 = new Book.Builder("La coscienza di Zeno","Italo Svevo","9788822776358");
        Book book1 = b1.setReadingStatus(ReadingStatus.READ).
                setGenre("Romanzo").setRating(Rating.CINQUE).build();
        Book.Builder b2 = new Book.Builder("Il piccolo principe","Antoine de Saint-Exupéry","9780544109100");
        Book book2 = b2.setReadingStatus(ReadingStatus.TOREAD).setGenre("Novella").build();
        Book.Builder b3 = new Book.Builder("Il visconte dimezzato","Italo Calvino","9788478441716");
        Book book3 = b3.setReadingStatus(ReadingStatus.READING).setGenre("Narrativa").setRating(Rating.CINQUE).build();


        library.handle(library.createAddCommand(book1));
        library.handle(library.createAddCommand(book2));
        library.handle(library.createAddCommand(book3));
    }

    @Test
    public void manageBooksTest(){
        //dopo il @BeforeAll ci dovrebbero essere tre libri
        assertEquals(3, library.getBooks().size());

        //undo dell'aggiunta dell'ultimo libro: i libri dovrebbero essere due
        library.undo();
        assertEquals(2, library.getBooks().size());

        //redo: i libri tornano 3
        library.redo();
        assertEquals(3, library.getBooks().size());

        //modifica del genere dell'ultimo libro aggiunto (il visconte dimezzato)
        library.handle(library.createModifyGenreCommand(library.getBooks().getLast(),"Cavalleresco"));
        assertEquals("Cavalleresco", library.getBooks().getLast().getGenre());

        //undo: il genere dovrebbe tornare "Narrativa"
        library.undo();
        assertEquals("Narrativa",library.getBooks().getLast().getGenre());

        //cambio stato di lettura de "La coscienza di Zeno" in TOREAD
        library.handle(library.createModifyStatusCommand(library.getBooks().getFirst(),ReadingStatus.TOREAD));
        assertEquals(ReadingStatus.TOREAD,library.getBooks().getFirst().getReadingStatus());
        library.undo();

        //rimozione dell'ultimo libro (il visconte dimezzato)
        library.handle(library.createRemoveCommand(library.getBooks().getLast()));
        assertEquals("Il piccolo principe",library.getBooks().getLast().getTitle());
        library.undo();

        //rimozione di un libro non presente nella libreria
        Book.Builder b = new Book.Builder("Prova","Prova","123");
        library.handle(library.createRemoveCommand(b.build()));
        assertEquals("Il visconte dimezzato",library.getBooks().getLast().getTitle());
    }

    @Test
    public void orderBooksTest(){
        //ordinamento per autore
        library.setStrategy(library.createOrderByAuthorStrategy());
        List<Book> books = library.Order(library.getBooks());
        assertEquals("Antoine de Saint-Exupéry", books.getFirst().getAuthor());
        assertEquals("Italo Svevo", books.getLast().getAuthor());

        //ordinamento per titolo
        library.setStrategy(library.createOrderByTitleStrategy());
        books = library.Order(library.getBooks());
        assertEquals("Il piccolo principe", books.getFirst().getTitle());
        assertEquals("La coscienza di Zeno", books.getLast().getTitle());

        //ordinamento per genere
        library.setStrategy(library.createOrderByGenreStrategy());
        books = library.Order(library.getBooks());
        assertEquals("Narrativa", books.getFirst().getGenre());
        assertEquals("Romanzo", books.getLast().getGenre());

        //ordinamento per status
        library.setStrategy(library.createOrderByStatusStrategy());
        books = library.Order(library.getBooks());
        assertEquals(ReadingStatus.READ, books.getFirst().getReadingStatus());
        assertEquals(ReadingStatus.TOREAD, books.getLast().getReadingStatus());
    }

    @Test
    public void searchBooksTest(){
        //ricerca per autore
        library.setStrategy(library.createSearchByAuthorStrategy());
        List<Book> books = library.searchBook(library.getBooks(),"Antoine de Saint-Exupéry");
        assertEquals("Il piccolo principe", books.getFirst().getTitle());
        assertEquals(1,books.size());

        //ricerca per titolo
        library.setStrategy(library.createSearchByTitleStrategy());
        books = library.searchBook(library.getBooks(),"La coscienza di Zeno");
        assertEquals("La coscienza di Zeno", books.getFirst().getTitle());
        assertEquals(1,books.size());

        //ricerca per ISBN
        library.setStrategy(library.createSearchByISBNStrategy());
        books = library.searchBook(library.getBooks(),"9788478441716");
        assertEquals("Il visconte dimezzato", books.getFirst().getTitle());
        assertEquals(1,books.size());
    }

    @Test
    public void filterBooksTest(){
        //filtra per genere
        library.setStrategy(library.createFilterByGenreStrategy());
        List<Book> books = library.filter(library.getBooks(),"Romanzo");
        assertEquals("La coscienza di Zeno", books.getFirst().getTitle());
        assertEquals(1,books.size());

        //ricerca per status
        library.setStrategy(library.createFilterByStatusStrategy());
        books = library.filter(library.getBooks(),"read");
        assertEquals("La coscienza di Zeno", books.getFirst().getTitle());
        assertEquals(1,books.size());
    }

    @Test
    public void persistenceBooksTest(){
        String path = "libreriaTest.json";
        try{
            library.save(library.getBooks(),path);
        }catch(IOException e){
            System.out.println("Errore nel salvataggio: "+e.getMessage());
        }
        File filetest = new File(path);
        assertTrue(filetest.length()>0);
    }
}
