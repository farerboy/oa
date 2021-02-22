package com.farerboy.oa.param;

import com.farerboy.framework.boot.common.annotaion.NotNull;
import com.farerboy.framework.boot.common.valid.ParamsRequired;
import com.farerboy.oa.enums.ResourceTypeEnum;
import lombok.Data;

/**
 * TODO description
 *
 * @author linjianbin
 * @date 2021/2/19 4:18 下午
 */
@Data
public class AuthorityResourceAddParam implements ParamsRequired {
    @NotNull
    private Integer authorityId;
    @NotNull
    private Integer resourceId;
    @NotNull
    private ResourceTypeEnum resourceTypeEnum;
}
