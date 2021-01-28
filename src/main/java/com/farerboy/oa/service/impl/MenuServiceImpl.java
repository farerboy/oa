package com.farerboy.oa.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.farerboy.oa.comparator.SystemMenuComparator;
import com.farerboy.oa.mapper.SystemMenuMapper;
import com.farerboy.oa.model.SystemMenu;
import com.farerboy.oa.service.MenuService;
import com.farerboy.oa.service.base.impl.BaseServiceImpl;
import com.farerboy.oa.vo.easyui.TreeNode;
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
    public List<TreeNode> tree(Integer id) {
        if(id == null){
            id = -1;
        }
        QueryWrapper wrapper = getBaseQueryWrapper();
        wrapper.eq("parent_id",id);
        wrapper.orderByAsc("seq");
        List<SystemMenu> list = baseMapper.selectList(wrapper);
        List<TreeNode> tree = new ArrayList<TreeNode>();
        for(SystemMenu systemMenu : list){
            tree.add(this.tree(systemMenu,false));
        }
        return tree;
    }

    private TreeNode tree(SystemMenu t, boolean recursive) {
        TreeNode node = new TreeNode();
        node.setId(t.getId().toString());
        node.setText(t.getName());
        Map<String, Object> attributes = new HashMap<String, Object>();
        attributes.put("url", t.getUrl());
        node.setAttributes(attributes);
        if (t.getIconcls() != null) {
            node.setIconCls(t.getIconcls());
        } else {
            node.setIconCls("");
        }
        QueryWrapper wrapper = getBaseQueryWrapper();
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
}
