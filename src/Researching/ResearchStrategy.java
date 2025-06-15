package Researching;
import Model.Book;
import java.util.List;

public interface ResearchStrategy {
    List<Book> SearchBy(List<Book> list, String s);
}


