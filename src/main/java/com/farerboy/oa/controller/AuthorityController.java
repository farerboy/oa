package com.farerboy.oa.controller;

import com.farerboy.framework.boot.common.dto.ServerResponse;
import com.farerboy.oa.service.AuthorityService;
import com.farerboy.oa.vo.easyui.DataGrid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO description
 *
 * @author linjianbin
 * @date 2021/2/7 4:33 下午
 */
@RestController
@RequestMapping("authority")
public class AuthorityController {

    @Autowired
    private AuthorityService authorityService ;

    @PostMapping("datagrid")
    public DataGrid datagrid(String name){
        return authorityService.datagrid(name);
    }

    @PostMapping("add")
    public ServerResponse add(String name){
        authorityService.add(name);
        return ServerResponse.createBySuccess();
    }

    @PostMapping("edit")
    public ServerResponse edit(Integer id,String name){
        authorityService.edit(id,name);
        return ServerResponse.createBySuccess();
    }

}
