package Filtering;

import Model.Book;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class FilterByGenre implements FilterStrategy {

    @Override
    public List<Book> FilterBy(List<Book> list, String parameter) {
        if (parameter==null || parameter.equals("") || list==null){
            return Collections.emptyList();
        }

        LinkedList<Book> books = new LinkedList<>();
        for(Book book: list)
            if(book.getGenre().replaceAll("\\s+", "").equalsIgnoreCase(parameter.replaceAll("\\s+", "")))
                books.add(book);
        return books;
    }
}
