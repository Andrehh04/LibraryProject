package Command;
import java.util.List;
import Model.Book;
import Management.*;
public class RemoveCommand implements CommandIF {
    private final Book book;
    private final BookManagement bm;

    public RemoveCommand(Book book, BookManagement bm) {
        this.book = book;
        this.bm = bm;
    }
    @Override
    public boolean doIt() {
        if(bm.getBooks().contains(book)) {
            bm.remove(book);
            return true;
        }
        return false;
    }

    @Override
    public boolean undoIt() {
        bm.addBook(book);
        return true;
    }
}
