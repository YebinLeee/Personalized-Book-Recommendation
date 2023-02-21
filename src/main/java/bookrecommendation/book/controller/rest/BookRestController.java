package bookrecommendation.book.controller.rest;

import bookrecommendation.book.controller.status.RecommendResponse;
import bookrecommendation.book.controller.status.ResponseMessage;
import bookrecommendation.book.controller.status.StatusCode;
import bookrecommendation.book.dto.BooksResultDto;
import bookrecommendation.book.dto.MemberInfoRequestBodyDto;
import bookrecommendation.book.service.BookRecommendService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
public class BookRestController {
    private final BookRecommendService bookRecommendService;

    @Autowired
    public BookRestController(BookRecommendService bookRecommendService) {
        this.bookRecommendService = bookRecommendService;
    }

    @RequestMapping(value = "/recommend", method = RequestMethod.GET)
    public ResponseEntity<RecommendResponse> recommend(@RequestBody MemberInfoRequestBodyDto memberInfoRequestBodyDto){
        RecommendResponse response = new RecommendResponse();

        try {
            BooksResultDto books = bookRecommendService.getBookRecommendation(memberInfoRequestBodyDto);
            response.setCode(StatusCode.OK);
            response.setMessage(ResponseMessage.API_CALL_SUCCESS);
            response.setData(books);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
