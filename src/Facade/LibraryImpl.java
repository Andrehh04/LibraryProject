package Facade;

import Command.*;
import Filtering.BookFilter;
import Filtering.BookFilterImpl;
import Management.BookManagement;
import Management.BookManagementImpl;
import Model.Book;
import Model.Rating;
import Model.ReadingStatus;
import Ordering.BookOrder;
import Ordering.BookOrderImpl;
import Persistence.BookSave;
import Persistence.BookSaveJSON;
import Researching.BookResearch;
import Researching.BookResearchImpl;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public enum LibraryImpl implements Library {
    INSTANCE;
    final BookManagement bookManagement;
    final BookOrder bookOrder;
    final BookResearch bookResearch;
    final BookFilter bookFilter;
    BookSave bookSave;
    final CommandHandler commandHandler;

    LibraryImpl(){
        bookManagement = new BookManagementImpl();
        bookOrder = new BookOrderImpl();
        bookResearch = new BookResearchImpl();
        bookFilter = new BookFilterImpl();
        commandHandler = new CommandHandler();
        bookSave = new BookSaveJSON();
    }
    @Override
    public List<Book> filter(List<Book> list, String parameter) {
        return bookFilter.filter(list, parameter);
    }

    @Override
    public List<Book> getBooks(){
        return bookManagement.getBooks();
    }

    @Override
    public void addBook(Book book) {
        CommandIF command = new AddCommand(book,bookManagement);
        commandHandler.handle(command);
    }

    @Override
    public Book remove(Book book) {
        CommandIF command = new RemoveCommand(book,bookManagement);
        commandHandler.handle(command);
        return book;
    }

    @Override
    public void modifyGenre(Book book, String genre) {
        CommandIF command = new ModifyGenreCommand(bookManagement,book,genre);
        commandHandler.handle(command);
    }

    @Override
    public void modifyRating(Book book, Rating rating) {
        CommandIF command = new ModifyRatingCommand(bookManagement,book,rating);
        commandHandler.handle(command);
    }

    @Override
    public void modifyStatus(Book book, ReadingStatus status) {
        CommandIF command = new ModifyStatusCommand(bookManagement,book,status);
        commandHandler.handle(command);
    }

    @Override
    public List<Book> Order(List<Book> list) {
        return bookOrder.Order(list);
    }

    @Override
    public List<Book> searchBook(List<Book> list, String s) {
        return bookResearch.searchBook(list, s);
    }

    public void undo(){
        commandHandler.undo();
    }

    public void redo(){
        commandHandler.redo();
    }

    @Override
    public void save(List<Book> list, String path) throws IOException {
        try{
            bookSave.save(list,path);
        }catch (IOException e){
            System.out.println("Errore durante il salvataggio: "+e.getMessage());
        }
    }

    @Override
    public List<Book> load(String path) throws IOException {
        try{
            return bookSave.load(path);
        }catch (IOException e){
            System.out.println("Errore durante il caricamento del file: "+e.getMessage());
            return Collections.emptyList();
        }
    }
}
