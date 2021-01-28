package com.farerboy.oa.controller;

import com.farerboy.oa.service.MenuService;
import com.farerboy.oa.vo.easyui.TreeNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 *
 * @author linjianbin
 * @date 2021/1/26 8:48 下午
 */
@Slf4j
@RestController
@RequestMapping("menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PostMapping("tree")
    public List<TreeNode> tree(Integer id){
        return menuService.tree(id);
    }



}
