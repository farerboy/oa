package com.farerboy.oa.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.farerboy.framework.boot.orm.helper.EnvHelper;
import com.farerboy.oa.comparator.SystemMenuComparator;
import com.farerboy.oa.mapper.SystemMenuMapper;
import com.farerboy.oa.model.SystemMenu;
import com.farerboy.oa.model.SystemRoute;
import com.farerboy.oa.param.MenuAddParam;
import com.farerboy.oa.param.MenuEditParam;
import com.farerboy.oa.service.MenuService;
import com.farerboy.oa.service.base.impl.BaseServiceImpl;
import com.farerboy.oa.vo.admin.MenuVO;
import com.farerboy.oa.vo.easyui.TreeNode;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.*;

/**
 * TODO description
 *
 * @author linjianbin
 * @date 2021/1/26 8:55 下午
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl<SystemMenuMapper, SystemMenu> implements MenuService {

    @Override
    public List<TreeNode> tree(Integer id,boolean recursive) {
        if(id == null){
            id = -1;
        }
        QueryWrapper<SystemMenu> wrapper = getBaseQueryWrapper(SystemMenu.class);
        wrapper.eq("parent_id",id);
        wrapper.orderByAsc("seq");
        List<SystemMenu> list = baseMapper.selectList(wrapper);
        List<TreeNode> tree = new ArrayList<TreeNode>();
        for(SystemMenu systemMenu : list){
            tree.add(this.tree(systemMenu,recursive));
        }
        return tree;
    }

    private TreeNode tree(SystemMenu t, boolean recursive) {
        TreeNode node = new TreeNode();
        node.setId(t.getId().toString());
        node.setText(t.getName());
        Map<String, Object> attributes = new HashMap();
        attributes.put("url", t.getUrl());
        node.setAttributes(attributes);
        if (t.getIconcls() != null) {
            node.setIconCls(t.getIconcls());
        } else {
            node.setIconCls("");
        }
        QueryWrapper<SystemMenu> wrapper = getBaseQueryWrapper(SystemMenu.class);
        wrapper.eq("parent_id",t.getId());
        List<SystemMenu> list = baseMapper.selectList(wrapper);
        if (list != null && list.size() > 0) {
            node.setState("closed");
            // 递归查询子节点
            if (recursive) {
                // 排序
                Collections.sort(list, new SystemMenuComparator());
                List<TreeNode> children = new ArrayList<TreeNode>();
                for (SystemMenu r : list) {
                    TreeNode tn = tree(r, true);
                    children.add(tn);
                }
                node.setChildren(children);
                node.setState("open");
            }
        }
        return node;
    }

    @Override
    public List<MenuVO> treegrid(Integer parentId) {
        return treegrid(parentId,null);
    }

    private List<MenuVO> treegrid(Integer parentId,String parentName){
        if(parentId == null){
            parentId = -1;
        }
        QueryWrapper wrapper = getBaseQueryWrapper(SystemMenu.class);
        wrapper.eq("parent_id",parentId);
        wrapper.orderByAsc("seq");
        List<SystemMenu> list = baseMapper.selectList(wrapper);
        List<MenuVO> result = new ArrayList<>();
        if(CollectionUtils.isEmpty(list)){
            return result;
        }
        for (SystemMenu systemMenu : list){
            MenuVO menuVO = new MenuVO();
            menuVO.setId(systemMenu.getId());
            menuVO.setIconcls(systemMenu.getIconcls());
            menuVO.setName(systemMenu.getName());
            menuVO.setSeq(systemMenu.getSeq());
            menuVO.setUrl(systemMenu.getUrl());
            if(parentId != null && parentId != -1){
                menuVO.setParentId(systemMenu.getParentId());
                menuVO.setParentName(parentName);
            }
            List<MenuVO> child = treegrid(systemMenu.getId(),systemMenu.getName());
            if(CollectionUtils.isNotEmpty(child)){
                menuVO.setChildren(child);
            }
            result.add(menuVO);
        }
        return result;
    }

    @Override
    public int add(MenuAddParam addParam) {
        SystemMenu systemMenu = new SystemMenu();
        BeanUtils.copyProperties(addParam,systemMenu);
        if(systemMenu.getParentId() == null){
            systemMenu.setParentId(-1);
        }
        systemMenu.setEnv(EnvHelper.getEnv());
        baseMapper.insert(systemMenu);
        return systemMenu.getId();
    }

    @Override
    public int edit(MenuEditParam menuEditParam) {
        SystemMenu systemMenu = new SystemMenu();
        BeanUtils.copyProperties(menuEditParam,systemMenu);
        if(systemMenu.getParentId() == null){
            systemMenu.setParentId(-1);
        }
        systemMenu.setEnv(EnvHelper.getEnv());
        return baseMapper.updateById(systemMenu);
    }

    @Override
    public int delete(Integer id) {
        return baseMapper.deleteById(id);
    }
}
