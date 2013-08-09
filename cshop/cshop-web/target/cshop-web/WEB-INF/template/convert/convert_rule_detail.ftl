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
			<#include "/WEB-INF/template/convert/convert_rule_detail_form.ftl">
		</div>
	</div>
</div>
    <script>
        $(function () {
            $("[id^='src-property']").change(function () {
                var propId = $(this).val();
                var keyProp = $(this).attr("keyProp");
                $.getJSON("/convert/convert!categoryPropertyValues.action", {propertyId: propId}, function (data) {
                    $("#src-value-" + keyProp).html("<option value='-1'>请选择...</option>");
                    $.each(data, function (index, item) {
                        $("#src-value-" + keyProp).append("<option value=" + item.id + ">" + item.propertyValue + "</option>");
                    });
                })
            });

//            $(".icon-plus").click(function () {
//                var id = $(this).attr("id");
//                var $ruleItem = $("#rule-item-" + id);
//                var $srcProp = $("#src-property-" + id).clone();
//                var $srcVal= $("#src-value-" + id).clone();
//                $ruleItem.after("<tr><td colspan='3'></td><td>" + $srcProp[0].outerHTML +"</td>");
//                console.log("after ok");
//            });
        });
    </script>
</body>
</html>