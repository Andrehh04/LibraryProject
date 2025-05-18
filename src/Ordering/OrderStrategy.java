package Ordering;
import Model.*;
import java.util.List;
public interface OrderStrategy {
    public List<Book> OrderBy(List<Book> list);
}
