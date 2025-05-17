package Researching;

import Model.Book;

import java.util.LinkedList;
import java.util.List;

public class SearchByTitle implements ResearchStrategy{
    LinkedList<Book> books = new LinkedList<>();

    @Override
    public List<Book> SearchBy(List<Book> list, String s) {
        for(Book book : list)
            if(book.getTitle().equalsIgnoreCase(s))
                books.add(book);

        return books;
    }
}
