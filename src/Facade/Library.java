package Facade;

import Filtering.BookFilter;
import Ordering.BookOrder;
import Researching.BookResearch;

public interface Library extends BookOrder,BookResearch,BookFilter {

}
