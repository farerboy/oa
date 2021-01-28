package com.farerboy.oa.dto;

import lombok.Data;

/**
 * sessionInfo模型
 *
 * @author farerboy
 */
@Data
public class SessionInfo {
	/**
	 * 用户ID
	 */
	private Long userId;
	/**
	 * 用户登录名称
	 */
	private String userName;
	/**
	 * IP地址
	 */
	private String ip;
	/**
	 * 角色集合
	 */
	private String roleIds;
	/**
	 * 角色名称
	 */
	private String roleNames;
	/**
	 * 是否锁屏状态
	 */
	private boolean lock = false;


}
