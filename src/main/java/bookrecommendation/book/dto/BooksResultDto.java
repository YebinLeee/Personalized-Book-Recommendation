package bookrecommendation.book.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BooksResultDto {
    private List<BookDto> books;

    public BooksResultDto(List<BookDto> books) {
        this.books = books;
    }

    public void addBook(BookDto bookDto){
        this.books.add(bookDto);
    }
}
