package com.farerboy.oa.service.base.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.farerboy.framework.boot.common.enums.DeletedEnum;
import com.farerboy.framework.boot.core.helper.env.EnvHelper;
import com.farerboy.oa.service.base.BaseService;
import com.google.common.collect.Maps;
import java.util.Map;


/**
 * TODO description
 *
 * @author linjianbin
 * @date 2021/1/16 5:36 下午
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M,T> implements BaseService<T> {

    @Override
    public Map<String, Object> getBaseColumn() {
        Map<String,Object> column = Maps.newHashMap();
        column.put("env", EnvHelper.getEnvCode());
        column.put("deleted", DeletedEnum.getNotDeleted());
        return column;
    }

    @Override
    public QueryWrapper<T> getBaseQueryWrapper() {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("env", EnvHelper.getEnvCode());
        wrapper.eq("deleted", DeletedEnum.getNotDeleted());
        return wrapper;
    }
}
