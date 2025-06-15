package Persistence;
import Model.Book;
public class BookAssembler {
    public static Book fromDTOToBook(BookDTO dto) {
        Book.Builder builder = new Book.Builder(dto.getTitle(),dto.getAuthor(),dto.getIsbn());

        return builder.setGenre(dto.getGenre()).setRating(dto.getRating()).
                setReadingStatus(dto.getReadingStatus()).build();
    }

    public static BookDTO fromBookToDTO(Book book) {
        BookDTO dto = new BookDTO();
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setIsbn(book.getIsbn());
        dto.setGenre(book.getGenre());
        dto.setRating(book.getRating());
        dto.setReadingStatus(book.getReadingStatus());
        return dto;
    }
}


