package Ordering;

import Model.Book;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class OrderByTitle implements OrderStrategy{

    @Override
    public List<Book> OrderBy(List<Book> list) {
        ArrayList<Book> books = new ArrayList<Book>(list);
        books.sort(new OrderComparator());
        return books;
    }

    private static class OrderComparator implements Comparator<Book> {

        @Override
        public int compare(Book o1, Book o2) {
            return o1.getTitle().compareTo(o2.getTitle());
        }
    }
}
