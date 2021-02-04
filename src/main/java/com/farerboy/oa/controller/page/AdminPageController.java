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

    @GetMapping("/route")
    public String route(){
        return "admin/route";
    }

    @GetMapping("/routeAdd")
    public String routeAdd(){
        return "admin/routeAdd";
    }

    @GetMapping("/role")
    public String role(){
        return "admin/role";
    }

    @GetMapping("/user")
    public String user() {
        return "admin/user";
    }

    @GetMapping("/userAdd")
    public String userAdd() {
        return "admin/userAdd";
    }


}
