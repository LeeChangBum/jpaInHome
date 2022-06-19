package SpringMVC.servlet.web.springmvc.v2;

import SpringMVC.servlet.domain.member.Member;
import SpringMVC.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/springmvc/v2/members")//여기에 적으면 밑 @RequestMapping에 모두 적용됨
public class SpringMemberControllerV2 {

    private MemberRepository memberRepository=MemberRepository.getInstance();

    @RequestMapping("/new-form")
    public ModelAndView process(){
        return new ModelAndView("new-form");
    }

    @RequestMapping("/save")
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        Member member=new Member(username,age);
        memberRepository.save(member);

        ModelAndView mv=new ModelAndView("save-result");
        mv.addObject("member", member);
        return mv;
    }

    @RequestMapping
    public ModelAndView members() {

        List<Member> members=memberRepository.findAll();

        ModelAndView mv=new ModelAndView("members");
        mv.addObject("members", members);

        return mv;
    }
}
