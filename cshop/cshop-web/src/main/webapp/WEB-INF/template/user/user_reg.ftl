<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>
        注册页面
    </title>
    <link href="/css/index.css" rel="stylesheet" type="text/css"/>

    <link href="/css/style.css" rel="stylesheet" type="text/css"/>

    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="/js/jquery.validate.min.js"></script>
</head>
<script type="text/javascript" src="/js/jquery.metadata.js"></script>

<body>
<div id="header-wrapper">
    <div class="logo">
        <div class="logo1"><img src="/img/02.jpg" width="224" height="40" alt=""/></div>
        <div class="logo2"><img src="/img/04.jpg" alt=""/></div>
    </div>

</div>
<div id="page">
    <div class="left_pic"><img src="/img/login_left.jpg" width="495" height="432" alt=""/></div>
    <div class="login">
        <div class="login_title">
            <img src="/img/08.jpg" width="399" height="44" alt=""/>
        </div>

        <form name="form1" id="formLogin" method="post" action="${rootUrl}/user/user!reg.action">
            <div class="_login">

                <input type="text" name="userEmail" class="form_name"  id="formRegist_email" placeholder="请输入邮箱">

                <input type="text" name="userQq" class="form_name"  placeholder="请输入qq">

                <input type="password" name="jshopUserPassword" class="form_name"  id="formRegist_jshopUserPassword" placeholder="请输入密码">

                <input type="password" name="confirmPassword" class="form_name"  placeholder="请确认密码">


                <div class="bj"><input name="" class="box" type="checkbox"/>记住用户名
                    <input name="" class="box" type="checkbox"/>记住密码
                </div>



            </div>

            <div class="button">
                <button type="submit" name="submit" class="regButton" id="submit"></button>
                <a href="${rootUrl}/user/user!loginPage.action">已有账户，直接登录</a>

                <div class="button3">
                    <div class="forget">忘记密码?</div>
                    <div class="forget"><a href="#">立即找回</a></div>
                </div>
            </div>

        </form>

    </div>
</div>


<div id="bottom">
    <li>&nbsp</li>
    <li>&nbsp</li>
    <li>&nbsp</li>
    <li>&nbsp</li>
    <li>&nbsp</li>
    <li>公司地址：北京市朝阳区北苑路媒体村天畅园8号楼2802室 备案号：京ICP证030173号</li>
    <li>联系电话：010-56032090 13466730289 公司邮箱：wangwenfeng@jingpaidang.com</li>
    <li>Copyright © 2005-2012 京拍档 All Rights Reserved</li>
</div>

<script>
    $().ready(function () {

        $("#formRegist").validate({
            rules: {
                userEmail: {
                    required: true,
                    email: true,
                    remote: {
                        url: "/user/user!validateEmail.action",         //后台处理程序
                        type: "post",                             //数据发送方式
                        dataType: "json",                     //接受数据格式
                        data: {                                         //要传递的数据，默认已传递应用此规则的表单项
                            'model.userEmail': function () {
                                return $("#formRegist_email").val();
                            }
                        }
                    }
                },
                jshopUserPassword: {
                    required: true,
                    minlength: 5
                },
                confirmPassword: {
                    required: true,
                    equalTo: "#formRegist_jshopUserPassword"
                },
                shopName: {
                    required: true,
                    minlength: 3

                }
            },
            messages: {
                userEmail: {
                    required: "请输入Email地址",
                    email: "请输入正确的email地址",
                    remote: "用户名已经被注册"
                },
                jshopUserPassword: {
                    required: "请输入密码",
                    minlength: "密码不能小于{5}个字 符"
                },
                confirmPassword: {
                    required: "请确认密码",
                    equalTo: "两次输入不一致"
                },
                shopName: {
                    required: "请输入平台的店铺名称",
                    minlength: "店铺名不能小于3个字符"
                }
            }
        });
    });
</script>
</body>
</html>
