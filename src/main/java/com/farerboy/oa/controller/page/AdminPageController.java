package com.farerboy.oa.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 *
 * @author linjianbin
 * @date 2021/1/28 8:11 下午
 */
@Controller
@RequestMapping("admin")
public class AdminPageController {

    @GetMapping("/user")
    public String south() {
        return "admin/user";
    }
}
