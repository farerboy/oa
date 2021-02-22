<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" charset="utf-8">
	var treegrid;
	$(function() {
	    var authorityId = localStorage.getItem("authorityId");
		treegrid = $('#routeList').treegrid({
			url : '/route/treegridByAuthorityIdWithCheck',
			queryParams:{
			    authorityId:authorityId
			},
			toolbar : [ {
				text : '展开',
				iconCls : 'icon-redo',
				handler : function() {
					var node = treegrid.treegrid('getSelected');
					if (node) {
						treegrid.treegrid('expandAll', node.cid);
					} else {
						treegrid.treegrid('expandAll');
					}
				}
			}, '-', {
				text : '折叠',
				iconCls : 'icon-undo',
				handler : function() {
					var node = treegrid.treegrid('getSelected');
					if (node) {
						treegrid.treegrid('collapseAll', node.cid);
					} else {
						treegrid.treegrid('collapseAll');
					}
				}
			}, '-', {
				text : '刷新',
				iconCls : 'icon-reload',
				handler : function() {
					treegrid.treegrid('reload');
				}
			}, '-', {
				text : '取消选中',
				iconCls : 'icon-undo',
				handler : function() {
					treegrid.treegrid('unselectAll');
				}
			}, '-' ],
			title : '',
			iconCls : 'icon-save',
			fit : true,
			fitColumns : true,
			nowrap : true,
			animate : false,
			border : false,
			checkbox : true,
			checkOnSelect : true,
            selectOnCheck : true,
            singleSelect : false,
			idField : 'id',
			treeField : 'name',
			frozenColumns : [ [ {
				title : 'id',
				field : 'id',
				width : 50,
				hidden : true
			}, {
				field : 'name',
				title : '路由名称',
				width : 200,
				formatter : function(value) {
					if (value) {
						return util.fs('<span title="{0}">{1}</span>', value, value);
					}
				}
			} ] ],
			columns : [ [ {
				field : 'url',
				title : '路由地址',
				width : 250,
				formatter : function(value) {
					if (value) {
						return util.fs('<span title="{0}">{1}</span>', value, value);
					}
				}
			}, {
				field : 'description',
				title : '路由描述',
				width : 250,
				formatter : function(value) {
					if (value) {
						return util.fs('<span title="{0}">{1}</span>', value, value);
					}
				}
			}, {
				field : 'seq',
				title : '排序',
				width : 50
			}, {
				field : 'parentId',
				title : '上级路由ID',
				width : 150,
				hidden : true
			}, {
				field : 'parentName',
				title : '上级路由',
				width : 180
			} ] ],
			onExpand : function(row) {
				treegrid.treegrid('unselectAll');
			},
			onCollapse : function(row) {
				treegrid.treegrid('unselectAll');
			},
			onCheck : function(rowIndex,rowData){
			    console.log(rowIndex);
			    console.log(rowData);
			},
			onSelect:function(rowIndex,rowData){
			    console.log(rowIndex);
            	console.log(rowData);
			}

		});

	});
</script>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center',border:false" style="overflow: hidden;">
		<table id="routeList"></table>
	</div>
</div>
