package Filtering;

import Model.Book;

import java.util.List;

public class BookFilterImpl implements BookFilter {
    FilterStrategy strategy;

    public void setStrategy(FilterStrategy strategy) {
        this.strategy = strategy;
    }
    @Override
    public List<Book> filter(List<Book> list,String parameter) {
        return strategy.FilterBy(list,parameter);
    }
}
