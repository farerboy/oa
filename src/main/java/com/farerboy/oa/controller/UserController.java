package com.farerboy.oa.controller;

import com.farerboy.framework.boot.common.dto.ServerResponse;
import com.farerboy.framework.boot.core.helper.RequestHelper;
import com.farerboy.oa.dto.SessionInfo;
import com.farerboy.oa.dto.SystemUserDto;
import com.farerboy.oa.param.UserLoginParam;
import com.farerboy.oa.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author farerboy
 */
@Slf4j
@RestController
@RequestMapping("user")
public class UserController{

	@Autowired
	private UserService userService;
	public String user() {
		return "user";
	}

	public String userAdd() {
		return "userAdd";
	}

	public String userEdit() {
		return "userEdit";
	}

	public String userRoleEdit() {
		return "userRoleEdit";
	}

	public String doNotNeedAuth_userInfo() {
		return "doNotNeedAuth_userInfo";
	}


//	public void doNotNeedAuth_editUserInfo() {
//		Json j = new Json();
//		try {
//			userService.editUserInfo(user);
//			j.setSuccess(true);
//			j.setMsg("修改成功！");
//		} catch (Exception e) {
//			logger.error(ExceptionUtil.getExceptionMessage(e));
//			j.setMsg("修改失败！");
//		}
//		super.writeJson(j);
//	}

	@PostMapping("login")
	public ServerResponse login(UserLoginParam userLoginParam, HttpServletRequest request) {
		SystemUserDto systemUserDto = userService.login(userLoginParam.getUserName(),userLoginParam.getUserToken());
		if(systemUserDto == null){
			return ServerResponse.createByErrorMessage("登录失败！用户名或密码错误！");

		}
		SessionInfo sessionInfo = new SessionInfo();
		sessionInfo.setUserId(systemUserDto.getId());
		sessionInfo.setUserName(systemUserDto.getUserName());
		sessionInfo.setIp(RequestHelper.getRequestIp());
		sessionInfo.setLock(false);
		request.setAttribute("sessionInfo", sessionInfo);
		return ServerResponse.createBySuccess("登录成功",sessionInfo);
	}

//	public void doNotNeedSession_logout() {
//		ServletActionContext.getRequest().getSession().invalidate();
//		Json j = new Json();
//		j.setSuccess(true);
//		super.writeJson(j);
//	}
//
//	public void doNotNeedSession_lock() {
//		SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute(ResourceUtil.getSessionInfoName());
//		if(sessionInfo!=null){
//			sessionInfo.setIsLock(true);
//		}
//		Json j = new Json();
//		j.setObj(sessionInfo);
//		j.setSuccess(true);
//		super.writeJson(j);
//	}
//
//	public void add() {
//		Json j = new Json();
//		try {
//			userService.save(user);
//			j.setMsg("新增成功！");
//			j.setSuccess(true);
//		} catch (Exception e) {
//			j.setMsg("新增失败，用户名已存在！");
//			ExceptionUtil.getExceptionMessage(e);
//		}
//		super.writeJson(j);
//	}
//
//	public void edit() {
//		Json j = new Json();
//		try {
//			userService.update(user);
//			j.setSuccess(true);
//			j.setMsg("修改成功！");
//		} catch (Exception e) {
//			logger.error(ExceptionUtil.getExceptionMessage(e));
//			j.setMsg("修改失败，用户名已存在！");
//		}
//		super.writeJson(j);
//	}
//
//	public void roleEdit() {
//		Json j = new Json();
//		try {
//			userService.roleEdit(user);
//			j.setSuccess(true);
//			j.setMsg("修改成功！");
//		} catch (Exception e) {
//			logger.error(ExceptionUtil.getExceptionMessage(e));
//			j.setMsg("修改失败！");
//		}
//		super.writeJson(j);
//	}
//
//	public void delete() {
//		Json j = new Json();
//		userService.delete(user.getIds());
//		j.setSuccess(true);
//		j.setMsg("删除成功!");
//		super.writeJson(j);
//	}
//
//	public void datagrid() {
//		super.writeJson(userService.datagrid(user));
//	}
//
//	public void doNotNeedSession_datagrid() {
//		if (user.getQ() != null && !user.getQ().trim().equals("")) {
//			user.setName(user.getQ());
//		}
//		super.writeJson(userService.datagrid(user));
//	}

}
