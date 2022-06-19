package SpringMVC.servlet.web.frontController.v4.controller;

import SpringMVC.servlet.domain.member.Member;
import SpringMVC.servlet.domain.member.MemberRepository;
import SpringMVC.servlet.web.frontController.ModelView;
import SpringMVC.servlet.web.frontController.v3.ControllerV3;
import SpringMVC.servlet.web.frontController.v4.ControllerV4;

import java.util.List;
import java.util.Map;

public class MemberListControllerV4 implements ControllerV4 {

    private MemberRepository memberRepository=MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> paramMap,Map<String, Object> model) {
        List<Member> members=memberRepository.findAll();
        model.put("members", members);

        return "members";
    }
}
