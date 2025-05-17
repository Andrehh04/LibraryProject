package Filtering;

import Model.Book;

import java.util.List;

public interface FilterStrategy {
    List<Book> FilterBy(List<Book> list,String parameter);
}
