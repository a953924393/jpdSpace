<!DOCTYPE html>
<html lang="en">
<#include "/WEB-INF/template/common/common_header.ftl">
<link rel="stylesheet" href="/js/colorbox/colorbox.css" />
<link href="/css/move.css" rel="stylesheet">
<body>
<#include "/WEB-INF/template/common/common_navbar.ftl">
<div id="content">
	<#include "/WEB-INF/template/common/common_sidebar.ftl">
	<div class="right">
		<div id="breadcrumb">
			店铺管理&nbsp;&gt;&gt;一键搬家
		</div>
		<div style="clear:left;"></div>
		<div class="move">
			<div class="move_step">
				<ul>
					<li style="margin-left:0px;"><img src="/images/step2_1.jpg"></li>
					<li><img src="/images/step2.jpg"></li>
					<li><img src="/images/nostep3.jpg"></li>
				</ul>
                <input type="hidden" id="tbAppKey" value="${tbAppKey}">
                <input type="hidden" id="tbRedirectUri" value="${tbRedirectUri}">
                <input type="hidden" id="jdAppKey" value="${jdAppKey}">
                <input type="hidden" id="jdRedirectUri" value="${jdRedirectUri}">
			</div>
			<table width="988" cellpadding="0" cellspacing="0" style="border:0; background-color:#FFFFFF;">

				<tr>
					<td class="move_des" colspan="2">源网店（要搬家的店铺,目前只能从淘宝到京东，如果没有显示淘宝店铺，<a href="https://oauth.taobao.com/authorize?response_type=code&client_id=${tbAppKey}&redirect_uri=${tbRedirectUri}">请点击淘宝授权</a>））</td>
				</tr>
				<tr>
					<td class="move_c_l" width="15%">源网店：</td>
					<td class="move_c_r">
						<select name="accountId" id="accounts">
							<option value="-1">请选择...</option>
						</select><span style="color:red;" id="div1"></span>
						<p>如果搜索结果中商品不全，请点击下载商品<a onclick="move.downloadWares();" href="javascript:void(0);">下载商品</a></p>
					</td>
				</tr>
				<tr>
					<td class="move_des" colspan="2">源网店类目</td>
				</tr>
				<tr>
					<td class="move_c_l" width="15%">源网店类目：</td>
					<td class="move_c_r">
						<select name="categoryId" id="categories">
							<option>请选择...</option>
						</select><span style="color:red;" id="div2"></span>
						<p>如果搜索结果中类目不全，请点击下载类目<a href="javascript: void(0)"
                                                          id="downloadCategories">下载类目</a></p>
					</td>
				</tr>
				<tr>
					<td class="move_des" colspan="2">商品关键字/商品ID</td>
				</tr>
				<tr>
					<td class="move_c_l" width="15%">商品名称：</td>
					<td class="move_c_r">
						<input type="text" name="queryKey" placeholder="请输入商品名称..." />
					</td>
				</tr>
				<tr>
					<td class="move_c_l" width="15%">商品ID：</td>
					<td class="move_c_r">
						<input type="text" name="itemId" placeholder="请输入商品ID..." />
					</td>
				</tr>
				<tr>
					<td class="move_des" colspan="2">商品搬家状态</td>
				</tr>
				<tr>
					<td class="move_c_l" width="15%"></td>
					<td class="move_c_r">
						<input type="radio"  name="moved" value="0" />&nbsp;首次搬家&nbsp;&nbsp;&nbsp;<input type="radio"  name="moved" value="1" />&nbsp;已经搬家
					</td>
				</tr>
				<tr>
					<td class="move_des" colspan="2">商品在售状态</td>
				</tr>
				<tr>
					<td class="move_c_l" width="15%"></td>
					<td class="move_c_r">
						<input type="radio" name="sale" value="onsale" />&nbsp;在售&nbsp;&nbsp;&nbsp;<input type="radio" name="sale" value="instock" />&nbsp;库存中
					</td>
				</tr>
			</table>
			<div class="move_bottom">
				<p><input type="image" id="submit-query-form" src="/images/move_r4_c2.jpg" /></p><p>&nbsp;如果搜索结果中商品不全，请点击下载商品<a href="#">下载商品</a></p>
			</div>
			<div id="ware-item-list" class="shop">
		</div>
	</div>
</div>
<script type="text/javascript" src="/js/move.js"></script>
<script type="text/javascript" src="/js/colorbox/jquery.colorbox.js"></script>
</body>
</html>
