<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc.jsp"></jsp:include>
<script type="text/javascript" charset="utf-8">
	var treegrid;
	var iconData;
	$(function() {

		iconData = [ {
			value : '',
			text : '默认'
		}, {
			value : 'icon-add',
			text : 'icon-add'
		}, {
			value : 'icon-edit',
			text : 'icon-edit'
		}, {
			value : 'icon-remove',
			text : 'icon-remove'
		}, {
			value : 'icon-save',
			text : 'icon-save'
		}, {
			value : 'icon-cut',
			text : 'icon-cut'
		}, {
			value : 'icon-ok',
			text : 'icon-ok'
		}, {
			value : 'icon-no',
			text : 'icon-no'
		}, {
			value : 'icon-cancel',
			text : 'icon-cancel'
		}, {
			value : 'icon-reload',
			text : 'icon-reload'
		}, {
			value : 'icon-search',
			text : 'icon-search'
		}, {
			value : 'icon-print',
			text : 'icon-print'
		}, {
			value : 'icon-help',
			text : 'icon-help'
		}, {
			value : 'icon-undo',
			text : 'icon-undo'
		}, {
			value : 'icon-redo',
			text : 'icon-redo'
		}, {
			value : 'icon-back',
			text : 'icon-back'
		}, {
			value : 'icon-sum',
			text : 'icon-sum'
		}, {
			value : 'icon-tip',
			text : 'icon-tip'
		} ];

		treegrid = $('#treegrid').treegrid({
			url : '/menu/treegrid',
			toolbar : [ {
				text : '展开',
				iconCls : 'icon-redo',
				handler : function() {
					var node = treegrid.treegrid('getSelected');
					if (node) {
						treegrid.treegrid('expandAll', node.id);
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
						treegrid.treegrid('collapseAll', node.id);
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
				text : '新增',
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
			fitColumns : true,
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
				title : '菜单名称',
				width : 180,
				formatter : function(value) {
					if (value) {
						return util.fs('<span title="{0}">{1}</span>', value, value);
					}
				}
			} ] ],
			columns : [ [ {
				field : 'iconcls',
				title : '菜单图标',
				width : 70,
				formatter : function(value) {
					if (!value) {
						return '';
					} else {
						return util.fs('<span class="{0}" style="display:inline-block;vertical-align:middle;width:16px;height:16px;"></span>', value);
					}
				}
			}, {
				field : 'url',
				title : '菜单地址',
				width : 250,
				formatter : function(value) {
					if (value) {
						return util.fs('<span title="{0}">{1}</span>', value, value);
					}
				}
			}, {
				field : 'seq',
				title : '排序',
				width : 40
			}, {
				field : 'parentId',
				title : '上级菜单ID',
				width : 150,
				hidden : true
			}, {
				field : 'parentName',
				title : '上级菜单',
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
			onLoadSuccess : function(row, data) {
			/*
				var t = $(this);
				if (data) {
					$(data).each(function(index, d) {
						if (this.state == 'closed') {
							t.treegrid('expandAll');
						}
					});
				}
				*/
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
				title : '修改菜单',
				href : '/page/admin/menuEdit',
				width : 460,
				height : 220,
				buttons : [ {
					text : '修改',
					handler : function() {
						var f = p.find('form');
						f.form('submit', {
							url : '/menu/edit',
							success : function(d) {
								var json = $.parseJSON(d);
								if (json.success) {
									treegrid.treegrid('reload');
									p.dialog('close');
									parent.ctrlTree.tree('reload');/*刷新左侧菜单树*/
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
					var pid = f.find('input[name=parentId]');
					var iconCls = f.find('input[name=iconcls]');
					var iconCombo = iconCls.combobox({
						data : iconData,
						formatter : function(v) {
							return util.fs('<span class="{0}" style="display:inline-block;vertical-align:middle;width:16px;height:16px;"></span>{1}', v.value, v.value);
						}
					});
					var ptree = pid.combotree({
						lines : true,
						url : '/menu/treeRecursive',
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
			title : '新增菜单',
			href : '/page/admin/menuAdd',
			width : 460,
			height : 220,
			buttons : [ {
				text : '新增',
				handler : function() {
					var f = p.find('form');
					f.form('submit', {
						url : '/menu/add',
						success : function(d) {
							var json = $.parseJSON(d);
							if (json.success) {
								treegrid.treegrid('reload');
								p.dialog('close');
								parent.ctrlTree.tree('reload');/*刷新左侧菜单树*/
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
				var pid = f.find('input[name=parentId]');
				var iconCls = f.find('input[name=iconcls]');
				var iconCombo = iconCls.combobox({
					data : iconData,
					formatter : function(v) {
						return util.fs('<span class="{0}" style="display:inline-block;vertical-align:middle;width:16px;height:16px;"></span>{1}', v.value, v.value);
					}
				});
				var ptree = pid.combotree({
					lines : true,
					url : '/menu/treeRecursive'
				});
			}
		});
	}
	function remove() {
		var node = treegrid.treegrid('getSelected');
		if (node) {
			parent.util.messagerConfirm('询问', '您确定要删除<' + node.name + '>？', function(b) {
				if (b) {
					$.ajax({
						url : '/menu/delete',
						data : {
							id : node.id
						},
						cache : false,
						dataType : 'JSON',
						success : function(r) {
							if (r.success) {
								treegrid.treegrid('remove', node.id);
								parent.ctrlTree.tree('reload');/*刷新左侧菜单树*/
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
		<div onclick="append();" data-options="iconCls:'icon-add'">新增</div>
		<div onclick="edit();" data-options="iconCls:'icon-edit'">修改</div>
		<div onclick="remove();" data-options="iconCls:'icon-remove'">删除</div>
	</div>
</body>
</html>