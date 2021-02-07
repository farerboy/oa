package com.farerboy.oa.model;

import lombok.Data;

@Data
public class SystemMenu {
    private Integer id;

    private String iconcls;

    private String name;

    private Integer seq;

    private String url;

    private Integer parentId;

    private String env;

}