package Command;
import java.util.List;
import Model.Book;
import Management.*;
public class RemoveCommand implements CommandIF {
    private final List<Book> list;
    private final Book book;
    private final BookManagement bm;

    public RemoveCommand(List<Book> list, Book book, BookManagement bm) {
        this.list = list;
        this.book = book;
        this.bm = bm;
    }
    @Override
    public boolean doIt() {
        bm.remove(list,book);
        return true;
    }

    @Override
    public boolean undoIt() {
        bm.addBook(list,book);
        return true;
    }
}
