package CBLEE.CBLEEspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("Hello")
    public String hello(Model model)
    {
        model.addAttribute("data","hello!!");//key: data, value:hello!!
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMVC(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody//HTTP body에 return으로 데이터를 직접 넣어주곘다는 의미, 즉 view를 거치지 않음(객체는 JSON 형식으로 준다.)
    public String helloString(@RequestParam("name") String name)
    {
        return "hello"+name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name)
    {
        Hello hello=new Hello();
        hello.setName(name);
        return hello;
    }


    static class Hello{
        private String name;
        public String getName(){
            return name;
        }

        public void setName(String name){
            this.name=name;
        }
    }

}
