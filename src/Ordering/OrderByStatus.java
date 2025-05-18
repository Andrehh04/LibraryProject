package Ordering;

import Model.Book;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class OrderByStatus implements OrderStrategy{

    @Override
    public List<Book> OrderBy(List<Book> list) {
        ArrayList<Book> books = new ArrayList<Book>(list);
        books.sort(new StateComparator());
        return books;
    }

    private static class StateComparator implements Comparator<Book>{

        @Override
        public int compare(Book o1, Book o2) {
            return o1.getReadingStatus().toString().compareTo(o2.getReadingStatus().toString());
        }
    }
}
