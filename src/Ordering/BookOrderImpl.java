package Ordering;

import Model.Book;

import java.util.List;

public class BookOrderImpl implements BookOrder {
    OrderStrategy strategy;

    public void setStrategy(OrderStrategy strategy) {
        this.strategy = strategy;
    }
    @Override
    public List<Book> Order(List<Book> list) {
        return strategy.OrderBy(list);
    }
}
