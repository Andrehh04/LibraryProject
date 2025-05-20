package Filtering;

import Model.Book;

import java.util.List;
import java.util.Objects;

public interface BookFilter {
    List<Book> filter(List<Book> list,String parameter);
    void setStrategy(FilterStrategy strategy);
}
