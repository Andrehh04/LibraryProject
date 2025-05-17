package Researching;

import Model.Book;

import java.util.LinkedList;
import java.util.List;

public class SearchByISBN implements ResearchStrategy{

    @Override
    public List<Book> SearchBy(List<Book> list, String s) {
        LinkedList<Book> books = new LinkedList<>();
        for (Book book : list)
            if(book.getIsbn().equalsIgnoreCase(s))
                books.add(book);

        return books;
    }
}
