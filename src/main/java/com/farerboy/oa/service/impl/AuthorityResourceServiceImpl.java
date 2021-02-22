package com.farerboy.oa.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.farerboy.oa.context.DefaultColumnContext;
import com.farerboy.oa.dto.SystemAuthorityResourceDTO;
import com.farerboy.oa.enums.ResourceTypeEnum;
import com.farerboy.oa.mapper.SystemAuthorityResourceMapper;
import com.farerboy.oa.model.SystemAuthorityResource;
import com.farerboy.oa.param.AuthorityResourceAddParam;
import com.farerboy.oa.service.AuthorityResourceService;
import com.farerboy.oa.service.base.impl.BaseServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author linjianbin
 * @date 2021/2/19 4:19 下午
 */
@Service
public class AuthorityResourceServiceImpl extends BaseServiceImpl<SystemAuthorityResourceMapper, SystemAuthorityResource> implements AuthorityResourceService {

    @Override
    public int add(AuthorityResourceAddParam authorityResourceAddParam) {
        authorityResourceAddParam.validate();
        SystemAuthorityResource systemAuthorityResource = DefaultColumnContext.newInstance(SystemAuthorityResource.class);
        systemAuthorityResource.setAuthorityId(authorityResourceAddParam.getAuthorityId());
        systemAuthorityResource.setResourceId(authorityResourceAddParam.getResourceId());
        systemAuthorityResource.setResourceType(authorityResourceAddParam.getResourceTypeEnum().getCode());
        save(systemAuthorityResource);
        return systemAuthorityResource.getId();
    }

    @Override
    public boolean delete(Integer id){
        return removeById(id);
    }

    @Override
    public boolean deleteByAuthorityId(Integer authorityId) {
        QueryWrapper wrapper = getBaseQueryWrapper(SystemAuthorityResource.class);
        wrapper.eq("authority_id",authorityId);
        return remove(wrapper);
    }

    @Override
    public List<SystemAuthorityResourceDTO> listByAuthorityId(Integer authorityId) {
        QueryWrapper<SystemAuthorityResource> wrapper = getBaseQueryWrapper(SystemAuthorityResource.class);
        wrapper.eq("authority_id",authorityId);
        List<SystemAuthorityResource> list = list(wrapper);
        List<SystemAuthorityResourceDTO> result = new ArrayList<>();
        if(CollectionUtils.isEmpty(list)){
            return result;
        }
        for(SystemAuthorityResource systemAuthorityResource:list){
            result.add(fromSystemAuthorityResource(systemAuthorityResource));
        }
        return result;
    }

    @Override
    public List<Integer> listRouteIdsByAuthorityId(Integer authorityId) {
        QueryWrapper<SystemAuthorityResource> wrapper = getBaseQueryWrapper(SystemAuthorityResource.class);
        wrapper.eq("authority_id",authorityId);
        wrapper.eq("resource_type", ResourceTypeEnum.ROUTE.getCode());
        List<SystemAuthorityResource> list = list(wrapper);
        List<Integer> result = new ArrayList<>();
        if(CollectionUtils.isEmpty(list)){
            return result;
        }
        for(SystemAuthorityResource systemAuthorityResource:list){
            result.add(systemAuthorityResource.getResourceId());
        }
        return result;
    }

    private SystemAuthorityResourceDTO fromSystemAuthorityResource(SystemAuthorityResource systemAuthorityResource){
        SystemAuthorityResourceDTO dto = new SystemAuthorityResourceDTO();
        BeanUtils.copyProperties(systemAuthorityResource,dto);
        return dto;
    }
}
