package com.farerboy.oa.controller;

import com.farerboy.framework.boot.common.dto.ServerResponse;
import com.farerboy.oa.param.RouteAddParam;
import com.farerboy.oa.param.RouteEditParam;
import com.farerboy.oa.service.RouteService;
import com.farerboy.oa.vo.admin.RouteVO;
import com.farerboy.oa.vo.easyui.TreeNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * TODO description
 *
 * @author linjianbin
 * @date 2021/2/4 7:47 下午
 */
@Slf4j
@RestController
@RequestMapping("route")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @PostMapping("treegrid")
    public List<RouteVO> treegrid(Integer id){
        return routeService.treegrid(id);
    }

    @PostMapping("treegridByAuthorityIdWithCheck")
    public List<RouteVO> treegridByAuthorityIdWithCheck(Integer authorityId){
        return routeService.treegridByAuthorityIdWithCheck(authorityId);
    }

    @PostMapping("tree")
    public List<TreeNode> tree(){
        return routeService.tree(null);
    }

    @PostMapping("add")
    public ServerResponse add(RouteAddParam routeAddParam){
        routeService.add(routeAddParam);
        return ServerResponse.createBySuccess();
    }

    @PostMapping("edit")
    public ServerResponse edit(RouteEditParam routeEditParam){
        routeService.edit(routeEditParam);
        return ServerResponse.createBySuccess();
    }

    @PostMapping("delete")
    public ServerResponse delete(Integer id){
        routeService.delete(id);
        return ServerResponse.createBySuccess();
    }

}
