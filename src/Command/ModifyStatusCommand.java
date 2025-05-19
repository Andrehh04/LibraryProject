package Command;
import Model.*;
import Management.*;
public class ModifyStatusCommand implements CommandIF{
    BookManagement bm;
    Book book;
    ReadingStatus newStatus;
    ReadingStatus oldStatus;

    public ModifyStatusCommand(BookManagement bm, Book book, ReadingStatus newStatus) {
        this.bm = bm;
        this.book = book;
        this.newStatus = newStatus;
        oldStatus = book.getReadingStatus();
    }
    @Override
    public boolean doIt() {
        bm.modifyStatus(book, newStatus);
        return true;
    }

    @Override
    public boolean undoIt() {
        bm.modifyStatus(book, oldStatus);
        return true;
    }
}
