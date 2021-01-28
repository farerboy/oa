<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="padding: 5px;overflow: hidden;">
	<form id="userForm" method="post">
		<table class="tableForm">
		<tr>
				<th style="width: 55px;">账号</th>
				<td><input name="id" class="easyui-validatebox" data-options="required:'true',missingMessage:'请填写账号'" />
				</td>
				<th style="width: 55px;">昵称</th>
				<td><input name="name" class="easyui-validatebox" data-options="required:'true',missingMessage:'请填写昵称'" />
				</td>
			</tr>
			<tr>
				<th style="width: 55px;">所属角色</th>
				<td><input name="roleIds" style="width: 155px;" />
				</td>
				<th>密码</th>
				<td><input name="pwd" type="password" class="easyui-validatebox" data-options="required:'true',missingMessage:'请填写登录密码'" />
				</td>
			</tr>
			<tr>
				<th>重复密码</th>
				<td><input type="password" class="easyui-validatebox" data-options="required:'true',missingMessage:'请再次填写密码',validType:'eqPassword[\'#userForm input[name=pwd]\']'" />
				</td>
				<th></th>
				<td></td>
			</tr>
		</table>
	</form>
</div>