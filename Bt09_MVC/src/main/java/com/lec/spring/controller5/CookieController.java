package com.lec.spring.controller5;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// 주의사항!
// 만약 핸들러에서 HttpServletResponse 객체를 건드렸으면
// 해당 핸들러는 반드시 String 을 리턴하여 (View 나 redirect 가 될수 있도록 하자)
@Controller
@RequestMapping("/cookie")
public class CookieController {

    // 클라이언트로부터 온 request 안의 cookie 정보들 조회
    @RequestMapping("/list")
    public void list(HttpServletRequest request, Model model){
        // 클라이언트 안의 쿠키 정보는 request 시에 서버로 전달된다.
        // request.getCookies() 로 쿠키 받아올수 있다.
        Cookie[] cookies = request.getCookies(); // getCookies 는 Cookie 를 리턴

        StringBuffer buff = new StringBuffer();

        if (cookies != null) { // 쿠키가 하나도 없다면 getCookies() 는 null 을 리턴

            for (int i = 0; i < cookies.length; i++) {
                // Cookie 는 name-value 쌍으로 이루어진 데이터 (name, value 는 모두 String)
                String name = cookies[i].getName(); // 쿠키 '이름'
                String value = cookies[i].getValue(); // 쿠키 '값'

                buff.append((i + i) + "] " + name + " : " + value + "<br>");
            }

        } else {
            buff.append("쿠키가 없습니다<br>");
        }

        model.addAttribute("result", buff.toString());

    }


    // 쿠키 생성 절차
    // 1. 쿠키(Cookie) 클래스로 생성
    // 2. 쿠키속성 설정(setter)
    // 3. 쿠키의 전송 (response 객체에 탑재:addCookie())
    @RequestMapping("/create")
    public String create(HttpServletResponse response){
        String cookieName1 = "num1";
        String cookieValue1 = "" + (int)(Math.random() * 10);
        Cookie cookie1 = new Cookie(cookieName1, cookieValue1); // name - value 쌍으로 Cookie 생성
        cookie1.setMaxAge(30); // 쿠키 파기(expiry) 시간 설정 (생성 시점으로부터 30초 후 파기)
        response.addCookie(cookie1); // reponse 에 Cookie 추가

        // 쿠키는 여러개 생성가능
        String cookieName2 = "datetime";
        String cookieValue2 = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        Cookie cookie2 = new Cookie(cookieName2, cookieValue2);
        cookie2.setMaxAge(20);
        response.addCookie(cookie2);


        return "redirect:/cookie/list";
    }


    @RequestMapping("/delete")
    public String delete(HttpServletResponse response){
        String cookieName = "num1"; // 삭제 될 cookie 의 이름
        Cookie cookie = new Cookie(cookieName, "xxx");
        cookie.setMaxAge(0); // response 되자 마자 해당 이름의 cookie 는 삭제됨!
        response.addCookie(cookie);

        return "redirect:/cookie/list";
    }

    //----------------------------------------------------------------------------------------------------------
    // cookie 이용해서 로그인, 로그아웃 만들기
    public static final String ADMIN_ID = "admin";
    public static final String ADMIN_PW = "1234";

    @GetMapping("/login")
    public void login(@CookieValue(name="username", required = false) String username, Model model){
        model.addAttribute("username", username);
    }

    @PostMapping("/login")
    public String loginOk(String username, String password, HttpServletResponse response, Model model){
        if (ADMIN_ID.equalsIgnoreCase(username) && ADMIN_PW.equals(password)) {
            Cookie cookie = new Cookie("username", username);
            cookie.setMaxAge(30);
            response.addCookie(cookie);

            model.addAttribute("result", true);
        } else {
            Cookie cookie = new Cookie("username", username);
            cookie.setMaxAge(0); // 기존에 혹시 있었을지도 모를 쿠키를 삭제함.
            response.addCookie(cookie);
        }

        return "cookie/loginOk";
    }


    @PostMapping("/logout")
    public String logout(HttpServletResponse response){
        Cookie cookie = new Cookie("username", "");
        cookie.setMaxAge(0); // 쿠키 제거
        response.addCookie(cookie);
        return "cookie/logout";
    }


} // end controller
