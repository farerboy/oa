package com.farerboy.oa.param;

import lombok.Data;

/**
 * TODO description
 *
 * @author linjianbin
 * @date 2021/2/7 3:23 下午
 */
@Data
public class MenuAddParam {

    private String name;

    private Integer seq;

    private String url;

    private Integer parentId;

    private String iconcls;
}
