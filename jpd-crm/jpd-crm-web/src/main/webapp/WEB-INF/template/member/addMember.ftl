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
       <form action="/member/addMember" method="post">
           京东id ：<input type="text" name="memberJdId" id="memberJdId">         <br>
           会员等级 ：<input type="text" name="memberLevel" id="memberLevel">         <br>
           会员积分 ：<input type="text" name="memberIntegral" id="memberIntegral">         <br>
           会员生日 ：<input type="text" name="memberBirthday" id="memberBirthday">         <br>
           会员姓名 ： <input type="text" name="memberName" id="memberName">         <br>
           会员地址 ： <input type="text" name="memberAddress" id="memberAddress">         <br>
           会员电话 ： <input type="text" name="memberTelephone" id="memberTelephone">         <br>
           会员邮箱 ： <input type="text" name="memberEmail" id="memberEmail">         <br>
           会员京东用户名：   <input type="text" name="memberJdName" id="memberJdName">         <br>
          <#--婚否： <p></p>
                 <input type="radio" name="married" value="0">
                 <input type="radio" name="married" value="1">      <br>
           创建日期：<input type="text" name="createTime">      <br>     -->
           <input type="submit" value="提交"/>
       </form>
</body>
</html>
