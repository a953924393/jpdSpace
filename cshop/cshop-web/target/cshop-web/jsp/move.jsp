<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>优化商品菜单</title>
<script language="javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/js/move.js"></script>
</head>
<body>
	
	<s:hidden id="uid" value="%{session.get(user).id}"></s:hidden>
	<select id="accounts" name="accounts"></select>

</body>
</html>