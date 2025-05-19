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
import Researching.BookResearch;
import Researching.BookResearchImpl;

import java.util.LinkedList;
import java.util.List;

public enum LibraryImpl implements Library {
    INSTANCE;
    final BookManagement bookManagement;
    final BookOrder bookOrder;
    final BookResearch bookResearch;
    final BookFilter bookFilter;
    //BookSave bookSave;
    final CommandHandler commandHandler;

    LibraryImpl(){
        bookManagement = new BookManagementImpl();
        bookOrder = new BookOrderImpl();
        bookResearch = new BookResearchImpl();
        bookFilter = new BookFilterImpl();
        commandHandler = new CommandHandler();
    }
    @Override
    public List<Book> filter(List<Book> list, String parameter) {
        return bookFilter.filter(list, parameter);
    }

    @Override
    public void addBook(List<Book> list, Book book) {
        CommandIF command = new AddCommand(list,book,bookManagement);
        commandHandler.handle(command);
    }

    @Override
    public Book remove(List<Book> list, Book book) {
        CommandIF command = new RemoveCommand(list,book,bookManagement);
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
}
