package com.farerboy.oa.service;

import com.farerboy.oa.model.SystemRoute;
import com.farerboy.oa.param.RouteAddParam;
import com.farerboy.oa.param.RouteEditParam;
import com.farerboy.oa.service.base.BaseService;
import com.farerboy.oa.vo.admin.RouteVO;
import com.farerboy.oa.vo.easyui.TreeNode;

import java.util.List;

/**
 * 路由服务类
 *
 * @author linjianbin
 * @date 2021/2/4 7:12 下午
 */
public interface RouteService extends BaseService<SystemRoute> {

    List<RouteVO> treegrid(Integer parentId);

    List<RouteVO> treegridByAuthorityIdWithCheck(Integer authorityId);

    List<TreeNode> tree(Integer parentId);

    int add(RouteAddParam routeAddParam);

    int edit(RouteEditParam routeEditParam);

    int delete(Integer id);
}
