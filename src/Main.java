import Command.AddCommand;
import Command.CommandIF;
import Facade.Library;
import Facade.LibraryImpl;
import Model.Book;
import Model.Rating;
import Model.ReadingStatus;
import Ordering.*;

import java.util.LinkedList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
       /*
        Book.Builder b1 = new Book.Builder("Libro1","Autore1","123");
        b1.setGenre("Genre1").setRating(Rating.CINQUE).setReadingStatus(ReadingStatus.TOREAD);
        Book book1=b1.build();
        Book.Builder b2 = new Book.Builder("Libro2","Autore2","456");
        b2.setGenre("Genre1").setRating(Rating.CINQUE).setReadingStatus(ReadingStatus.READ);
        Book book2=b2.build();
        Book.Builder b3 = new Book.Builder("Libro3","Autore2","789");
        b3.setGenre("Genre3").setRating(Rating.CINQUE).setReadingStatus(ReadingStatus.READING);
        Book book3=b3.build();
        Book.Builder b4 = new Book.Builder("Libro4","Autore4","987");
        b4.setGenre("Genre4").setRating(Rating.CINQUE).setReadingStatus(ReadingStatus.READ);
        Book book4=b4.build();
        Book.Builder b5 = new Book.Builder("Libro5","Autore4","654");
        b5.setGenre("Genre5").setRating(Rating.CINQUE).setReadingStatus(ReadingStatus.TOREAD);
        Book book5=b5.build();
        Book.Builder b6 = new Book.Builder("Libro6","Autore3","321");
        b6.setGenre("Genre6").setRating(Rating.CINQUE).setReadingStatus(ReadingStatus.READING);
        Book book6=b6.build();

        CommandIF cmd1 = LibraryImpl.INSTANCE.createAddCommand(book1);
        CommandIF cmd2 = LibraryImpl.INSTANCE.createAddCommand(book2);
        CommandIF cmd3 = LibraryImpl.INSTANCE.createAddCommand(book3);
        CommandIF cmd4 = LibraryImpl.INSTANCE.createAddCommand(book4);
        CommandIF cmd5 = LibraryImpl.INSTANCE.createAddCommand(book5);
        CommandIF cmd6 = LibraryImpl.INSTANCE.createAddCommand(book6);

        LibraryImpl.INSTANCE.handle(cmd1);
        LibraryImpl.INSTANCE.handle(cmd2);
        LibraryImpl.INSTANCE.handle(cmd3);
        LibraryImpl.INSTANCE.handle(cmd4);
        LibraryImpl.INSTANCE.handle(cmd5);
        LibraryImpl.INSTANCE.handle(cmd6);

        System.out.println(LibraryImpl.INSTANCE.getBooks());

        LibraryImpl.INSTANCE.undo();
        System.out.println("\nDopo l'undo\n");
        System.out.println(LibraryImpl.INSTANCE.getBooks());
        LibraryImpl.INSTANCE.redo();
        System.out.println("\nDopo il redo\n");
        System.out.println(LibraryImpl.INSTANCE.getBooks());

        CommandIF cmd7 = LibraryImpl.INSTANCE.createRemoveCommand(book3);
        LibraryImpl.INSTANCE.handle(cmd7);
        System.out.println("\nDopo il remove\n");
        System.out.println(LibraryImpl.INSTANCE.getBooks());
        LibraryImpl.INSTANCE.undo();
        System.out.println("\nDopo l'undo del remove\n");
        System.out.println(LibraryImpl.INSTANCE.getBooks());

        try{
            LibraryImpl.INSTANCE.save(LibraryImpl.INSTANCE.getBooks(), "libreriafile.json");
        }catch (Exception e){
            System.out.println("Errore durante il salvataggio: "+e.getMessage());
        }*/
        System.out.println("Prima del caricamento:\n ");
        System.out.println(LibraryImpl.INSTANCE.getBooks());
        try{
            LibraryImpl.INSTANCE.load("libreriafile.json");
        }catch(Exception e){
            System.out.println("Errore durante il caricamento: "+e.getMessage());
        }
        System.out.println("\nDopo il caricamento:\n ");
        System.out.println(LibraryImpl.INSTANCE.getBooks());


    }
}