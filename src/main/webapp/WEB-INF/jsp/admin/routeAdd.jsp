<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="padding: 5px;">
	<form method="post">
		<table class="tableForm">
			<tr>
				<th style="width: 70px;">路由名称</th>
				<td><input name="name" class="easyui-validatebox" data-options="required:true,missingMessage:'请填写菜单名称'" style="width: 155px;" /></td>
				<th style="width: 50px;">排序</th>
				<td><input name="seq" class="easyui-numberspinner" data-options="min:0,max:999,editable:false,required:true,missingMessage:'请选择菜单排序'" value="10" style="width:120px;" /></td>
			</tr>
			<tr>
				<th>路由地址</th>
				<td colspan="3"><input name="url" style="width:98%;" /></td>
			</tr>
			<tr>
				<th>路由描述</th>
				<td colspan="3"><input name="description" style="width:98%;" /></td>
			</tr>
			<tr>
				<th>上级路由</th>
				<td colspan="3"><input name="parentId" style="width: 335px;" /></td>
			</tr>
		</table>
	</form>
</div>