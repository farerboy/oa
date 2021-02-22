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
@RequestMapping("page/admin")
public class PageAdminController {

    @GetMapping("/route")
    public String route(){
        return "admin/route";
    }

    @GetMapping("/routeAdd")
    public String routeAdd(){
        return "admin/routeAdd";
    }

    @GetMapping("/routeEdit")
    public String routeEdit(){
        return "admin/routeEdit";
    }

    @GetMapping("/authority")
    public String authority(){
        return "admin/authority";
    }

    @GetMapping("/authorityAdd")
    public String authorityAdd(){
        return "admin/authorityAdd";
    }

    @GetMapping("/authorityEdit")
    public String authorityEdit(){
        return "admin/authorityEdit";
    }

    @GetMapping("/authorityRoute")
    public String authorityRoute(){
        return "admin/authorityRoute";
    }

    @GetMapping("/routeSelect")
    public String routeSelect(){
        return "admin/routeSelect";
    }

    @GetMapping("/role")
    public String role(){
        return "admin/role";
    }

    @GetMapping("/menu")
    public String menu(){
        return "admin/menu";
    }

    @GetMapping("/menuAdd")
    public String menuAdd(){
        return "admin/menuAdd";
    }

    @GetMapping("/menuEdit")
    public String menuEdit(){
        return "admin/menuEdit";
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
