package bookrecommendation.book.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BooksResultDto {
    private List<BookDto> books = new ArrayList<>();

    public BooksResultDto(){
    }

    public void addBook(BookDto bookDto){
        books.add(bookDto);
    }
}
