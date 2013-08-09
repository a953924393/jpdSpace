<!DOCTYPE html>
<html lang="en">
<#include "/WEB-INF/template/common/common_header.ftl">
<script type="text/javascript" src="/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="/js/jquery.metadata.js"></script>
<link href="/css/bootstrap.css" rel="stylesheet">
<link href="/css/bootstrap-responsive.css" rel="stylesheet">
<link href="/css/style.css" rel="stylesheet">
<style type="text/css">
        body {
            padding-top: 60px;
            padding-bottom: 40px;
        }

        .sidebar-nav {
            padding: 9px 0;
        }

        @media (max-width: 980px) {
            /* Enable use of floated navbar text */
            .navbar-text.pull-right {
                float: none;
                padding-left: 5px;
                padding-right: 5px;
            }
        }
    </style>
<body>
<div class="container">
    <div class="row-fluid">
        <div class="span12">
            <div class="row-fluid">
                <div class="span6">
                    <form name="form1" id="formLogin" method="post" action="${rootUrl}/user/user!loginAndRelate.action">


                        <fieldset>
                            <legend>登录</legend>
                            <label for="userEmail">邮箱</label>
                            <input type="hidden" name="own" value="2">
                            <input type="hidden" name="accountId" value="${accountId}">
                            <input type="text" name="userEmail" placeholder="请输入京商店注册邮箱">
                            <label class="error">${errorMsg} </label>

                            <label for="jshopUserPassword">密码</label>
                            <input type="password" name="jshopUserPassword" placeholder="请输入在京商店的账户密码">

                            <#--<label for="shopName">店铺名称</label>-->
                            <#--<input type="text" name="shopName" placeholder="请输入平台店铺名称">-->

                            <label for="submit">
                                <button type="submit" name="submit" class="btn">开始使用</button>
                            </label>
                        </fieldset>
                    </form>
                </div>
                <div class="span6">
                    <form name="formRegist" id="formRegist" method="post" action="${rootUrl}/user/user!registAndRelate.action">


                        <fieldset>
                            <legend>注册</legend>
                            <input type="hidden" name="own" value="1">

                            <input type="hidden" name="accountId" value="${accountId}">
                            <#--<label for="shopName">店铺名称</label>-->
                            <#--<input type="text" name="shopName" placeholder="请输入平台店铺名称">-->

                            <label for="userEmail">邮箱</label>
                            <input type="text" name="userEmail" id="formRegist_email" placeholder="请输入邮箱">

                            <label for="userQq">QQ</label>
                            <input type="text" name="userQq" placeholder="请输入qq">

                            <label for="jshopUserPassword">密码</label>
                            <input type="password" name="jshopUserPassword" id="formRegist_jshopUserPassword" placeholder="请输入qq">

                            <label for="confirmPassword">确认密码</label>
                            <input type="password" name="confirmPassword" placeholder="请输入qq">

                            <label>
                                <button type="submit" class="btn">开始使用</button>
                            </label>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    $().ready(function() {
        $("#formLogin").validate({
            rules: {
                userEmail: {
                    required: true,
                    email: true
                },
                jshopUserPassword: {
                    required: true,
                    minlength: 5
                }
            },
            messages: {
                userEmail: {
                    required: "请输入Email地址",
                    email: "请输入正确的email地址"
                },
                jshopUserPassword: {
                    required: "请输入密码",
                    minlength: "密码不能小于{5}个字 符"
                }
            }
        });
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
                            'model.userEmail': function() {
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
                }
            }
        });
    });
</script>

</body>
</html>

