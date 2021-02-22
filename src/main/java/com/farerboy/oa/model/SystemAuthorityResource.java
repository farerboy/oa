package com.farerboy.oa.model;

import lombok.Data;

@Data
public class SystemAuthorityResource {
    private Integer id;

    private Integer authorityId;

    private Integer resourceId;

    private Integer resourceType;

    private String env;

}