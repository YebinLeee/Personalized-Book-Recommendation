package hello.hellospring.controller;

import hello.hellospring.domain.MemberInfo;
import hello.hellospring.service.BookSearchService;
import hello.hellospring.service.MemberInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.xml.parsers.ParserConfigurationException;
import java.util.List;

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
        return "redirect:/"; // 홈 화면 으로 redirect
    }

    @GetMapping("/book")
    public String bookList(Model model) {
        List<MemberInfo> memberInfos = memberInfoService.findMemberInfos();
        model.addAttribute("memberInfos", memberInfos);
        return "book/memberInfoList";
    }

    @PostMapping("/book/search")
    public String searchBooks(BookSearchQuery query) throws ParserConfigurationException {
        String result = bookSearchService.searchResultByXml(query).toString();
        System.out.println("result = " + result);
        return result;
    }

    /*
    @GetMapping("/book/result")
    public String showResult(Model model, @ModelAttribute BookSearchQuery query) {
        String jsonResult = bookSearchService.searchBooks(query).toString();
        BookSearchedResult result = bookSearchService.parseResult(jsonResult);
        model.addAttribute("result", result);
        return "book-result";
    }

     */
}