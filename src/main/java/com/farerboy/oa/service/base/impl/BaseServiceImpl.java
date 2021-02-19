package com.farerboy.oa.service.base.impl;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.farerboy.framework.boot.common.exception.BaseException;
import com.farerboy.oa.context.DefaultColumnContext;
import com.farerboy.oa.service.base.BaseService;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;


/**
 * TODO description
 *
 * @author linjianbin
 * @date 2021/1/16 5:36 下午
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M,T> implements BaseService<T> {


    @Override
    public boolean save(T entity) {
        Class cls = entity.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            String columnStr = null;
            TableField tableField = f.getAnnotation(TableField.class);
            if(tableField != null && StringUtils.isNotBlank(tableField.value())){
                columnStr = tableField.value();
            }
            if(columnStr == null){
                columnStr = f.getName();
            }
            if(DefaultColumnContext.contains(columnStr)){
                try {
                    f.set(entity,DefaultColumnContext.getDefaultColumn(columnStr));
                }catch (Exception e){
                    throw new BaseException("SET_DEFAULT_COLUMN_ERROR",e.getMessage(),e);
                }
            }
        }
        return retBool(baseMapper.insert(entity));
    }

    @Override
    public Map<String, Object> getBaseColumn(Class<T> cls) {
        Map<String,Object> column = Maps.newHashMap();
        Field[] fields = cls.getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            String columnStr = null;
            TableField tableField = f.getAnnotation(TableField.class);
            if(tableField != null && StringUtils.isNotBlank(tableField.value())){
                columnStr = tableField.value();
            }
            if(columnStr == null){
                columnStr = f.getName();
            }
            if(DefaultColumnContext.contains(columnStr)){
                column.put(columnStr,DefaultColumnContext.getDefaultColumn(columnStr));
            }
        }
        return column;
    }

    @Override
    public QueryWrapper<T> getBaseQueryWrapper(Class<T> cls) {
        QueryWrapper wrapper = new QueryWrapper();
        Map<String,Object> map = getBaseColumn(cls);
        Set<String> keySet = map.keySet();
        if(CollectionUtils.isEmpty(keySet)){
            return wrapper;
        }
        for(String key : keySet){
            wrapper.eq(key,map.get(key));
        }
        return wrapper;
    }

    @Override
    public QueryWrapper<T> getQueryWrapper(T o) {
        Class cls = o.getClass();
        Map<String,Object> map = getBaseColumn(cls);
        QueryWrapper wrapper = new QueryWrapper();
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
            map.remove(column);
        }
        Set<String> keySet = map.keySet();
        if(CollectionUtils.isEmpty(keySet)){
            return wrapper;
        }
        for(String key : keySet){
            wrapper.eq(key,map.get(key));
        }
        return wrapper;
    }
}
