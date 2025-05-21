package Facade;

import Command.*;
import Filtering.BookFilter;
import Filtering.BookFilterImpl;
import Filtering.FilterStrategy;
import Management.BookManagement;
import Management.BookManagementImpl;
import Model.Book;
import Model.Rating;
import Model.ReadingStatus;
import Ordering.BookOrder;
import Ordering.BookOrderImpl;
import Ordering.OrderStrategy;
import Persistence.BookSave;
import Persistence.BookSaveJSON;
import Researching.BookResearch;
import Researching.BookResearchImpl;
import Researching.ResearchStrategy;

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
    final BookSave bookSave;
    final CommandHandler commandHandler;

    LibraryImpl(){
        bookManagement = new BookManagementImpl();
        bookOrder = new BookOrderImpl();
        bookResearch = new BookResearchImpl();
        bookFilter = new BookFilterImpl();
        commandHandler = new CommandHandler();
        bookSave = new BookSaveJSON();
    }

    //Metodi modulo filtraggio
    @Override
    public List<Book> filter(List<Book> list, String parameter) {
        return bookFilter.filter(list, parameter);
    }

    @Override
    public void setStrategy(FilterStrategy strategy){
        bookFilter.setStrategy(strategy);
    }

    //Metodi modulo gestione con command
    public CommandIF createAddCommand(Book book){
        return new AddCommand(book,bookManagement);
    }

    public CommandIF createRemoveCommand(Book book){
        return new RemoveCommand(book,bookManagement);
    }

    public CommandIF createModyfyGenreCommand(Book book, String genre){
        return new ModifyGenreCommand(bookManagement,book,genre);
    }

    public CommandIF createModifyRatingCommand(Book book, Rating rating){
        return new ModifyRatingCommand(bookManagement,book,rating);
    }

    public CommandIF createModifyStatusCommand(Book book, ReadingStatus status){
        return new ModifyStatusCommand(bookManagement,book,status);
    }

    public List<Book> getBooks(){
        return bookManagement.getBooks();
    }

    //metodi modulo ordinamento
    @Override
    public List<Book> Order(List<Book> list) {
        return bookOrder.Order(list);
    }

    @Override
    public void setStrategy(OrderStrategy strategy){
        bookOrder.setStrategy(strategy);
    }

    //metodi modulo ricerca
    @Override
    public List<Book> searchBook(List<Book> list, String s) {
        return bookResearch.searchBook(list, s);
    }

    @Override
    public void setStrategy(ResearchStrategy strategy){
        bookResearch.setStrategy(strategy);
    }

    //metodi command handler
    public void handle(CommandIF command){
        commandHandler.handle(command);
    }

    public void undo(){
        commandHandler.undo();
    }

    public void redo(){
        commandHandler.redo();
    }

    //metodi modulo persistenza
    public void save(List<Book> list, String path) throws IOException {
        bookSave.save(list,path);
    }

    public void load(String path) throws IOException {
        for(Book book:bookSave.load(path)){
            bookManagement.addBook(book);
        }
    }
}
