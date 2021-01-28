package com.farerboy.oa.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.farerboy.oa.dto.SessionInfo;
import com.farerboy.oa.dto.SystemUserDto;
import com.farerboy.oa.model.SystemUser;
import com.farerboy.oa.service.base.BaseService;

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
    SystemUserDto login(String userName, String userToken);

}
