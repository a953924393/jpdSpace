<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>
        登录页面

    </title>

</head>

<body>
       <form action="/example/insert" method="post">
           名称：<input type="text" name="name">         <br>
           婚否： <p></p>
                 <input type="radio" name="married" value="0">
                 <input type="radio" name="married" value="1">      <br>
           创建日期：<input type="text" name="createTime">      <br>
           <input type="submit" value="提交"/>
       </form>
</body>
</html>
