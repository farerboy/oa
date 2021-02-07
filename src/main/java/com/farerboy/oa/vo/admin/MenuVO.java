package com.farerboy.oa.vo.admin;

import lombok.Data;
import java.util.List;

/**
 * TODO description
 *
 * @author linjianbin
 * @date 2021/2/7 11:04 上午
 */
@Data
public class MenuVO {
    private Integer id;
    private Integer parentId;
    private String parentName;
    /**
     * 是否展开(open,closed)
     */
    private String state = "open";
    private String iconcls;
    private String name;
    private Integer seq;
    private String url;

    private List<MenuVO> children;
}
