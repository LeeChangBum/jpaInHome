package SpringMVC.servlet.web.frontController.v3.controller;

import SpringMVC.servlet.domain.member.Member;
import SpringMVC.servlet.domain.member.MemberRepository;
import SpringMVC.servlet.web.frontController.ModelView;
import SpringMVC.servlet.web.frontController.v3.ControllerV3;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;

public class MemberListControllerV3 implements ControllerV3 {

    private MemberRepository memberRepository=MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        List<Member> members=memberRepository.findAll();
        ModelView mv=new ModelView("members");
        mv.getModel().put("members", members);

        return mv;
    }
}
