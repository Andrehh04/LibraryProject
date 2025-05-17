package Filtering;

import Model.Book;

import java.util.LinkedList;
import java.util.List;

public class FilterByGenre implements FilterStrategy {

    @Override
    public List<Book> FilterBy(List<Book> list, String parameter) {
        LinkedList<Book> books = new LinkedList<>();
        for(Book book: list)
            if(book.getGenre().trim().equalsIgnoreCase(parameter.trim()))
                books.add(book);
        return books;
    }
}
