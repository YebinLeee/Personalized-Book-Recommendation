package bookrecommendation.book.controller;

import bookrecommendation.book.form.MemberConfirmForm;
import bookrecommendation.book.form.MemberForm;
import bookrecommendation.book.domain.Gender;
import bookrecommendation.book.domain.Member;
import bookrecommendation.book.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Transactional
@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping(value = "/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping(value = "/members/new")
    public String create(MemberForm form){
        Member member = new Member();

        member.setName(form.getName());
        member.setPassword(form.getPassword());
        member.setRfid(form.getRfid());
        member.setBarcode(form.getBarcode());
        member.setAge(form.getAge());

        if(form.getGender().equals("male")){
            member.setGender(Gender.MALE);
        }
        else{
            member.setGender(Gender.FEMALE);
        }

        memberService.join(member);
        return "redirect:/"; // 홈 화면 으로 redirect
    }

    @GetMapping(value = "/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }

    @GetMapping(value = "/members/confirm")
    public String confirm(){
        return "members/memberConfirmForm";
    }

    @PostMapping(value = "/members/confirm")
    public String confirm(MemberConfirmForm confirmForm, Model model){
        String rfid = confirmForm.getRfid();
        String barcode = confirmForm.getBarcode();

        Optional<Member> getMember1 = memberService.validateByRfid(rfid);
        Optional<Member> getMember2 = memberService.validateByBarcode(barcode);

        if(getMember1.isPresent()){
            model.addAttribute("member", getMember1.get());
            return "members/welcome";
        }
        else if(getMember2.isPresent()){
            model.addAttribute("member", getMember2.get());
            return "members/welcome";
        }
        else{
            model.addAttribute("msg", "회원 인증을 다시 시도해주세요!");
            return "members/memberConfirmForm";
        }
    }
}
