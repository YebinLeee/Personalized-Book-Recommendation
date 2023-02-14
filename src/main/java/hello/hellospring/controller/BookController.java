package hello.hellospring.controller;

import hello.hellospring.book.dto.BookSearchQuery;
import hello.hellospring.book.dto.BookSearchedResult;
import hello.hellospring.book.form.BookRecomForm;
import hello.hellospring.domain.MemberInfo;
import hello.hellospring.service.BookSearchService;
import hello.hellospring.service.MemberInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Transactional
@Controller
public class BookController {

    private final MemberInfoService memberInfoService;
    private final BookSearchService bookSearchService;

    public BookController(MemberInfoService memberInfoService, BookSearchService bookSearchService) {
        this.memberInfoService = memberInfoService;
        this.bookSearchService = bookSearchService;
    }

    @GetMapping("/book/new")
    public String createMemberInfoForm() {
        return "book/createBookRecom";
    }


    @PostMapping("/book/new")
    public String createMemberInfo(BookRecomForm form) {
        MemberInfo memberInfo = new MemberInfo();

        memberInfo.setName(form.getName());
        memberInfo.setAge(form.getAge());
        memberInfo.setGender(form.getGender());
        memberInfo.setInterest(form.getInterest());
        memberInfo.setFeeling(form.getFeeling());

        memberInfoService.join(memberInfo);
        return "redirect:/";
    }

    @GetMapping("/book")
    public String bookList(Model model) {
        List<MemberInfo> memberInfos = memberInfoService.findMemberInfos();
        model.addAttribute("memberInfos", memberInfos);
        return "book/memberInfoList";
    }


    @RequestMapping(value = "/book/result", method = RequestMethod.GET)
    public String showResult(Model model, BookSearchQuery query) {
        List<BookSearchedResult> results = bookSearchService.searchBooks(query);
        model.addAttribute("results", results);
        return "book/result";
    }
}