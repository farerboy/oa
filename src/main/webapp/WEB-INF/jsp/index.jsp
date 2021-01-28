<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" 
	
%>
<!DOCTYPE html>
<html>
<head>
<title>在线业务办公自动化平台</title>
<jsp:include page="inc.jsp"></jsp:include>
</head>
<body class="easyui-layout">

	<div data-options="region:'north',href:'${pageContext.request.contextPath}/layout/north'" style="height: 50px;overflow: hidden;" class="logo"></div>
	<div data-options="region:'west',title:'功能导航',href:'${pageContext.request.contextPath}/layout/west'" style="width: 200px;overflow: hidden;"></div>
	<div data-options="region:'center',title:'欢迎使用',href:'${pageContext.request.contextPath}/layout/center'" style="overflow: hidden;"></div>
	<div data-options="region:'south',href:'${pageContext.request.contextPath}/layout/south'" style="height: 20px;overflow: hidden;"></div>
	<jsp:include page="user/login.jsp"></jsp:include>
</body>
</html>