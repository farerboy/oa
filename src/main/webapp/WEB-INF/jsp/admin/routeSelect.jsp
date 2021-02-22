<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" charset="utf-8">
	var routeTreegrid;
	$(function() {
	    var authorityId = localStorage.getItem("authorityId");
		routeTreegrid = $('#routeTreegrid').treegrid({
			url : '/route/treegridByAuthorityIdWithCheck',
			queryParams:{
                authorityId:authorityId
            },
			toolbar : [ {
				text : '展开',
				iconCls : 'icon-redo',
				handler : function() {
					var node = routeTreegrid.treegrid('getSelected');
					if (node) {
						routeTreegrid.treegrid('expandAll', node.cid);
					} else {
						routeTreegrid.treegrid('expandAll');
					}
				}
			}, '-', {
				text : '折叠',
				iconCls : 'icon-undo',
				handler : function() {
					var node = routeTreegrid.treegrid('getSelected');
					if (node) {
						routeTreegrid.treegrid('collapseAll', node.cid);
					} else {
						routeTreegrid.treegrid('collapseAll');
					}
				}
			}, '-', {
				text : '刷新',
				iconCls : 'icon-reload',
				handler : function() {
					routeTreegrid.treegrid('reload');
				}
			}, '-', {
				text : '取消选中',
				iconCls : 'icon-undo',
				handler : function() {
					routeTreegrid.treegrid('unselectAll');
				}
			}, '-' ],
			title : '',
			iconCls : 'icon-save',
			fit : true,
			fitColumns : false,
			nowrap : false,
			animate : false,
			border : false,
			checkbox : true,
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
			onExpand : function(row) {
				routeTreegrid.treegrid('unselectAll');
			},
			onCollapse : function(row) {
				routeTreegrid.treegrid('unselectAll');
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
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center',border:false" style="overflow: hidden;">
		<table id="routeTreegrid"></table>
	</div>
</div>
