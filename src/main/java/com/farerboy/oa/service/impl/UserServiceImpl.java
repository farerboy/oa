package com.farerboy.oa.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.farerboy.framework.boot.common.enums.ResponseCode;
import com.farerboy.framework.boot.common.exception.BaseException;
import com.farerboy.framework.boot.orm.helper.PageHelper;
import com.farerboy.framework.boot.util.DateUtil;
import com.farerboy.oa.dto.SystemUserDTO;
import com.farerboy.oa.mapper.SystemUserMapper;
import com.farerboy.oa.model.SystemUser;
import com.farerboy.oa.param.UserDataGridParam;
import com.farerboy.oa.service.UserService;
import com.farerboy.oa.service.base.impl.BaseServiceImpl;
import com.farerboy.oa.vo.admin.UserVO;
import com.farerboy.oa.vo.easyui.DataGrid;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.unit.DataUnit;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    public SystemUserDTO login(String userName, String userToken) {
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

    @Override
    public DataGrid datagrid(UserDataGridParam userDataGridParam) throws ParseException {

        QueryWrapper wrapper = getBaseQueryWrapper();
        if(StringUtils.isNotBlank(userDataGridParam.getUserName())){
            wrapper.like("user_name",userDataGridParam.getUserName());
        }
        if(StringUtils.isNotBlank(userDataGridParam.getUserNickName())){
            wrapper.like("user_nick_name",userDataGridParam.getUserNickName());
        }
        if(StringUtils.isNotBlank(userDataGridParam.getCreateTimeStart()) && StringUtils.isNotBlank(userDataGridParam.getCreateTimeEnd())){
            Date createStart = DateUtil.parseDateTime(userDataGridParam.getCreateTimeStart());
            Date createEnd = DateUtil.parseDateTime(userDataGridParam.getCreateTimeEnd());
            wrapper.between("created_time",createStart,createEnd);
        }else if(StringUtils.isNotBlank(userDataGridParam.getCreateTimeStart())){
            Date createStart = DateUtil.parseDateTime(userDataGridParam.getCreateTimeStart());
            wrapper.ge("created_time",createStart);
        }else if(StringUtils.isNotBlank(userDataGridParam.getCreateTimeEnd())){
            Date createEnd = DateUtil.parseDateTime(userDataGridParam.getCreateTimeEnd());
            wrapper.le("created_time",createEnd);
        }
        String sort = null;
        if("userName".equals(userDataGridParam.getSort())){
            sort = "user_name";
        }else if("userNickName".equals(userDataGridParam.getSort())){
            sort = "user_nick_name";
        }else if("createTime".equals(userDataGridParam.getSort())){
            sort = "create_time";
        }else if("updateTime".equals(userDataGridParam.getSort())){
            sort = "update_time";
        }else {
            sort = "create_time";
        }
        IPage page = PageHelper.page(userDataGridParam.getPage(),userDataGridParam.getRows(),sort,userDataGridParam.getOrder());
        IPage<SystemUser> iPage = baseMapper.selectPage(page, wrapper);
        List<UserVO> list = changeVO(iPage.getRecords());
        DataGrid<UserVO> dataGrid = new DataGrid<>();
        dataGrid.setRows(list);
        dataGrid.setTotal(iPage.getTotal());
        return dataGrid;
    }

    private List<UserVO> changeVO(List<SystemUser> list){
        List<UserVO> result = new ArrayList<>();
        if(CollectionUtils.isEmpty(list)){
            return result;
        }
        for(SystemUser systemUser : list){
            UserVO vo = new UserVO();
            BeanUtils.copyProperties(systemUser,vo);
            result.add(vo);
        }
        return result;
    }

    private SystemUserDTO fromModel(SystemUser systemUser){
        SystemUserDTO systemUserDto = new SystemUserDTO();
        BeanUtils.copyProperties(systemUser,systemUserDto);
        return systemUserDto;
    }
}
