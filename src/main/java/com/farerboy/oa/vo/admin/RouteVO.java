package com.farerboy.oa.vo.admin;

import lombok.Data;

import java.util.List;

/**
 * 路由vo
 *
 * @author linjianbin
 * @date 2021/2/4 7:24 下午
 */
@Data
public class RouteVO {

    private Integer id;
    private Integer parentId;
    private String parentName;
    /**
     * 是否展开(open,closed)
     */
    private String state = "open";
    private String description;
    private String name;
    private Integer seq;
    private String url;
    // 是否勾选状态
    private Boolean checked = false;
    private List<RouteVO> children;
}
