<%@ page language="java" pageEncoding="UTF-8"%>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'east',title:'拥有权限',split:true" style="width: 200px;">
		<ul></ul>
	</div>
	<div data-options="region:'center',title:'个人信息'" style="overflow: hidden;padding: 5px;">
		<form id="userInfoForm" method="post">
			<input name="cid" type="hidden" value="${sessionInfo.userId}" />
			<table class="tableForm">
				<tr>
					<th style="width: 55px;" align="left">账    号</th>
					<td><input  value="${sessionInfo.userId}" style= "background-color:transparent;border:0px; width:150px;" readonly="readonly"  /></td>
				</tr>
				<tr>
					<th style="width: 55px;" align="left">昵    称</th>
					<td><input  value="${sessionInfo.loginName}"  style= "background-color:transparent;border:0px; width:150px;" readonly="readonly"/></td>
				</tr>
				<tr>
					<th align="left">登录IP</th>
					<td><input  style= "background-color:transparent;border:0px; width:150px;" value="${sessionInfo.ip}" readonly="readonly"/></td>
				</tr>
				<tr>
					<th align="left">修改密码</th>
					<td><input name="pwd" type="password" class="easyui-validatebox" data-options="required:'true',missingMessage:'请填写修改密码'" /></td>
				</tr>
				<tr>
					<th align="left">确认密码</th>
					<td><input type="password" class="easyui-validatebox" data-options="required:'true',missingMessage:'请再次填写密码',validType:'eqPassword[\'#userInfoForm input[name=pwd]\']'" /></td>
				</tr>
				<tr>
					<th align="left">所属角色</th>
					<td><textarea readonly="readonly">${sessionInfo.roleNames}</textarea></td>
				</tr>
				<tr style="display: none;">
					<th>权限ID</th>
					<td><input name="authIds" value="${sessionInfo.authIds}"/></td>
				</tr>
			</table>
		</form>
	</div>
</div>