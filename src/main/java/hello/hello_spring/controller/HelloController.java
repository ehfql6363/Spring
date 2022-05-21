package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "spring!!");
        //data라는 이름에 hello!!라는 값이 들어감
        //data = hello!!라는 식?
        return "hello";
        // resources\templates\파일 이름(=ViewName).html 을 리턴하여 랜더링하게 함.
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody //http body부분에 직접 접근하(넣)기 위해 사용
    public String helloString(@RequestParam("name") String name){
        return "hello "+name; //문자열 그대로 출력(html태그가 없음)
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello(); //Hello객체 생성
        hello.setName(name);
        return hello; //객체를 반환 = JSON형식으로 출력
    }

    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
