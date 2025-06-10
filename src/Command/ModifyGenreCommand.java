package Command;

import Model.Book;
import Management.*;

public class ModifyGenreCommand implements CommandIF{
    BookManagement bm;
    Book book;
    String newGenre;
    String oldGenre;

    public ModifyGenreCommand(BookManagement bm, Book book, String newGenre) {
        this.bm = bm;
        this.book = book;
        this.newGenre = newGenre;
        oldGenre = book.getGenre();
    }

    @Override
    public boolean doIt() {
        if(bm.getBooks().contains(book)) {
            bm.modifyGenre(book, newGenre);
            return true;
        }
        return false;
    }

    @Override
    public boolean undoIt() {
        bm.modifyGenre(book, oldGenre);
        return true;
    }
}
