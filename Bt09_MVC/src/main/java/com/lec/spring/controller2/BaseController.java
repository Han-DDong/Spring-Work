package com.lec.spring.controller2;

import com.lec.spring.common.U;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;

@Controller
public class BaseController {
    @RequestMapping("/member/search")
    @ResponseBody
    public String searchMember() {
        System.out.println("/member/search  searchMember() 호출");
        return "searchMember() 호출";
    }

    // ModelAndView 객체 사용
    // '뷰(View)' 와 '데이터(Model)' 을 둘다 -> ModelAndView 에 세팅
    @RequestMapping(value = "/member/find")
    public ModelAndView findMember() {
        ModelAndView mv = new ModelAndView();

        mv.addObject("mbName", "한정우");
        mv.addObject("mbDate", LocalDateTime.now());
        mv.setViewName("member/find"); // setViewName ➡️ 사용자에게 "member/find.html을 찾아서 보여줌

        return mv;
    }

    // wildcard *, ** 사용 가능
    @RequestMapping("/member/action/*") // /member/action/ 다음에 단일 경로 요소가 있는 모든 요청을 처리함.
    @ResponseBody
    public String memberAction1(HttpServletRequest request) {
        return U.requestInfo(request);
    }

    @RequestMapping("/member/action/*/*") // /member/action/ 다음에 두 개의 경로 요소가 있는 모든 요청을 처리함.
    @ResponseBody
    public String memberAction2(HttpServletRequest request) {
        return U.requestInfo(request);
    }

    @RequestMapping("/member/action/*/*/**") // /member/action/ 다음에 두 개의 경로 요소와 그 뒤에 어떤 경로가 더 붙어도 됨.
    @ResponseBody
    public String memberAction3(HttpServletRequest request) {
        return U.requestInfo(request);
    }

    // 확장자 패턴 사용 가능
    @RequestMapping("/member/*.do") // /member/ 다음에 어떤 이름이 오고 .do 확장자로 끝나는 모든 요청을 처리함.
    @ResponseBody
    public String doMember(HttpServletRequest request){
        return U.requestInfo(request);
    }

} // end BaseController
