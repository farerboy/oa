<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" charset="utf-8">
	function logout(b) {
		$('#sessionInfoDiv').html('');
		$.post('${pageContext.request.contextPath}/userAction!doNotNeedSession_logout.action', function() {
			if (b) {
				if (util.isLessThanIe8()) {
					loginDialog.dialog('open');
				} else {
					location.replace('${pageContext.request.contextPath}/');
				}
			} else {
				loginDialog.dialog('open');
			}
		});
	}
	function showUserInfo() {
		var p = parent.util.dialog({
			title : '用户信息',
			href : '${pageContext.request.contextPath}/userAction!doNotNeedAuth_userInfo.action',
			width : 500,
			height : 300,
			buttons : [ {
				text : '修改密码',
				handler : function() {
					var f = p.find('form');
					f.form('submit', {
						url : '${pageContext.request.contextPath}/userAction!doNotNeedAuth_editUserInfo.action',
						success : function(d) {
							var json = $.parseJSON(d);
							if (json.success) {
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
				var authIds = p.find('ul');
				var authIdsTree = authIds.tree({
					url : '${pageContext.request.contextPath}/authAction!doNotNeedSession_treeRecursive.action',
					lines : true,
					checkbox : true,
					onLoadSuccess : function(node, data) {
						var f = p.find('form');
						var ids = f.find('input[name=authIds]').val();
						var idList = util.getList(ids);
						if (idList.length > 0) {
							for ( var i = 0; i < idList.length; i++) {
								var n = authIdsTree.tree('find', idList[i]);
								authIdsTree.tree('check', n.target);
							}
						}
						authIdsTree.unbind();
					}
				});
			}
		});
	}
	
	function lockWin(){
		$.post('${pageContext.request.contextPath}/userAction!doNotNeedSession_lock.action', function() {});
		loginDialog.dialog('open');
	}
</script>
<div id="sessionInfoDiv" style="position: absolute;right: 5px;top:10px;">
	<c:if test="${sessionInfo.userId != null}">[<strong>${sessionInfo.loginName}</strong>]，欢迎你！您使用[<strong>${sessionInfo.ip}</strong>]IP登录！</c:if>
</div>
<div style="position: absolute; right: 0px; bottom: 0px; ">
<a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#layout_north_kzmbMenu'">设置</a> <a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#layout_north_zxMenu'">注销</a>
</div>
<div id="layout_north_kzmbMenu" style="width: 100px; display: none;">
	<div onclick="showUserInfo();">个人信息</div>
	<div class="menu-sep"></div>
	<div>
		<span>更换主题</span>
		<div style="width: 120px;">
			<div onclick="util.changeTheme('default');">默认</div>
			<div onclick="util.changeTheme('gray');">灰暗</div>
			<div onclick="util.changeTheme('metro');">metro</div>
		</div>
	</div>
</div>
<div id="layout_north_zxMenu" style="width: 100px; display: none;">
	<div onclick="lockWin();">锁定窗口</div>
	<div class="menu-sep"></div>
	<div onclick="logout(true);">退出系统</div>
</div>
