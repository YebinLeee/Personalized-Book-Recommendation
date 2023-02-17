package hello.hellospring.service;

import hello.hellospring.book.dto.BookSearchQuery;
import hello.hellospring.book.dto.BookSearchedResult;
import hello.hellospring.domain.Gender;

import java.util.List;

public interface BookSearchService {
    List<BookSearchedResult> searchBooksByQuery(BookSearchQuery query);
    List<BookSearchedResult> searchBooksByCategory(String interest);
    List<BookSearchedResult> searchBooksByAgeandGender(int age, Gender gender);
    List<BookSearchedResult> searchBooksByFeeling(String feeling);
}
