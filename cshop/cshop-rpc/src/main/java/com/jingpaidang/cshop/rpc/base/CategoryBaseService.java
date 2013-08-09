package com.jingpaidang.cshop.rpc.base;

import com.jd.open.api.sdk.JdException;
import com.jd.open.api.sdk.domain.category.AttValue;
import com.jd.open.api.sdk.response.category.CategoryAttributeSearchResponse;
import com.jd.open.api.sdk.response.category.CategoryAttributeValueSearchResponse;
import com.jingpaidang.cshop.common.constant.BasicConstant;
import com.jingpaidang.cshop.common.utils.CommonUtil;
import com.jingpaidang.cshop.dao.category.ItemCategoryMapper;
import com.jingpaidang.cshop.dao.user.AccountMapper;
import com.jingpaidang.cshop.domain.category.CategoryProperty;
import com.jingpaidang.cshop.domain.category.CategoryPropertyValue;
import com.jingpaidang.cshop.domain.category.ItemCategory;
import com.jingpaidang.cshop.domain.user.Account;
import com.jingpaidang.cshop.rpc.jd.JdService;
import com.jingpaidang.cshop.rpc.tb.TbService;
import com.taobao.api.ApiException;
import com.taobao.api.domain.ItemProp;
import com.taobao.api.domain.PropValue;
import com.taobao.api.response.ItempropsGetResponse;
import com.taobao.api.response.ItempropvaluesGetResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 类目基础服务
 *
 * @author Thomson Tang
 * @version 1.0-SNAPSHOT
 * @date 6/26/13
 */
@Service("categoryBaseService")
public class CategoryBaseService {
    private static final Logger logger = LoggerFactory.getLogger(CategoryBaseService.class);

    @Resource
    private AccountMapper accountMapper;
    @Resource
    private ItemCategoryMapper itemCategoryMapper;
    @Resource
    private JdService jdService;
    @Resource
    private TbService tbService;


    /**
     * 根据店铺账户ID和类目主键ID获取该类目下的商品属性
     *
     * @param aid 账户ID
     * @param cid 类目主键ID
     * @return 类目属性集
     */
    public List<CategoryProperty> pullCategoryPropertyList(long aid, long cid) throws ApiException, JdException {
        int platformFlag = getAccountById(aid).getPlatformFlag();
        String accessToken = getAccountById(aid).getPlatformAccessToken();

        if (platformFlag == BasicConstant.PLATFORM_JING_DONG) {
            return pullJDCategoryPropertyList(accessToken, cid, platformFlag);
        } else if (platformFlag == BasicConstant.PLATFORM_TAO_BAO) {
            return pullTBCategoryPropertyList(cid);
        }
        return null;
    }

    public List<CategoryPropertyValue> pullCategoryPropertyValueList(long aid, long cid, long propertyId, long pid) throws ApiException, JdException {
        int platformFlag = getAccountById(aid).getPlatformFlag();
        String accessToken = getAccountById(aid).getPlatformAccessToken();

        if (platformFlag == BasicConstant.PLATFORM_JING_DONG) {
            return pullJDCategoryPropertyValueList(accessToken, propertyId, pid);
        } else if (platformFlag == BasicConstant.PLATFORM_TAO_BAO) {
            return pullTBCategoryPropertyValueList(cid, propertyId, pid, BasicConstant.PLATFORM_TAO_BAO);
        } else if (platformFlag == BasicConstant.PLATFORM_TIAN_MAO) {
            return pullTBCategoryPropertyValueList(cid, propertyId, pid, BasicConstant.PLATFORM_TIAN_MAO);
        }
        return null;
    }

    /**
     * 获取淘宝类目属性
     *
     * @param cid 类目主键ID
     * @return
     */
    private List<CategoryProperty> pullTBCategoryPropertyList(long cid) throws ApiException {
        long categoryId = getItemCategoryById(cid).getCategoryId();
        ItempropsGetResponse itempropsGetResponse = tbService.getCategoryAttribute(categoryId);
        List<ItemProp> itemProps = itempropsGetResponse.getItemProps();

        List<CategoryProperty> categoryProperties = new ArrayList<CategoryProperty>();
        for (ItemProp itemProp : itemProps) {
            CategoryProperty categoryProperty = new CategoryProperty();
            categoryProperty.setCategoryId(cid);
            categoryProperty.setPropertyId(itemProp.getPid());
            categoryProperty.setPropertyName(itemProp.getName());
            categoryProperty.setPlatformFlag(BasicConstant.PLATFORM_TAO_BAO);
            categoryProperty.setRequired(itemProp.getMust().toString());
            categoryProperty.setCreateTime(new Date());
            categoryProperty.setOperator("");
            categoryProperties.add(categoryProperty);
        }
        return categoryProperties;
    }

    /**
     * 获取淘宝/天猫类目属性值
     *
     * @param cid        类目主键ID
     * @param propertyId 属性ID
     * @param pid        属性主键ID
     * @param flag       平台标识
     * @return
     */
    private List<CategoryPropertyValue> pullTBCategoryPropertyValueList(long cid, long propertyId, long pid, int flag) throws ApiException {
        long categoryId = getItemCategoryById(cid).getCategoryId();
        ItempropvaluesGetResponse itempropvaluesGetResponse = tbService.getCategoryAttributeValue(categoryId, Long.toString(propertyId), Long.valueOf(flag));
        List<PropValue> propValues = itempropvaluesGetResponse.getPropValues();

        List<CategoryPropertyValue> categoryPropertyValues = new ArrayList<CategoryPropertyValue>();
        if (null != propValues) {
            for (PropValue propValue : propValues) {
                CategoryPropertyValue categoryPropertyValue = new CategoryPropertyValue();
                categoryPropertyValue.setPropertyId(pid);
                categoryPropertyValue.setPropertyValueId(propValue.getVid());
                categoryPropertyValue.setPropertyValue(propValue.getName());
                categoryPropertyValue.setCreateTime(new Date());
                categoryPropertyValue.setOperator("");
                categoryPropertyValues.add(categoryPropertyValue);
            }
        }
        return categoryPropertyValues;
    }

    /**
     * 获取京东类目属性值
     *
     * @param accessToken 访问token
     * @param propertyId  属性ID
     * @param pid         属性主键ID
     * @return 类目属性值集
     */
    private List<CategoryPropertyValue> pullJDCategoryPropertyValueList(String accessToken, long propertyId, long pid) throws JdException {
        CategoryAttributeValueSearchResponse valueSearchResponse = jdService.getCategoryAttributeValue(accessToken, Long.toString(propertyId));
        List<AttValue> attValues = valueSearchResponse.getAttValues();

        List<CategoryPropertyValue> propertyValues = new ArrayList<CategoryPropertyValue>();
        for (AttValue attValue : attValues) {
            CategoryPropertyValue propertyValue = new CategoryPropertyValue();
            propertyValue.setPropertyId(pid);
            propertyValue.setPropertyValueId(attValue.getVid());
            propertyValue.setPropertyValue(attValue.getName());
            propertyValue.setCreateTime(new Date());
            propertyValue.setOperator("");
            propertyValues.add(propertyValue);
            logger.info("========= add a propertyValue: " + propertyValue.getPropertyId() + "--" + propertyValue.getPropertyValue());
        }

        return propertyValues;
    }

    /**
     * 获取京东类目属性
     *
     * @param accessToken  访问token
     * @param cid          类目主键
     * @param platformFlag 平台标识
     * @return 类目属性集
     */
    private List<CategoryProperty> pullJDCategoryPropertyList(String accessToken, long cid, int platformFlag) throws JdException {
        String categoryId = Long.toString(getItemCategoryById(cid).getCategoryId());
        CategoryAttributeSearchResponse attributeSearchResponse = jdService.getCategoryAttribute(accessToken, categoryId);
        List<CategoryAttributeSearchResponse.Attribute> attributes = attributeSearchResponse.getAttributes();

        List<CategoryProperty> properties = new ArrayList<CategoryProperty>();
        for (CategoryAttributeSearchResponse.Attribute attribute : attributes) {
            CategoryProperty categoryProperty = new CategoryProperty();
            categoryProperty.setPropertyId(attribute.getAid());
            categoryProperty.setPropertyName(attribute.getName());
            categoryProperty.setCategoryId(cid);
            categoryProperty.setPlatformFlag(platformFlag);
            categoryProperty.setRequired(attribute.getReq());
            categoryProperty.setCreateTime(new Date());
            categoryProperty.setOperator("");
            properties.add(categoryProperty);
        }

        return properties;
    }

    private ItemCategory getItemCategoryById(long id) {
        return itemCategoryMapper.findItemCategoryById(CommonUtil.fromLong2Int(id));
    }

    private Account getAccountById(long accountId) {
        return accountMapper.findAccountById(CommonUtil.fromLong2Int(accountId));
    }
}
