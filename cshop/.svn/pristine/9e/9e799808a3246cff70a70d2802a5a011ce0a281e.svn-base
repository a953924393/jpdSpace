<!DOCTYPE html>
<html lang="en">
<#include "/WEB-INF/template/common/common_header.ftl">
<link rel="stylesheet" href="/js/colorbox/colorbox.css" />
<link href="/css/rule.css" rel="stylesheet">
<link href="/css/history.css" rel="stylesheet">
<body>
<#include "/WEB-INF/template/common/common_navbar.ftl">

<div id="content">
	<#include "/WEB-INF/template/common/common_sidebar.ftl">
	<div class="right">
		<div id="breadcrumb">
			店铺管理&nbsp;&gt;&gt;搬家历史
		</div>
		<div style="clear:left;"></div>
		<div class="list">
			<div class="search">
				<form action="/history/history!moveHistoryList.action" method="post">
				<ul>
					<li>源&nbsp;&nbsp;网&nbsp;&nbsp;店：
						<select name="accountId" id="accounts">
							<option></option>
						</select>
					</li>
					<li>商品编号：<input type="text" name="itemId" value="${itemId}" /></li>
					<li>商品名称：<input type="text" name="itemName" value="${itemName}" /></li>
					<li>商品状态：
						<select name="status">
							<option></option>
							<option value="0" <#if status==0>selected="selected"</#if>>上传失败</option>
							<option value="1" <#if status==1>selected="selected"</#if>>上传成功</option>
						</select>
					</li>
				</ul>
				<div style="clear:left;"></div>
				<p>
				<input type="image" src="/images/search.jpg" />
				</p>
				</form>
			</div>
			<table width="988" cellpadding="0" cellspacing="0" style="border:0;">
				<tr>
					<td class="list-title" colspan="10"></td>
				</tr>
				<tr>
					<th width="5%">选择</th>
					<th width="5%">图片</th>
					<th width="10%">商品货号</th>
					<th width="21%">商品名称</th>
					<th width="7%">销售价</th>
					<th width="15%">上传网店</th>
					<th width="10%">上传状态</th>
					<th width="15%">失败原因</th>
					<th width="7%">创建时间</th>
					<th width="5">操作</th>
				</tr>
				<#if moveHistoryList.size() != 0>
                	<#list moveHistoryList as history>
					<tr>
						<td><input type="checkbox"  name="checkbox" value="${history.id}"></td>
						<td><img src="${history.img}" width="50" height="50" /></td>
						<td>${history.itemId}</td>
						<td>${history.itemName}</td>
						<td>${history.price}</td>
						<td>${history.targetShopName}</td>
						<td><#if history.status==0 ><p style="color:red;">上传失败</p><#else>上传成功</#if></td>
						<td>${history.statusReason}</td>
						<td>${history.created?string("yyyy-MM-dd HH:mm:ss")}</td>
						<td></td>
					</tr>
					</#list>
                <#else>
                    <tr>
                       <td colspan="10" align="center"><font color="red" style="font-size:24px;font-weight:bold;">暂无搬家历史数据!</font></td>
                  	</tr>
                </#if>
			</table>
			<div class="list-bottom">
				<p><input type="checkbox" name="checkAll" id="checkAll" />&nbsp;全选</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;<input type="image" id="remove_btn" src="/images/remove.jpg" /></p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;<input type="image" id="del_btn" src="/images/del_btn.jpg" /></p>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="/js/colorbox/jquery.colorbox.js"></script>
<script type="text/javascript" src="/js/history.js"></script>
</body>
</html>