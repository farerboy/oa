<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="padding: 5px;">
	<form id="userForm" method="post">
		<table class="tableForm">
			<tr>
				<th style="width: 55px;">账号</th>
				<td><input name="id" class="easyui-validatebox" data-options="required:'true',missingMessage:'请填写登录账号'" style= "background-color:transparent;border:0px; width:150px;" readonly="readonly"/>
				</td>
				<th style="width: 55px;">昵称</th>
				<td><input name="name"  class="easyui-validatebox"  data-options="required:'true',missingMessage:'请填写昵称'" style="width: 150px;" /></td>
			</tr>
			<tr>
				<th style="width: 55px;">密码</th>
				<td><input name="pwd" type="password" />
				</td>
				<th style="width: 55px;">所属角色</th>
				<td><input name="roleIds" style="width: 155px;" /></td>
			</tr>
			<tr>
				<th style="width: 55px;">创建时间</th>
				<td><input name="createdatetime"    style= "background-color:transparent;border:0px; width:150px;" readonly="readonly"/>
				</td>
				<th style="width: 55px;">创建人员</th>
				<td><input name="createid"    style= "background-color:transparent;border:0px; width:150px;" readonly="readonly"/>
				</td>
			</tr>
			<tr>
				<th style="width: 55px;">修改时间</th>
				<td><input name="modifydatetime"    style= "background-color:transparent;border:0px; width:150px;" readonly="readonly"/>
				</td>
				<th style="width: 55px;">修改人员</th>
				<td><input name="modifyid"    style= "background-color:transparent;border:0px; width:150px;" readonly="readonly"/>
				</td>
			</tr>
		</table>
	</form>
</div>