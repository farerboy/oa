package com.farerboy.oa.service;

import com.farerboy.oa.model.SystemMenu;
import com.farerboy.oa.param.MenuAddParam;
import com.farerboy.oa.param.MenuEditParam;
import com.farerboy.oa.service.base.BaseService;
import com.farerboy.oa.vo.admin.MenuVO;
import com.farerboy.oa.vo.easyui.TreeNode;

import java.util.List;

/**
 * TODO description
 *
 * @author linjianbin
 * @date 2021/1/26 8:54 下午
 */
public interface MenuService extends BaseService<SystemMenu> {

    List<TreeNode> tree(Integer id,boolean recursive);

    List<MenuVO> treegrid(Integer parentId);

    int add(MenuAddParam addParam);

    int delete(Integer id);

    int edit(MenuEditParam menuEditParam);
}
