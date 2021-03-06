<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc.jsp"></jsp:include>
<script type="text/javascript" charset="utf-8">
	var treegrid;
	$(function() {

		treegrid = $('#treegrid').treegrid({
			url : '/route/treegrid',
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
				text : '增加',
				iconCls : 'icon-add',
				handler : function() {
					append();
				}
			}, '-', {
				text : '修改',
				iconCls : 'icon-edit',
				handler : function() {
					edit();
				}
			}, '-', {
				text : '删除',
				iconCls : 'icon-remove',
				handler : function() {
					remove();
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
			fitColumns : false,
			nowrap : false,
			animate : false,
			border : false,
			idField : 'id',
			treeField : 'name',
			frozenColumns : [ [ {
				title : 'id',
				field : 'id',
				width : 150,
				hidden : true
			}, {
				field : 'name',
				title : '路由名称',
				width : 180,
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
				width : 150
			}, {
				field : 'parentId',
				title : '上级路由ID',
				width : 150,
				hidden : true
			}, {
				field : 'parentName',
				title : '上级路由',
				width : 150
			} ] ],
			onContextMenu : function(e, row) {
				e.preventDefault();
				$(this).treegrid('unselectAll');
				$(this).treegrid('select', row.id);
				$('#menu').menu('show', {
					left : e.pageX,
					top : e.pageY
				});
			},
			onExpand : function(row) {
				treegrid.treegrid('unselectAll');
			},
			onCollapse : function(row) {
				treegrid.treegrid('unselectAll');
			}

		});

	});

	function edit() {
		var node = treegrid.treegrid('getSelected');
		if (node) {
			var p = parent.util.dialog({
				title : '修改路由',
				href : '/page/admin/routeEdit',
				width : 440,
				height : 200,
				buttons : [ {
					text : '修改',
					handler : function() {
						var f = p.find('form');
						f.form('submit', {
							url : '/route/edit',
							success : function(d) {
								var json = $.parseJSON(d);
								if (json.success) {
									treegrid.treegrid('reload');
									p.dialog('close');
								}
								parent.util.messagerShow({
									msg : json.msg,
									title : '提示'
								});
							}
						});
					}
				} ],
				onLoad : function() {
					var f = p.find('form');
					var parentId = f.find('input[name=parentId]');
					var ptree = parentId.combotree({
						lines : true,
						url : '/route/tree',
					});
					f.form('load', node);
				}
			});
		} else {
			parent.util.messagerAlert('提示', '请选中要修改的记录！', 'error');
		}
	}
	function append() {
		var p = parent.util.dialog({
			title : '新增路由',
			href : '/page/admin/routeAdd',
			width : 440,
			height : 200,
			buttons : [ {
				text : '新增',
				handler : function() {
					var f = p.find('form');
					f.form('submit', {
						url : '/route/add',
						success : function(d) {
							var json = $.parseJSON(d);
							if (json.success) {
								treegrid.treegrid('reload');
								p.dialog('close');
							}
							parent.util.messagerShow({
								msg : json.msg,
								title : '提示'
							});
						}
					});
				}
			} ],
			onLoad : function() {
				var f = p.find('form');
				var parentId = f.find('input[name=parentId]');
				var ptree = parentId.combotree({
					lines : true,
					url : '/route/tree'
				});
			}
		});
	}
	function remove() {
		var node = treegrid.treegrid('getSelected');
		if (node) {
			parent.util.messagerConfirm('询问', '您确定要删除<' + node.cname + '>？', function(b) {
				if (b) {
					$.ajax({
						url : '/route/delete',
						data : {
							id : node.id
						},
						cache : false,
						dataType : 'JSON',
						success : function(r) {
							if (r.success) {
								treegrid.treegrid('remove', node.id);
							}
							parent.util.messagerShow({
								msg : r.msg,
								title : '提示'
							});
						}
					});
				}
			});
		}
	}
</script>
</head>
<body class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center',border:false" style="overflow: hidden;">
		<table id="treegrid"></table>
	</div>

	<div id="menu" class="easyui-menu" style="width:120px;display: none;">
		<div onclick="append();" data-options="iconCls:'icon-add'">增加</div>
		<div onclick="edit();" data-options="iconCls:'icon-edit'">修改</div>
		<div onclick="remove();" data-options="iconCls:'icon-remove'">删除</div>
	</div>
</body>
</html>