import Model.Book;
import Model.Rating;
import Model.ReadingStatus;
import Ordering.*;

import java.util.LinkedList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Book.Builder b1 = new Book.Builder("Libro1","Autore1","123");
        b1.setGenre("Genre1").setRating(Rating.CINQUE).setReadingStatus(ReadingStatus.TOREAD);
        Book.Builder b2 = new Book.Builder("Libro2","Autore2","456");
        b2.setGenre("Genre1").setRating(Rating.CINQUE).setReadingStatus(ReadingStatus.READ);
        Book.Builder b3 = new Book.Builder("Libro3","Autore2","789");
        b3.setGenre("Genre3").setRating(Rating.CINQUE).setReadingStatus(ReadingStatus.READING);
        Book.Builder b4 = new Book.Builder("Libro4","Autore4","987");
        b4.setGenre("Genre4").setRating(Rating.CINQUE).setReadingStatus(ReadingStatus.READ);
        Book.Builder b5 = new Book.Builder("Libro5","Autore4","654");
        b5.setGenre("Genre5").setRating(Rating.CINQUE).setReadingStatus(ReadingStatus.TOREAD);
        Book.Builder b6 = new Book.Builder("Libro6","Autore3","321");
        b6.setGenre("Genre6").setRating(Rating.CINQUE).setReadingStatus(ReadingStatus.READING);

        Book book1 = b1.build();
        Book book2 = b2.build();
        Book book3 = b3.build();
        Book book4 = b4.build();
        Book book5 = b5.build();
        Book book6 = b6.build();

        LinkedList<Book> books = new LinkedList<>();
        books.add(book3);
        books.add(book5);
        books.add(book1);
        books.add(book6);
        books.add(book2);
        books.add(book4);

        OrderStrategy strategy1 = new OrderByStatus();
        OrderStrategy strategy2 = new OrderByAuthor();
        OrderStrategy strategy3 = new OrderByGenre();
        OrderStrategy strategy4 = new OrderByTitle();

        System.out.println("Lista non ordinata:\n");
        for(Book b: books){
            System.out.println(b+"\n");
        }
        System.out.println("\nLista ordinata:\n");
        BookOrderImpl order = new BookOrderImpl();
        for(Book b: order.Order(books)){
            System.out.println(b+"\n");
        }


    }
}