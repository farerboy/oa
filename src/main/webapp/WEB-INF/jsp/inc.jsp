<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="ssh,easyui">
<meta http-equiv="description" content="Struts2+Spring3+Hibernate4+EasyUI">
<!-- jquery库 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/jquery-easyui-1.4.5/jquery.min.js" charset="utf-8"></script>
<!-- jquery Cookie插件 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/jquery.cookie.js" charset="utf-8"></script>
<!-- easyui相关库 -->
<link id="easyuiTheme" rel="stylesheet" href="${pageContext.request.contextPath}/jslib/jquery-easyui-1.4.5/themes/<c:out value="${cookie.easyuiThemeName.value}" default="default"/>/easyui.css" type="text/css"></link>
<link rel="stylesheet" href="${pageContext.request.contextPath}/jslib/jquery-easyui-1.4.5/themes/icon.css" type="text/css"></link>
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/jquery-easyui-1.4.5/jquery.easyui.min.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/jquery-easyui-1.4.5/locale/easyui-lang-zh_CN.js" charset="utf-8"></script>
<!-- 自定义库 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/common.css" type="text/css"></link>
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/util.js" charset="utf-8"></script>