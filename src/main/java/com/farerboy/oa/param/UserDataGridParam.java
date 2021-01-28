package com.farerboy.oa.param;

import com.farerboy.oa.param.base.easyui.EasyUiPage;
import lombok.Data;

/**
 *
 * @author linjianbin
 * @date 2021/1/28 9:25 下午
 */
@Data
public class UserDataGridParam extends EasyUiPage {


    private String userName;

    private String userNickName;

    private String createTimeStart;

    private String createTimeEnd;

    private String updateTimeStart;

    private String updateTimeEnd;



}
