package com.farerboy.oa.service;

import com.farerboy.oa.dto.SystemUserDTO;
import com.farerboy.oa.model.SystemUser;
import com.farerboy.oa.param.UserDataGridParam;
import com.farerboy.oa.service.base.BaseService;
import com.farerboy.oa.vo.easyui.DataGrid;

import java.text.ParseException;

/**
 * TODO description
 *
 * @author linjianbin
 * @date 2021/1/16 5:17 下午
 */
public interface UserService extends BaseService<SystemUser> {

    /**
     * 登录
     * @param userName
     * @param userToken
     * @return boolean
     */
    SystemUserDTO login(String userName, String userToken);

    DataGrid datagrid(UserDataGridParam user) throws ParseException;
}
