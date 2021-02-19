package com.farerboy.oa.context;

import com.farerboy.framework.boot.common.enums.DeletedEnum;
import com.farerboy.framework.boot.orm.context.AbstractDefaultColumnContext;
import com.farerboy.framework.boot.orm.helper.EnvHelper;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 *
 *
 * @author linjianbin
 * @date 2021/2/5 4:14 下午
 */
@Component
public class DefaultColumnContext extends AbstractDefaultColumnContext {

    @Override
    public void setDefaultColumn(Map<String, Object> defaultColumn) {
        defaultColumn.put("env", EnvHelper.getEnv());
        defaultColumn.put("deleted", DeletedEnum.getNotDeleted());
    }
}
