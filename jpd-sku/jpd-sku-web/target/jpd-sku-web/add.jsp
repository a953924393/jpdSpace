<%--
  Created by IntelliJ IDEA.
  User: jackchen
  Date: 7/8/13
  Time: 8:17 AM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>SKU查询</title>
    <link rel="stylesheet" href="css/sku.css">
    <script src="js/jquery-1.9.0.js"></script>
</head>

<body>
<div id="site-nav">
    <div id="header">
        <p class="nav-title">京拍档SKU搜索助手</p>

        <p class="nav-login">
            <%
                if (session.getAttribute("user") != null && !"".equals(session.getAttribute("user"))) {
            %>
            <%=session.getAttribute("user") %>|<a href="/loginout">退出</a>
            <%
            } else {
            %>
            <a href="/login.jsp">登录</a>|<a href="/register.jsp">注册</a>
            <%
                }
            %>
        </p>
    </div>
</div>

<div id="content">
    <div class="form-content">
        <form action="/addSku" method="post">
            <textarea id="keywordList" class="keywordText" name="keywordList" placeholder="填写需要录入的sku，每行填写一个词"></textarea>  <br>

            <input type="submit" onclick="return judge()" value="提交查询任务"  >
        </form>
    </div>
</div>

<div id="footer">
    <p>有任何问题，请致电15652515060或QQ：953924393</p>

    <p>邮箱：jishu@jingpaidang.com</p>
</div>

<script>
    var judge = function() {
        var value = $("#keywordList").val();

        if(value == null || $.trim(value) == "" ) {
            return false;
        }  else {
            alert("提交成功！")
            return true;
        }

    }

</script>
</body>
</html>
