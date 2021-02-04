package com.farerboy.oa.model;

import lombok.Data;

import java.util.Date;

@Data
public class SystemUser {
    private Long id;

    private String userName;

    private String userToken;

    private String userRealName;

    private String userNickName;

    private String userPhone;

    private String userEmail;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private Byte deleted;

    private String env;

}