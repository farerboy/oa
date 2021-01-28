package com.farerboy.oa.vo.easyui;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Menu {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String pid;
	private String pname;
	private String state = "open";// 是否展开(open,closed)
	private String iconCls;// 前面的小图标样式
	
	private String iconcls;
	private String name;
	private BigDecimal seq;
	private String url;

}
