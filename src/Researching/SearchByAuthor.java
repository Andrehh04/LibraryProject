package Researching;

import Model.Book;

import java.util.LinkedList;
import java.util.List;

public class SearchByAuthor implements ResearchStrategy{
    LinkedList<Book> books = new LinkedList<>();
    @Override
    public List<Book> SearchBy(List<Book> list, String s) {
        for(Book book: list)
            if(book.getAuthor().equalsIgnoreCase(s))
                books.add(book);

        return books;
    }
}
