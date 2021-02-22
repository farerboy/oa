<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc.jsp"></jsp:include>
<script type="text/javascript" charset="utf-8">
	var datagrid;
	$(function() {

		datagrid = $('#datagrid').datagrid({
			url : '/authority/datagrid',
			title : '权限列表',
			iconCls : 'icon-save',
			pagination : true,
			pagePosition : 'bottom',
			pageSize : 10,
			pageList : [ 10, 20, 30, 40 ],
			fit : true,
			fitColumns : false,
			nowrap : false,
			border : false,
			idField : 'id',
			sortName : 'name',
			sortOrder : 'desc',
			checkOnSelect : true,
			selectOnCheck : true,
			frozenColumns : [ [ {
				checkbox : true,
				title : '',
				field : ''
			},{
                title : 'id',
                field : 'id',
                width : 100,
            },
			{
				title : '权限名称',
				field : 'name',
				width : 250,
			} ] ],
			toolbar : [ {
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
					datagrid.datagrid('clearSelections');
					datagrid.datagrid('uncheckAll');
				}
			}, '-', {
				text : '关联路由',
				iconCls : 'icon-edit',
				handler : function() {
					authorityRoute();
				}
			}, '-' ],
			onRowContextMenu : function(e, rowIndex, rowData) {
				if (rowIndex == -1)
					return;
				e.preventDefault();
				$(this).datagrid('unselectAll');
				$(this).datagrid('selectRow', rowIndex);
				$('#menu').menu('show', {
					left : e.pageX,
					top : e.pageY
				});
			}
		});
	});

    function authorityRoute(){
        var rows = datagrid.datagrid('getChecked');
        if (rows.length > 1) {
            parent.util.messagerAlert('提示', '只能修改一条记录！', 'error');
            return;
        }else if(rows.length < 1){
            parent.util.messagerAlert('提示', '请选择要修改的记录！', 'error');
            return;
        }
        localStorage.setItem("authorityId",rows[0].id);
        var p = parent.util.dialog({
            title : '关联路由',
            href : '/page/admin/authorityRoute',
            width : 1000,
            height : 800,
            buttons : [ {
                text : '新增',
                handler : function() {
                    var nodes = p.find('#routeList').treegrid('getChecked')
                    var f = p.find('form');
                    f.form('submit', {
                        url : '/authority/add',
                        success : function(d) {
                            var json = $.parseJSON(d);
                            if (json.success) {
                                datagrid.datagrid('reload');
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

            }
        });
    }

	function append() {
		var p = parent.util.dialog({
			title : '新增用户',
			href : '/page/admin/authorityAdd',
			width : 500,
			height : 200,
			buttons : [ {
				text : '新增',
				handler : function() {
					var f = p.find('form');
					f.form('submit', {
						url : '/authority/add',
						success : function(d) {
							var json = $.parseJSON(d);
							if (json.success) {
								datagrid.datagrid('reload');
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

			}
		});
	}

	function edit() {
        var rows = datagrid.datagrid('getChecked');
        if (rows.length == 1) {
            var p = parent.util.dialog({
                title : '修改用户',
                href : '/page/admin/authorityEdit',
                width : 500,
                height : 200,
                buttons : [ {
                    text : '修改',
                    handler : function() {
                        var f = p.find('form');
                        f.form('submit', {
                            url : '/authority/edit',
                            success : function(d) {
                                var json = $.parseJSON(d);
                                if (json.success) {
                                    datagrid.datagrid('reload');
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
                    f.form('load', {
                        id : rows[0].id,
                        name : rows[0].name
                    });
                }
            });
        } else if (rows.length > 1) {
            parent.util.messagerAlert('提示', '只能修改一条记录！', 'error');
        } else {
            parent.util.messagerAlert('提示', '请选择要修改的记录！', 'error');
        }
    }

	function remove() {
		var rows = datagrid.datagrid('getChecked');
		var ids = [];
		if (rows.length > 0) {
			parent.util.messagerConfirm('请确认', '您要删除当前所选项目？', function(r) {
				if (r) {
					for ( var i = 0; i < rows.length; i++) {
						ids.push(rows[i].id);
					}
					$.ajax({
						url : '/authority/delete',
						data : {
							ids : ids.join(',')
						},
						dataType : 'json',
						success : function(d) {
							datagrid.datagrid('load');
							datagrid.datagrid('unselectAll');
							parent.util.messagerShow({
								title : '提示',
								msg : d.msg
							});
						}
					});
				}
			});
		} else {
			parent.util.messagerAlert('提示', '请勾选要删除的记录！', 'error');
		}
	}
	function _search() {
		datagrid.datagrid('load', util.serializeObject($('#searchForm')));
	}
	function cleanSearch() {
		datagrid.datagrid('load', {});
		$('#searchForm input').val('');
	}
</script>
</head>
<body class="easyui-layout" data-options="fit:true">
    <div data-options="region:'north',border:false,title:'过滤条件'" style="height: 70px;overflow: hidden;" align="left">
        <form id="searchForm">
            <table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
                <tr>
                    <th>权限名称</th>
                    <td><input name="name" style="width:140px;" />
                    <a href="javascript:void(0);" class="easyui-linkbutton" onclick="_search();">查询</a><a href="javascript:void(0);" class="easyui-linkbutton" onclick="cleanSearch();">清空</a></td>
                </tr>
            </table>
        </form>
    </div>
    <div data-options="region:'center',border:false" style="overflow: hidden;">
        <table id="datagrid"></table>
    </div>

    <div id="menu" class="easyui-menu" style="width:120px;display: none;">
        <div onclick="append();" data-options="iconCls:'icon-add'">增加</div>
        <div onclick="edit();" data-options="iconCls:'icon-edit'">修改</div>
        <div onclick="remove();" data-options="iconCls:'icon-remove'">删除</div>
    </div>
</body>
</html>