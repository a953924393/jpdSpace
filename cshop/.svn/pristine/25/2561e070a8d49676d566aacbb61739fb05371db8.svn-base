<!DOCTYPE html>
<html lang="en">
<head>
    <title>示例列表</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!--BootStrap-->
    <link href="/css/bootstrap.min.css" rel="stylesheet" media="screen">
</head>
<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span2"></div>
        <div class="span10">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th><input type="checkbox"></th>
                    <th>用户名</th>
                    <th>描述</th>
                </tr>
                </thead>
                <tbody>
                <#list examples as example>
                <tr>
                    <td><input type="checkbox" name="ids" value="${example.id}"/></td>
                    <td>${example.name}</td>
                    <td>${example.desc}</td>
                </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script src="http://code.jquery.com/jquery.js"></script>
<script src="/js/bootstrap.min.js"></script>
</body>
</html>