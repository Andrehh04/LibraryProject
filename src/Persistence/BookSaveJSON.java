package Persistence;

import Model.Book;

import java.util.LinkedList;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.File;
import java.io.IOException;


public class BookSaveJSON implements BookSave{
    private final ObjectMapper mapper = new ObjectMapper();
    @Override
    public void save(List<Book> list, String path) throws IOException {
        LinkedList<BookDTO> dtoList = new LinkedList<>();
        for(Book book: list){
            dtoList.add(BookAssembler.fromBookToDTO(book));
        }
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(path),dtoList);
    }

    @Override
    public List<Book> load(String path) throws IOException {
        List<BookDTO> dtoList = mapper.readValue(new File(path), new TypeReference<List<BookDTO>>() {});
        LinkedList<Book> list = new LinkedList<>();
        for(BookDTO dto: dtoList){
            list.add(BookAssembler.fromDTOToBook(dto));
        }
        return list;
    }
}
