<!DOCTYPE html>
<html lang="en">
<#include "/WEB-INF/template/common/common_header.ftl">

<body>
<#include "/WEB-INF/template/common/common_navbar.ftl">

<div class="container-fluid">
    <div class="row-fluid">
    <#include "/WEB-INF/template/common/common_sidebar.ftl">

        <!--当前页面主要内容-->
        <div class="span9">
            <!--header-->
            <div id="header">
                <h1>一键搬家</h1>
                <ul>
                    <li><img src="/img/icon_1.gif"/></li>
                    <li><img src="/img/icon_2.gif"/></li>
                    <li>平台转换规则设置</li>
                </ul>
            </div>
            <!--header==end-->
        </div>
        <div class="span9">
            <!--guild-->
            <div class="guild">
                <dl>
                    <dt><code><img src="/img/icon_3.gif"/></code>公告</dt>
                    <dd>1、支持淘宝，天猫搬家到京东店。</dd>
                    <dd>2、淘宝端需要每天授权。点击首页的授权按钮或者到淘宝后台点击授权</dd>
                    <dd>3、有任何问题，请致电15652515060或QQ：953924393<a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=953924393&site=qq&menu=yes"><img border="0" src="http://wpa.qq.com/pa?p=2:953924393:41" alt="点击这里给我发消息" title="点击这里给我发消息"/></a></dd>
                </dl>
            </div>
            <!--guild==end-->
            <div class="row-fluid">
                <div class="tabbable"> <!-- Only required for left/right tabs -->
                    <div class="tab-content">
                        <!--shop-->
                        <div class="shop">
                            <table class="table table-hover">
                                <thead>
                                <tr>
                                    <th>
                                        <input id="checkAllItems" name="checkAllItems" type="checkbox">
                                    </th>
                                    <th>图片</th>
                                    <th>商品货号</th>
                                    <th>商品名称</th>
                                    <th>商品类目</th>
                                    <th>市场价</th>
                                    <th>销售价</th>
                                    <th>库存</th>
                                    <th>搬家状态</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td><input name="itemId" type="checkbox" value=""/></td>
                                    <td><img src="" class="img-polaroid"></td>
                                    <td>123</td>
                                    <td><a target="_blank" href="">.....</a></td>
                                    <td>coat</td>
                                    <td>112</td>
                                    <td>112</td>
                                    <td>11</td>
                                    <td>
                                        <button class="btn btn-mini btn-primary" type="button">OPT</button>
                                    </td>
                                    <td>
                                        <button class="btn btn-mini" type="button">OPT</button>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                            <!--msg-->
                            <div class="msg1">
                                <p>查询结果只有5条记录</p>

                                <p><span class="fr">当商品不在<a href="#"><img src="/img/btn.gif" alt="下载商品"
                                                                          title="下载商品"/></a></span></p>
                            </div>
                            <!--msg--end-->
                        </div>
                        <!--shopdiv=end-->
                    </div>
                </div>
            </div>

        </div>
    </div>

    <script src="http://code.jquery.com/jquery.js"></script>
    <script type="text/javascript" src="/js/move.js"></script>
</body>
</html>