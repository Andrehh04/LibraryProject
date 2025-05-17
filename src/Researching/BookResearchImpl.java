package Researching;

import Model.Book;

import java.util.LinkedList;
import java.util.List;

public class BookResearchImpl implements BookResearch {
    ResearchStrategy strategy;
    List<Book> books = new LinkedList<>();

    public BookResearchImpl(ResearchStrategy strategy) {
        this.strategy=strategy;
    }

    @Override
    public List<Book> searchBook(List<Book> list, String s) {
        books.addAll(strategy.SearchBy(list, s));
        return books;
    }
}
