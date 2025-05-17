package Filtering;

import Model.Book;
import Model.ReadingStatus;

import java.util.LinkedList;
import java.util.List;

public class FilterByStatus implements FilterStrategy {

    @Override
    public List<Book> FilterBy(List<Book> list, String parameter) {
        LinkedList<Book> books = new LinkedList<>();
        ReadingStatus status=ReadingStatus.NONE;
        for(ReadingStatus rs : ReadingStatus.values()) {
            if (rs.toString().replaceAll("\\s+", "").equalsIgnoreCase(parameter.replaceAll("\\s+", ""))) {
                status = rs;
                break;
            }
        }
        for (Book book : list)
            if(book.getReadingStatus().equals(status))
                books.add(book);
        return books;
    }
}
