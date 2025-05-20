package Facade;

import Filtering.BookFilter;
import Management.BookManagement;
import Ordering.BookOrder;
import Persistence.BookSave;
import Researching.BookResearch;

public interface Library extends BookOrder,BookResearch,BookFilter, BookSave {

}
