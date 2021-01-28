package com.farerboy.oa.dto;

import lombok.Data;

import java.util.Date;

/**
 * TODO description
 *
 * @author linjianbin
 * @date 2021/1/16 7:09 下午
 */
@Data
public class SystemUserDTO {

    private Long id;

    private String userName;

    private String userRealName;

    private String userNickName;

    private String userPhone;

    private String userEmail;

    private String createdBy;

    private Date createdTime;

    private String updatedBy;

    private Date updatedTime;

}
