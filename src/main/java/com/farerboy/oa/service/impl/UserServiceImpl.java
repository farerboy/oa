package com.farerboy.oa.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.farerboy.framework.boot.common.enums.ResponseCode;
import com.farerboy.framework.boot.common.exception.BaseException;
import com.farerboy.oa.dto.SystemUserDto;
import com.farerboy.oa.mapper.SystemUserMapper;
import com.farerboy.oa.model.SystemUser;
import com.farerboy.oa.service.UserService;
import com.farerboy.oa.service.base.impl.BaseServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.Map;

/**
 * TODO description
 *
 * @author linjianbin
 * @date 2021/1/16 5:19 下午
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<SystemUserMapper,SystemUser> implements UserService{

    @Override
    public SystemUserDto login(String userName, String userToken) {
        Map<String,Object> column = getBaseColumn();
        column.put("user_name",userName);
        column.put("user_token",userToken);
        QueryWrapper wrapper = getBaseQueryWrapper();
        wrapper.eq("user_name",userName);
        wrapper.eq("user_token",userToken);
        SystemUser systemUser = getOne(wrapper);
        if(systemUser == null){
            throw new BaseException(ResponseCode.NO_DATA.getCode(),"用户名或密码错误！");
        }
        return fromModel(systemUser);
    }

    private SystemUserDto fromModel(SystemUser systemUser){
        SystemUserDto systemUserDto = new SystemUserDto();
        BeanUtils.copyProperties(systemUser,systemUserDto);
        return systemUserDto;
    }
}
