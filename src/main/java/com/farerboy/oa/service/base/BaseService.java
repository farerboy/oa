package com.farerboy.oa.service.base;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * TODO description
 *
 * @author linjianbin
 * @date 2021/1/16 5:33 下午
 */
public interface BaseService<T> extends IService<T> {

    Map<String,Object> getBaseColumn();

    QueryWrapper<T> getBaseQueryWrapper();

}
