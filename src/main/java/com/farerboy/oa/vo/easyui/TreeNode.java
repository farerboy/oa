package com.farerboy.oa.vo.easyui;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * easyui使用的tree模型
 * 
 */
@Data
public class TreeNode {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String text;// 树节点名称
	private String iconCls;// 前面的小图标样式
	private Boolean checked = false;// 是否勾选状态
	private Map<String, Object> attributes;// 其他参数
	private List<TreeNode> children;// 子节点
	private String state = "open";// 是否展开(open,closed)

}
