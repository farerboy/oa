package com.farerboy.oa.service.base.impl;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.farerboy.framework.boot.common.enums.DeletedEnum;
import com.farerboy.framework.boot.common.exception.BaseException;
import com.farerboy.framework.boot.core.helper.env.EnvHelper;
import com.farerboy.oa.service.base.BaseService;
import com.google.common.collect.Maps;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
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

    @Override
    public QueryWrapper<T> getQueryWrapper(T o) {
        Map<String,Object> map = getBaseColumn();
        QueryWrapper wrapper = new QueryWrapper();
        Class cls = o.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            Object fieldObject = null;
            try {
                fieldObject = f.get(o);
            } catch (IllegalAccessException e) {
                throw new BaseException("IllegalAccessException",e);
            }
            if (fieldObject == null) {
                continue;
            }
            if(fieldObject instanceof String && StringUtils.isBlank(fieldObject.toString())){
                continue;
            }
            String column = null;
            TableField tableField = f.getAnnotation(TableField.class);
            if(tableField != null && StringUtils.isNotBlank(tableField.value())){
                column = tableField.value();
            }
            if(column == null){
                column = f.getName();
            }
            wrapper.eq(column,fieldObject);
            if(map.containsKey(column)){
                map.remove(column);
            }
        }
        if(MapUtils.isEmpty(map)){
            for (String key:map.keySet()) {
                wrapper.eq(key,map.get(key));
            }
        }
        return wrapper;
    }
}
