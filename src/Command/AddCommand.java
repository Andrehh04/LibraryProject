package Command;
import java.util.List;

import Management.BookManagement;
import Model.Book;

public class AddCommand implements CommandIF {
    private final Book book;
    private final List<Book> list;
    private final BookManagement bm;
    public AddCommand(List<Book> list, Book book, BookManagement bm) {
        this.book = book;
        this.list = list;
        this.bm = bm;
    }
    @Override
    public boolean doIt() {
        bm.addBook(list, book);
        return true;
    }

    @Override
    public boolean undoIt() {
        bm.remove(list, book);
        return true;
    }
}
