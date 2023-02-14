package hello.hellospring.book.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookSearchDto {
    private List<BookSearchedResult> books;

    public BookSearchDto(List<BookSearchedResult> books) {
        this.books = books;
    }

    public void addBook(BookSearchedResult bookSearchedResult){
        this.books.add(bookSearchedResult);
    }
}
