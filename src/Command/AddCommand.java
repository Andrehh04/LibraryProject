package Command;

import Management.BookManagement;
import Model.Book;

public class AddCommand implements CommandIF {
    private final Book book;
    private final BookManagement bm;
    public AddCommand(Book book, BookManagement bm) {
        this.book = book;
        this.bm = bm;
    }
    @Override
    public boolean doIt() {
        bm.addBook(book);
        return true;
    }

    @Override
    public boolean undoIt() {
        bm.remove(book);
        return true;
    }
}
