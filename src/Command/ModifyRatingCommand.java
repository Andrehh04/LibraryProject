package Command;
import Management.BookManagement;
import Model.*;
public class ModifyRatingCommand implements CommandIF{
    BookManagement bm;
    Book book;
    Rating newRating;
    Rating oldRating;

    public ModifyRatingCommand(BookManagement bm, Book book, Rating newRating) {
        this.bm = bm;
        this.book=book;
        this.newRating=newRating;
        oldRating=book.getRating();
    }
    @Override
    public boolean doIt() {
        if(bm.getBooks().contains(book)) {
            bm.modifyRating(book, newRating);
            return true;
        }
        return false;
    }

    @Override
    public boolean undoIt() {
        bm.modifyRating(book, oldRating);
        return true;
    }
}
