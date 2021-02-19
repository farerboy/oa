package com.farerboy.oa.service;

import com.farerboy.oa.model.SystemAuthority;
import com.farerboy.oa.service.base.BaseService;
import com.farerboy.oa.vo.easyui.DataGrid;

/**
 * TODO description
 *
 * @author linjianbin
 * @date 2021/2/7 4:35 下午
 */
public interface AuthorityService extends BaseService<SystemAuthority> {

    DataGrid datagrid(String name);

    Integer add(String name);

    void edit(Integer id,String name);
}
