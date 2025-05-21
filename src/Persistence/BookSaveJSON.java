package Persistence;

import Model.Book;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.File;
import java.io.IOException;


public class BookSaveJSON implements BookSave{
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void save(List<Book> list, String path){
        try{
            LinkedList<BookDTO> dtoList = new LinkedList<>();
            for(Book book: list){
                dtoList.add(BookAssembler.fromBookToDTO(book));
            }
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(path),dtoList);
        }catch(IOException e){
            System.out.println("Errore nel salvataggio: "+e.getMessage());
        }

    }

    @Override
    public List<Book> load(String path){
        try {
            List<BookDTO> dtoList = mapper.readValue(new File(path), new TypeReference<List<BookDTO>>() {
            });
            LinkedList<Book> list = new LinkedList<>();
            for(BookDTO dto: dtoList){
                list.add(BookAssembler.fromDTOToBook(dto));
            }
            return list;
        }catch (IOException e){
            System.out.println("Errore nel caricamento: "+e.getMessage());
            return Collections.emptyList();
        }

    }
}
