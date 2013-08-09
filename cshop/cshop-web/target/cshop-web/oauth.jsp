<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/env.jsp"%>
<%
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<div align="center">
	<h2>
		<a target="_blank"
			href="http://auth.360buy.com/oauth/authorize?response_type=code&client_id=<%=jdappkey %>&redirect_uri=<%=jdredirectUrlPrefix %><%=contextPath %>/user/user!enterSystem.action?flag=3">
			<font>京东授权按钮</font>
		</a>
	</h2>
	<h2>
		<a target="_blank"
			href="https://oauth.taobao.com/authorize?response_type=code&client_id=<%=tbappkey%>&redirect_uri=<%=tbredirectUrlPrefix%><%=contextPath%>/user/user!enterSystem.action?flag=1">淘宝授权按钮</a>
	</h2>
</div>
</body>
</html>
