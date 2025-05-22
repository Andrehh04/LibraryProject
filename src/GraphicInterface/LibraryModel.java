package GraphicInterface;

import javax.swing.table.AbstractTableModel;

import Facade.LibraryImpl;
import Model.Book;
import java.util.*;

public class LibraryModel extends AbstractTableModel {
    List<Book> books;
    String[] columns = {"Title","Author","ISBN","Genre","Status","Rating"};

    public LibraryModel(){
        this.books = LibraryImpl.INSTANCE.getBooks();
    }

    @Override
    public int getRowCount() {
        return books.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Book book = books.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> book.getTitle();
            case 1 -> book.getAuthor();
            case 2 -> book.getIsbn();
            case 3 -> book.getGenre();
            case 4 -> book.getReadingStatus();
            case 5 -> book.getRating();
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    public void setBooks(List<Book> books) {
        this.books = books;
        refresh();
    }

    public void refresh() {
        fireTableDataChanged();
    }
}
