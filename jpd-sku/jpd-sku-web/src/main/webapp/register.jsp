<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/reg.css" rel="stylesheet">
<title>SKU搜索助手注册</title>
</head>
<body>
<div id="site-nav">
	<div id="header">
		<p class="nav-title">京拍档SKU搜索助手</p>
	</div>
</div>
	<div id="content">
		<div class="con-left"><img src="images/reg-left.jpg" /></div>
		<div class="con-right">
			<div class="reg-title"><p class="reg-t-p"><img src="images/t.jpg" /></p><p class="reg-t-t">京拍档SKU搜索助手</p><p class="reg-t-w">|&nbsp;欢迎注册</p></div>
			<div style="clear:both"></div>
			<div class="reg-form">
					<table width="415" border="0" cellpadding="0" cellspacing="0" style=" margin:0 auto;">
						<tr>
							<td width="80" align="right"><p><span>*</span>邮箱：</p></td><td><input id="userEmail" name="userEmail" class="input-text" type="text" placeholder="请输入邮箱" /></td>
						</tr>
						<tr>
							<td></td><td><p class="msg" id="emailmsg">请输入可用邮箱</p></td>
						</tr>
						<tr>
							<td align="right"><p><span>*</span>密码：</p></td><td><input id="password" name="password" class="input-text" type="password" placeholder="请输入密码" /></td>
						</tr>
						<tr>
							<td></td><td><p class="msg" id="passwordmsg">6-20位字符，可使用字母、数字或符号的组成</p></td>
						</tr>
						<tr>
							<td align="right"><p><span>*</span>确认密码：</p></td><td><input id="confirmPassword" name="confirmPassword" class="input-text" type="password" placeholder="请再次输入密码" /></td>
						</tr>
						<tr style="height:10px;">
							<td colspan="2" style="border-bottom:1px #EDEDED solid;">&nbsp;</td>
						</tr>
						<tr style="height:20px;"></tr>
						<tr>
							<td align="right"><p>QQ号码：</p></td><td><input id="userQq" name="userQq" class="input-text" type="text" placeholder="请输入您的QQ号码" /></td>
						</tr>
						<tr style="height:20px;"></tr>
						<tr>
							<td align="right"><p><span>*</span>手机号码：</p></td><td><input id="userTelephone" name="userTelephone" class="input-text" type="text" placeholder="请输入您店铺名称" /></td>
						</tr>
						<tr style="height:20px;"><td></td><td><p class="msg" id="telephonemsg"></p></td></tr>
						<tr>
							<td colspan="2" style="text-align:center"><p style="float:left;padding-left:30px;"><input id="register_btn" type="image" src="images/reg.jpg" />&nbsp;</p><p style="float:left" id="msg">我有京拍档账号，<a href="/login.jsp">直接登录</a></p></td>
						</tr>
					</table>
			</div>
		</div>
	</div>
	<div style="clear:left"></div>
	<div id="footer">
		<p>公司地址：北京市朝阳区北苑路媒体村天畅园8号楼2802室&nbsp;&nbsp;备案号：京ICP证030173号</p>
		<p>联系电话：010-56032090 13466730289   公司邮箱：wangwenfeng@jingpaidang.com</p>
		<p>Copyright©2005-2013京拍档All Rights Reserved</p>
	</div>
	<script src="js/jquery-1.9.0.js" type="text/javascript"></script>
	<script src="colorbox/jquery.colorbox.js" type="text/javascript"></script>
	<script src="js/reg.js" type="text/javascript"></script>
</body>
</html>