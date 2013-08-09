<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>
        Delete 页面

    </title>

    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="/js/jquery.validate.min.js"></script>
    <script type="text/javascript" src="/js/jquery.metadata.js"></script>
</head>

<body>
<a href="/merchant/toAdd">添加</a>
<table width="500">
	<tr>
		<th>商家编号</th>
		<th>登陆名称</th>
		<th>店铺名称</th>
		<th>联系人姓名</th>
		<th>联系人手机</th>
		<th>操作</th>
	</tr>
	<#list lists as merchant>
		<tr>
			<td>${merchant.merchantNum}</td>
			<td>${merchant.loginName}</td>
			<td>${merchant.shopName}</td>
			<td>${merchant.contact}</td>
			<td>${merchant.telephone}</td>
			<td><a href="/merchant/toEdit/${merchant.id}">修改</a>&nbsp;<a href="/merchant/delete/${merchant.id}">删除</a></td>
		</tr>
	</#list>
	
</table>
</body>
</html>