package com.farerboy.oa.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.farerboy.framework.boot.orm.helper.EnvHelper;
import com.farerboy.oa.mapper.SystemRouteMapper;
import com.farerboy.oa.model.SystemRoute;
import com.farerboy.oa.param.RouteAddParam;
import com.farerboy.oa.param.RouteEditParam;
import com.farerboy.oa.service.RouteService;
import com.farerboy.oa.service.base.impl.BaseServiceImpl;
import com.farerboy.oa.vo.admin.RouteVO;
import com.farerboy.oa.vo.easyui.TreeNode;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 *
 * @author linjianbin
 * @date 2021/2/4 7:15 下午
 */
@Service
public class RouteServiceImpl extends BaseServiceImpl<SystemRouteMapper, SystemRoute> implements RouteService {


    @Override
    public List<RouteVO> treegrid(Integer parentId) {
        return treegrid(parentId,null);
    }

    private List<RouteVO> treegrid(Integer parentId,String parentName){
        if(parentId == null){
            parentId = -1;
        }
        QueryWrapper wrapper = getBaseQueryWrapper(SystemRoute.class);
        wrapper.eq("parent_id",parentId);
        wrapper.orderByAsc("seq");
        List<SystemRoute> list = baseMapper.selectList(wrapper);
        List<RouteVO> result = new ArrayList<>();
        if(CollectionUtils.isEmpty(list)){
            return result;
        }
        for(SystemRoute systemRoute : list){
            RouteVO routeVO = new RouteVO();
            routeVO.setId(systemRoute.getId());
            routeVO.setName(systemRoute.getName());
            routeVO.setDescription(systemRoute.getDescription());
            routeVO.setSeq(systemRoute.getSeq());
            routeVO.setUrl(systemRoute.getUrl());
            if(parentId == -1){
                routeVO.setParentId(null);
            }else {
                routeVO.setParentId(parentId);
            }
            routeVO.setParentName(parentName);
            List<RouteVO> child = treegrid(systemRoute.getId(),systemRoute.getName());
            if(CollectionUtils.isNotEmpty(child)){
                routeVO.setChildren(child);
            }
//            QueryWrapper countWrapper = getBaseQueryWrapper();
//            countWrapper.eq("parent_id",systemRoute.getId());
//            Integer count = baseMapper.selectCount(countWrapper);
//            routeVO.setState(count > 0 ? "closed" : "open");
            result.add(routeVO);
        }
        return result;
    }

    @Override
    public List<TreeNode> tree(Integer parentId) {
        if(parentId == null){
            parentId = -1;
        }
        QueryWrapper wrapper = getBaseQueryWrapper(SystemRoute.class);
        wrapper.eq("parent_id",parentId);
        wrapper.orderByAsc("seq");
        List<SystemRoute> list = baseMapper.selectList(wrapper);
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        List<TreeNode> tree = new ArrayList<>();
        for(SystemRoute systemRoute : list){
            TreeNode node = new TreeNode();
            node.setId(systemRoute.getId().toString());
            node.setText(systemRoute.getName());
            node.setAttributes(new HashMap<>(0));
            List<TreeNode> child = tree(systemRoute.getId());
            node.setChildren(child);
            tree.add(node);
        }
        return tree;
    }

    @Override
    public int add(RouteAddParam routeAddParam) {
        SystemRoute systemRoute = new SystemRoute();
        BeanUtils.copyProperties(routeAddParam,systemRoute);
        systemRoute.setEnv(EnvHelper.getEnv());
        if(systemRoute.getParentId() == null){
            systemRoute.setParentId(-1);
        }
        baseMapper.insert(systemRoute);
        return systemRoute.getId();
    }

    @Override
    public int edit(RouteEditParam routeEditParam) {
        SystemRoute systemRoute = new SystemRoute();
        BeanUtils.copyProperties(routeEditParam,systemRoute);
        if(systemRoute.getParentId() == null){
            systemRoute.setParentId(-1);
        }
        systemRoute.setEnv(EnvHelper.getEnv());
        return baseMapper.updateById(systemRoute);
    }

    @Override
    public int delete(Integer id) {
        QueryWrapper queryWrapper = getBaseQueryWrapper(SystemRoute.class);
        queryWrapper.eq("id",id);
        return baseMapper.delete(queryWrapper);
    }

}
