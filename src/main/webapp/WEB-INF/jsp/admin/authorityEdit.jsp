<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div align="center" style="padding: 5px;overflow: hidden;">
	<form method="post">
		<input name="id" type="hidden" />
		<table class="tableForm">
			<tr>
				<th style="width: 55px;">权限名称</th>
				<td><input name="name" class="easyui-validatebox" data-options="required:true,missingMessage:'请填写菜单名称'" style="width:323px;" />
				</td>
			</tr>
		</table>
	</form>
</div>