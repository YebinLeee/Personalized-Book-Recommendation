package bookrecommendation.book.controller.rest;

import bookrecommendation.book.dto.BooksResultDto;
import bookrecommendation.book.dto.MemberInfoRequestBodyDto;
import bookrecommendation.book.service.BookService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
public class BookRestController {
    private final BookService bookService;

    @Autowired
    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(value = "/book/recommendation", method = RequestMethod.POST)
    public ResponseEntity<BooksResultDto> recommend(@RequestBody MemberInfoRequestBodyDto memberInfoRequestBodyDto){
        return null;
    }
}
