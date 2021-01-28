<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript" charset="utf-8">
	var loginDialog;
	var backgroudDialog;
	var loginInputForm;
	var loginDatagridForm;
	var loginComboboxForm;
	var loginDatagridName;
	var sessionInfo;
	$(function() {
		setBgImg();
		backgroudDialog = $('#backgroudDialog');
		var formParam = {
			url : '${pageContext.request.contextPath}/user/login',
			success : function(data) {
				var d = $.parseJSON(data);
				if (d.success) {
					loginDialog.dialog('close');
					backgroudDialog.dialog('close');
					message.location.reload(true);
					$('#sessionInfoDiv').html(util.fs('[<strong>{0}</strong>]，欢迎你！您使用[<strong>{1}</strong>]IP登录！', d.data.userName, d.data.ip));
				}
				$.messager.show({
					msg : d.msg,
					title : '提示'
				});
			}
		};

		loginInputForm = $('#loginInputForm').form(formParam);
		loginDatagridForm = $('#loginDatagridForm').form(formParam);
		loginComboboxForm = $('#loginComboboxForm').form(formParam);

		loginDialog = $('#loginDialog').show().dialog({
			closable : false,
			title : '登录',
			modal : true,
			buttons : [ {
				text : '登录',
				handler : function() {
					$('#loginComboboxForm').submit();
				}
			} ],
			onOpen : function() {
				var t = $(this);
				window.setTimeout(function() {
					t.find('input[name=id]').focus();
				}, 0);
			}
		});

		$('form input').bind('keyup', function(event) {/* 增加回车提交功能 */
			if (event.keyCode == '13') {
				$(this).submit();
			}
		});

		sessionInfo = {
			userId : '${sessionInfo.userId}',
			userName : '${sessionInfo.userName}',
			ip : '${sessionInfo.ip}',
			isLock : '${sessionInfo.lock}'
		};
		if (sessionInfo.userId) {/*如果有userId说明已经登陆过了*/
			window.setTimeout(function() {
				$('div.validatebox-tip').remove();
			}, 500);
			if (sessionInfo.isLock == 'false') {
				loginDialog.dialog('close');
				backgroudDialog.dialog('close');
			}

		}

	});
	function setBgImg() {
		var num = Math.floor(Math.random() * 2 + 1);
		$("#bgImg").attr("src", "style/images/bg" + num + ".jpg");
	}
</script>
<div id="backgroudDialog" class="easyui-dialog" data-options="fit:true,border:true,noheader:true" style="overflow:hidden">
	<img id="bgImg" src="" width="100%" height="100%">
</div>
<div id="loginDialog" style="width:250px;height:130px;display: none;overflow: hidden;">
	<div data-options="fit:true,border:false">
		<div align="center">
			<form id="loginComboboxForm" method="post">
				<table>
					<tr>
						<th style="text-align: right;">账号</th>
						<td><input name="userName" class="easyui-textbox" data-options="iconCls:'icon-man'" style="width: 150px;" /></td>
					</tr>
					<tr>
						<th style="text-align: right;">密码</th>
						<td><input name="userToken" type="password" class="easyui-textbox" data-options="iconCls:'icon-lock'" style="width: 150px;" /></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</div>