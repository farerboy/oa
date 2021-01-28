package com.farerboy.oa.param.base.easyui;

import lombok.Data;

/**
 * TODO description
 *
 * @author linjianbin
 * @date 2021/1/28 9:22 下午
 */
@Data
public class EasyUiPage {

    private Integer page;
    private Integer rows;
    private String sort;
    private String order;
}
