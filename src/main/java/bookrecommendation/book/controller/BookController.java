package bookrecommendation.book.controller;

import bookrecommendation.book.dto.BookSearchQuery;
import bookrecommendation.book.dto.BookDto;
import bookrecommendation.book.form.BookRecomForm;
import bookrecommendation.book.domain.MemberInfo;
import bookrecommendation.book.service.BookService;
import bookrecommendation.book.service.MemberInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Transactional
@Controller
public class BookController {

    private final MemberInfoService memberInfoService;
    private final BookService bookService;

    @Autowired
    public BookController(MemberInfoService memberInfoService, BookService bookService) {
        this.memberInfoService = memberInfoService;
        this.bookService = bookService;
    }

    @GetMapping("/book/new")
    public String createMemberInfoForm() {
        return "book/createBookRecom";
    }


    @RequestMapping(value = "/book/search", method = RequestMethod.GET)
    public String createMemberInfo(Model model, BookRecomForm form) {
        MemberInfo memberInfo = new MemberInfo();

        memberInfo.setName(form.getName());
        memberInfo.setAge(form.getAge());
        memberInfo.setGender(form.getGender());
        memberInfo.setInterest(form.getInterest());
        memberInfo.setFeeling(form.getFeeling());

        try {
            memberInfoService.join(memberInfo);
        }catch(IllegalStateException e){
        }
        model.addAttribute("member", form);

        List<BookDto> results = bookService.searchByCategory(form.getInterest());
        model.addAttribute("results", results);
        return "book/result";
    }

    @GetMapping("/book")
    public String bookList(Model model) {
        List<MemberInfo> memberInfos = memberInfoService.findMemberInfos();
        model.addAttribute("memberInfos", memberInfos);
        return "book/memberInfoList";
    }


    @RequestMapping(value = "/book/result", method = RequestMethod.GET)
    public String showResult(Model model, BookSearchQuery query) {
        List<BookDto> results = bookService.searchByQuery(query);
        model.addAttribute("results", results);
        return "book/result";
    }
}