package com.farerboy.oa.service;

import com.farerboy.oa.dto.SystemAuthorityResourceDTO;
import com.farerboy.oa.model.SystemAuthorityResource;
import com.farerboy.oa.param.AuthorityResourceAddParam;
import com.farerboy.oa.service.base.BaseService;

import java.util.List;

/**
 * TODO description
 *
 * @author linjianbin
 * @date 2021/2/19 4:11 下午
 */
public interface AuthorityResourceService extends BaseService<SystemAuthorityResource> {

    int add(AuthorityResourceAddParam authorityResourceAddParam);

    boolean delete(Integer id);

    boolean deleteByAuthorityId(Integer authorityId);

    List<SystemAuthorityResourceDTO> listByAuthorityId(Integer authorityId);

    List<Integer> listRouteIdsByAuthorityId(Integer authorityId);
}
