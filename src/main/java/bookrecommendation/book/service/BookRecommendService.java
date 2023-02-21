package bookrecommendation.book.service;

import bookrecommendation.book.domain.Gender;
import bookrecommendation.book.dto.BooksResultDto;
import bookrecommendation.book.dto.MemberInfoRequestBodyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookRecommendService {
    private final BookService bookService;

    private final int numberOfBooks;
    private final BooksResultDto booksResultDto;


    @Autowired
    public BookRecommendService(BookService bookService) {
        this.bookService = bookService;
        this.numberOfBooks = 2;
        booksResultDto = new BooksResultDto();
    }

    public BooksResultDto getBookRecommendation(MemberInfoRequestBodyDto memberInfo){
        int age = memberInfo.getAge();
        Gender gender = memberInfo.getGender();
        List<String> feelings = memberInfo.getFeelings();
        String interest = memberInfo.getInterest();

        for(int i=0;i<numberOfBooks;i++) {
            booksResultDto.addBook(bookService.searchByAgeAndGender(age, gender).get(i));
            booksResultDto.addBook(bookService.searchByFeeling(feelings).get(i));
            booksResultDto.addBook(bookService.searchByInterest(interest).get(i));
        }
        return booksResultDto;
    }




}
