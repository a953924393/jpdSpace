<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@page isErrorPage="true" %>
<!DOCTYPE HTML>
<html lang="en-US">
<head>
    <meta charset="UTF-8">
    <title>提示信息500</title>
    <style type="text/css">
        * {
            padding: 0;
            margin: 0;
        }
        .errorBox {
            width:800px;
            height: 400px;
            margin: 20px auto;
            font-size: 24px;
            color: #333;
            background: url(../images/mituerrbg.jpg) no-repeat right top;
        }
        a { color: #0072C6;}
        .errorMessage {
            padding: 120px 0 40px;
        }
        .errorMessage b {
            font-family:georgia;
            font-size: 60px;
        }
        #detail-info {
            width:800px;
            margin: 10px auto;
        }
        #detail-info dd {
            padding:10px;
            background: #f8f8f8;
            line-height: 1.8;
            border-radius: 6px;
        }
        #detail-info dt {
            padding: 10px;
            cursor: pointer;
            color: #0072C6;
            font-size: 14px;
        }
        .hidden {
            display: none;
        }
        li {
            list-style: inside;
        }
    </style>
    <script type="text/javascript">
        window.onload = function(){
            var obj = document.getElementById("detail-info");
            var oDd = obj.getElementsByTagName("dd")[0];
            var oDt = obj.getElementsByTagName("dt")[0];
            //
            oDt.addEventListener("click",function(){
                var status = oDd.className;
                if(status == "hidden"){
                    oDd.className = "";
                }else{
                    oDd.className = "hidden";
                }
            },false);
        }
    </script>
</head>
<body class="errorPage">
<div class="body">
    <div class="errorBox">
        <div class="errorMessage">
            <b>500</b>系统出现异常,请与管理员联系!
        </div>
    </div>
    <dl id="detail-info">
        <dt>查看详细错误信息>></dt>
        <dd class="hidden">
           <%
    			out.println(exception);
			%>
        </dd>
    </dl>
</div>
</body>
</html>