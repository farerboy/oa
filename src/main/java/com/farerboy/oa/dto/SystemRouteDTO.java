package com.farerboy.oa.dto;

import lombok.Data;

@Data
public class SystemRouteDTO {
    private Integer id;

    private String name;

    private String description;

    private Integer seq;

    private String url;

    private Integer parentId;

}