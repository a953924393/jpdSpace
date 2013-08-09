<form action="${rootUrl}/convert/convert!createRule.action" method="post">
    <div class="tab-content">
        <!-- basic-info-table: 规则基本信息-->
        	<table width="988" cellpadding="0" cellspacing="0" style="border:0;">
        		<tr>
					<td class="list-title" colspan="8">类目转换规则</td>
				</tr>
				<tr>
					<th>源网店</th>
                	<th>源网店类目</th>
                	<th>目标网店</th>
                	<th>目标网店类目</th>
				</tr>
				<tr>
	                <td>${convertRule.srcShopName}
	                    <input type="hidden" name="convertRule.srcAccountId" value="${convertRule.srcAccountId}">
	                </td>
	                <td>${convertRule.srcCategoryName}
	                    <input type="hidden" name="convertRule.srcCategoryId" value="${convertRule.srcCategoryId}">
	                </td>
	                <td>${convertRule.targetShopName}
	                    <input type="hidden" name="convertRule.targetAccountId" value="${convertRule.targetAccountId}">
	                </td>
	                <td>${convertRule.targetCategoryName}
	                    <input type="hidden" name="convertRule.targetCategoryId" value="${convertRule.targetCategoryId}">
	                </td>
            	</tr>
			</table>
        <!--End basic-info-table -->
        <div class="shop">
            <table width="988" cellpadding="0" cellspacing="0" style="border:0;">
				<tr>
                    <th width="5%">序列号</th>
                    <th width="17%">目标网店类目属性</th>
                    <th width="10%">是否需要转换</th>
                    <th width="20%">源网店类目属性</th>
                    <th width="20%">源网店类目属性值</th>
                    <th width="20%">目标网店类目属性值</th>
                    <th width="8%">增加配置</th>
                </tr>
				<tbody id="property-mapping-table">
	                <#list targetProperties as targetProperty>
	                <tr id="rule-item-${targetProperty.id}">
	                    <td>${targetProperty_index + 1}</td>
	                    <#if targetProperty.required == 'true'>
	                        <td>${targetProperty.propertyName}<span style="color: #ff0000">*</span></td>
	                    <#else>
	                        <td>${targetProperty.propertyName}</td>
	                    </#if>
	                    <td>
	                        <input type="hidden" name="convertRuleDetails[${targetProperty_index}].targetPropertyId"
	                               value="${targetProperty.id}">
	                        <input type="checkbox" value="${targetProperty.isNeedConvertDesc}">
	                    </td>
	                    <td colspan="1">
	                        <select name="convertRuleDetails[${targetProperty_index}].srcPropertyId"
	                                id="src-property-${targetProperty.id}"
	                                keyProp="${targetProperty.id}">
	
	                            <#list srcProperties as srcProperty>
	                                <option value="${srcProperty.id}">${srcProperty.propertyName}</option>
	                            </#list>
	                        </select>
	                    </td>
	                    <td colspan="1">
	                        <select name="convertRuleDetails[${targetProperty_index}].srcPropertyValueId"
	                                id="src-value-${targetProperty.id}">
	
	                        </select>
	                    </td>
	                    <td colspan="1">
	                        <select name="convertRuleDetails[${targetProperty_index}].targetPropertyValueId"
	                                id="target-value-${targetProperty.id}">
	
	                            <#list targetProperty.categoryPropertyValues as targetPropVal>
	                                <option value="${targetPropVal.id}">${targetPropVal.propertyValue}</option>
	                            </#list>
	                        </select>
	                    </td>
	                    <td style="text-align:center;">
	                    	<img src="/images/add.jpg" id="${targetProperty.id}" />
	                    </td>
	                </tr>
	                </#list>
                </tbody>
			</table>
        </div>
        <!--shopdiv=end-->
        <p style="margin:10px 0 20px 10px;">
        	<input type="image" src="/images/confirm.jpg" id="submit-rule-mapping">
        </p>
    </div>
</form>