package com.lec.spring.controller2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {

    // string  타입 방식
    @RequestMapping("/list")
    public String list() {
        return "/board/list";
    }
    @RequestMapping("/write")
    public String write() {
        return "/board/write";
    }
    @RequestMapping("/detail")
    public String detail() {
        return "/board/detail";
    }
    @RequestMapping("/update")
    public String update() {
        return "/board/update";
    }
    @RequestMapping("/delete")
    public String delete() {
        return "/board/delete";
    }

    // void 타입 방식
//    @RequestMapping("/list")
//    public void list() {
//    }
//
//    @RequestMapping("/write")
//    public void write() {
//    }
//
//    @RequestMapping("/detail")
//    public void detail() {
//    }
//
//    @RequestMapping("/delete")
//    public void delete() {
//    }
//
//    @RequestMapping("/update")
//    public void update() {
//    }
}
