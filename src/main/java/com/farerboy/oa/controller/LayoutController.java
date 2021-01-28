package com.farerboy.oa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * TODO description
 *
 * @author linjianbin
 * @date 2021/1/13 9:51 下午
 */
@Controller
@RequestMapping("layout")
public class LayoutController {

    @GetMapping("/south")
    public String south() {
        return "layout/south";
    }

    @GetMapping("/center")
    public String center() {
        return "layout/center";
    }

    @GetMapping("/west")
    public String west() {
        return "layout/west";
    }

    @GetMapping("/north")
    public String north() {
        return "layout/north";
    }

}
