package Researching;

import Model.Book;

import java.util.List;

public class BookResearchImpl implements BookResearch {
    ResearchStrategy strategy;

    @Override
    public void setStrategy(ResearchStrategy strategy) {
        this.strategy=strategy;
    }

    @Override
    public List<Book> searchBook(List<Book> list, String s) {
        return strategy.SearchBy(list, s);
    }
}
