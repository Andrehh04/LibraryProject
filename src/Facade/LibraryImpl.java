package Facade;

import Command.*;
import Filtering.*;
import Management.BookManagement;
import Management.BookManagementImpl;
import Model.Book;
import Model.Rating;
import Model.ReadingStatus;
import Ordering.*;
import Persistence.BookSave;
import Persistence.BookSaveJSON;
import Researching.*;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public enum LibraryImpl {
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
    public List<Book> filter(List<Book> list, String parameter) {
        return bookFilter.filter(list, parameter);
    }


    public void setStrategy(FilterStrategy strategy){
        bookFilter.setStrategy(strategy);
    }

    public FilterStrategy createFilterByGenreStrategy(){
        return new FilterByGenre();
    }

    public FilterStrategy createFilterByStatusStrategy(){
        return new FilterByStatus();
    }


    //Metodi modulo gestione con command
    public CommandIF createAddCommand(Book book){
        return new AddCommand(book,bookManagement);
    }

    public CommandIF createRemoveCommand(Book book){
        return new RemoveCommand(book,bookManagement);
    }

    public CommandIF createModifyGenreCommand(Book book, String genre){
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
    public List<Book> Order(List<Book> list) {
        return bookOrder.Order(list);
    }

    public void setStrategy(OrderStrategy strategy){
        bookOrder.setStrategy(strategy);
    }

    public OrderStrategy createOrderByTitleStrategy(){
        return new OrderByTitle();
    }

    public OrderStrategy createOrderByAuthorStrategy(){
        return new OrderByAuthor();
    }

    public OrderStrategy createOrderByGenreStrategy(){
        return new OrderByGenre();
    }

    public OrderStrategy createOrderByStatusStrategy(){
        return new OrderByStatus();
    }


    //metodi modulo ricerca
    public List<Book> searchBook(List<Book> list, String s) {
        return bookResearch.searchBook(list, s);
    }

    public void setStrategy(ResearchStrategy strategy){
        bookResearch.setStrategy(strategy);
    }

    public ResearchStrategy createSearchByAuthorStrategy(){
        return new SearchByAuthor();
    }

    public ResearchStrategy createSearchByISBNStrategy(){
        return new SearchByISBN();
    }

    public ResearchStrategy createSearchByTitleStrategy(){
        return new SearchByTitle();
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
