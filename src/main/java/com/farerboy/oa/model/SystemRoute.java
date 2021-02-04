package com.farerboy.oa.model;

import lombok.Data;

@Data
public class SystemRoute {
    private Integer id;

    private String name;

    private String description;

    private Integer seq;

    private String url;

    private Integer parentId;

    private String env;

    private Integer deleted;

}