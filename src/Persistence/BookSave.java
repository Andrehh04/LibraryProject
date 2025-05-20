package Persistence;

import Model.Book;

import java.io.IOException;
import java.util.List;

public interface BookSave {
    public void save(List<Book> list, String path) throws IOException;
    public List<Book> load(String path) throws IOException;
}
