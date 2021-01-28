package com.farerboy.oa.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *
 * @author linjianbin
 * @date 2021/1/15 11:51 上午
 */
@Slf4j
@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model model) {
        log.debug("/index");
        model.addAttribute("name","farerboy");
//        ModelAndView view = new ModelAndView("index");
//        view.addObject("userName", "蝈蝈");
//        return view;
        return "index";
    }

    @GetMapping("test")
    public String test(){
        return "123";
    }
}
