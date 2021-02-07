package com.farerboy.oa.param;

import lombok.Data;

/**
 *
 * @author linjianbin
 * @date 2021/2/5 10:39 上午
 */
@Data
public class RouteAddParam {

    private String name;

    private String description;

    private Integer parentId;

    private Integer seq;

    private String url;
}
