<!DOCTYPE html>
<html lang="en">
<#include "/WEB-INF/template/common/common_header.ftl">
<link href="/css/rule.css" rel="stylesheet">
<body>
<#include "/WEB-INF/template/common/common_navbar.ftl">

<div id="content">
	<#include "/WEB-INF/template/common/common_sidebar.ftl">
	<div class="right">
		<div id="breadcrumb">
			店铺管理&nbsp;&gt;&gt;规则设置
		</div>
		<div style="clear:left;"></div>
		<div class="list">
			<table width="988" cellpadding="0" cellspacing="0" style="border:0;">
				<tr>
					<td class="list-title" colspan="8">京东类目规则</td>
				</tr>
				<tr>
					<th width="5%">选择</th>
					<th width="12%">规则编码</th>
					<th width="12%">源网店</th>
					<th width="18%">源网店类目</th>
					<th width="12%">目标网店</th>
					<th width="18%">目标网店类目</th>
					<th width="18%">创建日期</th>
					<th width="5%">操作</th>
				</tr>
				<#if convertRules.size() != 0>
                	<#list convertRules as convertRule>
					<tr>
						<td><input type="checkbox"  name="checkbox"value="${convertRule.id}"></td>
						<td>${convertRule.ruleEncoding}</td>
						<td>${convertRule.srcShopName}</td>
						<td>${convertRule.srcCategoryName}</td>
						<td>${convertRule.targetShopName}</td>
						<td>${convertRule.targetCategoryName}</td>
						<td>${convertRule.createTimeDesc}</td>
						<td>修改</td>
					</tr>
					</#list>
                <#else>
                    <tr>
                       <td colspan="8" align="center"><font color="red" style="font-size:24px;font-weight:bold;">暂无匹配规则数据!</font></td>
                  	</tr>
                </#if>
			</table>
			<div class="list-bottom">
				<input id="new-rule" type="image" src="/images/new_btn.jpg"/>&nbsp;
				<input id="delselectconvertRule" type="image" src="/images/del_btn.jpg"/>
			</div>
		</div>
	</div>
</div>
    <div id="new-rule-dialog-form" title="新增转换规则">
        <p class="validateTips">所有选项均为必选项</p>

        <form action="${rootUrl}/convert/convert!ruleDetail.action" method="post">
            <fieldset>
                <label for="src-shop-sel">源网店</label>
                <select class="span6" name="convertRule.srcAccountId" id="src-account-sel">
                    <option>请选择...</option>
                </select>
                <label for="src-category-sel">源网店类目</label>
                <select class="span6" name="convertRule.srcCategoryId" id="src-category-sel">
                    <option>请选择...</option>
                </select>
                <label for="target-account-sel">目标网店</label>
                <select class="span6" name="convertRule.targetAccountId" id="target-account-sel">
                    <option>请选择...</option>
                </select>
                <label for="target-category-sel">目标网店类目</label>
                <select class="span6" name="convertRule.targetCategoryId" id="target-category-sel">
                    <option>请选择...</option>
                </select>
                <input id="submit-new-rule-form" type="submit" style="visibility: hidden;" value="确认">
            </fieldset>
        </form>
    </div>
    <script src="/js/script/convert.js"></script>
    <script type="text/javascript" src="/js/del_convertRule.js"></script>
</body>
</html>