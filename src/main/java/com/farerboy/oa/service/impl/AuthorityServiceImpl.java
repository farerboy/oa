package com.farerboy.oa.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.farerboy.framework.boot.util.AssertUtil;
import com.farerboy.oa.context.DefaultColumnContext;
import com.farerboy.oa.mapper.SystemAuthorityMapper;
import com.farerboy.oa.model.SystemAuthority;
import com.farerboy.oa.service.AuthorityService;
import com.farerboy.oa.service.base.impl.BaseServiceImpl;
import com.farerboy.oa.vo.admin.AuthorityVO;
import com.farerboy.oa.vo.admin.UserVO;
import com.farerboy.oa.vo.easyui.DataGrid;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO description
 *
 * @author linjianbin
 * @date 2021/2/7 4:36 下午
 */
@Service
public class AuthorityServiceImpl extends BaseServiceImpl<SystemAuthorityMapper, SystemAuthority> implements AuthorityService {

    @Override
    public DataGrid datagrid(String name) {
        QueryWrapper wrapper = getBaseQueryWrapper(SystemAuthority.class);
        if(StringUtils.isNotBlank(name)){
            wrapper.like("name",name);
        }
        DataGrid<AuthorityVO> dataGrid = new DataGrid<>();
        List<SystemAuthority> list = baseMapper.selectList(wrapper);
        if(CollectionUtils.isEmpty(list)){
            dataGrid.setTotal(0L);
            dataGrid.setRows(new ArrayList<>());
            return dataGrid;
        }
        List<AuthorityVO> rows = new ArrayList<>();
        for (SystemAuthority authority : list){
            AuthorityVO vo = new AuthorityVO();
            vo.setId(authority.getId());
            vo.setName(authority.getName());
            rows.add(vo);
        }
        dataGrid.setRows(rows);
        dataGrid.setTotal(Long.valueOf(list.size()));
        return dataGrid;
    }

    @Override
    public Integer add(String name) {
        AssertUtil.notBlank(name,"权限名称不能为空！");
        SystemAuthority systemAuthority = new SystemAuthority();
        systemAuthority.setName(name);
        save(systemAuthority);
        return systemAuthority.getId();
    }

    @Override
    public void edit(Integer id, String name) {
        AssertUtil.notNull(id,"Authority id can not be null !");
        AssertUtil.notBlank(name,"Authority name can not be null !");
        SystemAuthority systemAuthority = DefaultColumnContext.newInstance(SystemAuthority.class);
        systemAuthority.setName(name);
        systemAuthority.setId(id);
        updateById(systemAuthority);
    }

}
