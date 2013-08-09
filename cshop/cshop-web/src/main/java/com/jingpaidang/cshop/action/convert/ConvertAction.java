/*
 * Copyright 2011-2012 the original author or authors.
 *
 *  http://www.jingpaidang.com
 */

package com.jingpaidang.cshop.action.convert;

import com.jd.open.api.sdk.JdException;
import com.jingpaidang.cshop.action.admin.BaseAction;
import com.jingpaidang.cshop.domain.category.CategoryProperty;
import com.jingpaidang.cshop.domain.category.CategoryPropertyValue;
import com.jingpaidang.cshop.domain.convert.ConvertRuleDetail;
import com.jingpaidang.cshop.domain.convert.PlatformConvertRule;
import com.jingpaidang.cshop.domain.user.User;
import com.jingpaidang.cshop.service.category.CategoryPropertyService;
import com.jingpaidang.cshop.service.convert.ConvertRuleService;
import com.taobao.api.ApiException;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类目转换规则Action.
 *
 * @author Thomson Tang
 * @version 1.0-SNAPSHOT
 * @date 6/25/13
 */

@ParentPackage("convert")
@Result(name = "convert_rule_list_action", location = "convert!list", type = "redirectAction")
public class ConvertAction extends BaseAction {
    private static final Logger logger = LoggerFactory.getLogger(ConvertAction.class);

    private static final String RULE_LIST = "convert_rule_list";
    private static final String RULE_DETAIL = "convert_rule_detail";
    private static final String SUCCESS_CREATE_CONVERT_RULE = "/convert/convert!list.action";
    private static final String RULE_LIST_ACTION = "convert_rule_list_action";

    @Resource
    private ConvertRuleService convertRuleService;
    @Resource
    private CategoryPropertyService categoryPropertyService;

    private PlatformConvertRule convertRule;
    private List<PlatformConvertRule> convertRules;
    private List<CategoryProperty> targetProperties;
    private List<CategoryProperty> srcProperties;
    private List<CategoryPropertyValue> targetPropertyValues;
    private List<ConvertRuleDetail> convertRuleDetails;
    private ConvertRuleDetail convertRuleDetail;
    private long categoryId;
    private long propertyId;
    private String ruleId;    //Receive id from convert_rule_list.ftl
    private String signBite; //Receive massage of del_sign_bite


    /**
     * delete convertRule
     */
    public String deleteConvertRule() throws Exception {
        Map m = new HashMap();
        if ("single".equals(signBite)) {
            m = convertRuleService.deleteConvertRule(ruleId);
        }
        if (signBite.equals("someselect")) {
            ruleId = ruleId.substring(0, ruleId.length() - 1);
            String convertRulearra[] = ruleId.split(",");
            for (int i = 0; i < convertRulearra.length; i++) {
                m = convertRuleService.deleteConvertRule(convertRulearra[i]);
            }
        }
        return this.ajax((String) m.get("msg"));
    }

    public String updateRuleDetail() {
        try {
            convertRuleService.updateConvertRuleAndDetail(convertRule, convertRuleDetails);
        } catch (Exception e) {
            logger.error("********* 修改转换规则详情失败！", e);
            return this.ajax(Status.error);
        }

        return RULE_LIST_ACTION;
    }


    /**
     * 展示创建转换规则详情信息页
     *
     * @return
     */
    public String ruleDetail() {
        if (null != convertRule) {
            //下载类目属性和属性值
            try {
                convertRuleService.downloadPropsAndVals4ConvertRule(convertRule);
                //为转换详情展示目标属性和属性值集
                targetProperties = convertRuleService.findTargetPropsAndVals4ConvertRule(convertRule);
                //为转换详情展示源属性和属性值
                srcProperties = categoryPropertyService.findCategoryPropertyListByCategoryId(convertRule.getSrcCategoryId());
                return RULE_DETAIL;
            } catch (JdException e) {
                logger.info(e.getErrMsg());
                redirectUrl = "../oauth/jdOauth";
                return redirectUrl;
            } catch (ApiException e) {
                logger.info(e.getErrMsg());
                redirectUrl = "../oauth/tbOauth";
                return redirectUrl;
            }
        }

        return ERROR;
    }

    /**
     * 显示所有的配置文件列表
     *
     * @return
     */
    public String list() {
        long userId = ((User) getSession("user")).getId();
        convertRules = convertRuleService.findAllPlatformConvertRulesByUserId(userId);
        return RULE_LIST;
    }

    /**
     * 依据类目主键ID获取属性集
     *
     * @return
     */
    public String categoryProperties() {
        return this.ajax(categoryPropertyService.findCategoryPropertyListByCategoryId(categoryId));
    }

    /**
     * 依据属性主键ID获取属性值集
     *
     * @return
     */
    public String categoryPropertyValues() {
        return this.ajax(categoryPropertyService.findCategoryPropValListByPropId(propertyId));
    }

    /**
     * 创建转换规则详情
     *
     * @return
     */
    public String createRule() {
        try {
            if (null != convertRule) {
                long userId = ((User) getSession("user")).getId();
                convertRule.setJshopUserId(userId);
            }
            convertRuleService.createConvertRuleAndDetail(convertRule, convertRuleDetails);
            redirectUrl = SUCCESS_CREATE_CONVERT_RULE;
        } catch (Exception e) {
            logger.error("********* 创建转换规则详情失败！", e);
            return this.ajax(Status.error);
        }
        return SUCCESS;
    }

    /**
     * 转换规则选择列表展示
     *
     * @return
     */
    public String convertRules() {
        try {
            long userId = ((User) getSession("user")).getId();
            return this.ajax(convertRuleService.findAllPlatformConvertRulesByUserId(userId));
        } catch (Exception e) {
            logger.error("********* 查询转换规则失败！", e);
            return this.ajax(Status.error);
        }
    }

    public PlatformConvertRule getConvertRule() {
        return convertRule;
    }

    public void setConvertRule(PlatformConvertRule convertRule) {
        this.convertRule = convertRule;
    }

    public List<PlatformConvertRule> getConvertRules() {
        return convertRules;
    }

    public void setConvertRules(List<PlatformConvertRule> convertRules) {
        this.convertRules = convertRules;
    }

    public List<CategoryProperty> getTargetProperties() {
        return targetProperties;
    }

    public void setTargetProperties(List<CategoryProperty> targetProperties) {
        this.targetProperties = targetProperties;
    }

    public List<CategoryPropertyValue> getTargetPropertyValues() {
        return targetPropertyValues;
    }

    public void setTargetPropertyValues(List<CategoryPropertyValue> targetPropertyValues) {
        this.targetPropertyValues = targetPropertyValues;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public List<CategoryProperty> getSrcProperties() {
        return srcProperties;
    }

    public long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(long propertyId) {
        this.propertyId = propertyId;
    }

    public void setSrcProperties(List<CategoryProperty> srcProperties) {
        this.srcProperties = srcProperties;
    }

    public ConvertRuleDetail getConvertRuleDetail() {
        return convertRuleDetail;
    }

    public void setConvertRuleDetail(ConvertRuleDetail convertRuleDetail) {
        this.convertRuleDetail = convertRuleDetail;
    }

    public List<ConvertRuleDetail> getConvertRuleDetails() {
        return convertRuleDetails;
    }

    public void setConvertRuleDetails(List<ConvertRuleDetail> convertRuleDetails) {
        this.convertRuleDetails = convertRuleDetails;
    }

    public String getSignBite() {
        return signBite;
    }

    public void setSignBite(String signBite) {
        this.signBite = signBite;
    }

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }
}
