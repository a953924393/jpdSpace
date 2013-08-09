package com.jingpaidang.cshop.service.category;

import com.jd.open.api.sdk.JdException;
import com.jingpaidang.cshop.dao.category.CategoryPropertyMapper;
import com.jingpaidang.cshop.domain.category.CategoryProperty;
import com.jingpaidang.cshop.domain.category.CategoryPropertyValue;
import com.jingpaidang.cshop.rpc.base.CategoryBaseService;
import com.taobao.api.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 类目属性及属性值服务
 *
 * @author Thomson Tang
 * @version 1.0-SNAPSHOT
 * @date 6/26/13
 */
@Service("categoryPropertyService")
@Transactional(value = "transactionManager", rollbackFor = {Throwable.class})
public class CategoryPropertyService {
    private static final Logger logger = LoggerFactory.getLogger(CategoryPropertyService.class);

    @Resource
    private CategoryBaseService categoryBaseService;
    @Resource
    private CategoryPropertyMapper categoryPropertyMapper;

    /**
     * 下载类目属性和属性值
     *
     * @param aid
     * @param cid
     * @throws Exception
     */
    public void downloadCategoryPropertiesAndValues(long aid, long cid) throws JdException, ApiException {
        List<CategoryProperty> categoryProperties = categoryBaseService.pullCategoryPropertyList(aid, cid);

        for (CategoryProperty categoryProperty : categoryProperties) {
            if (isCategoryPropertyNotExisted(categoryProperty)) {
                categoryPropertyMapper.insertCategoryProperty(categoryProperty);
                downloadCategoryPropertyValues(aid, cid, categoryProperty);
            }
        }
    }

    private boolean isCategoryPropertyNotExisted(CategoryProperty categoryProperty) {
        return categoryPropertyMapper.countCategoryProperty(categoryProperty) == 0;
    }

    private void downloadCategoryPropertyValues(long aid, long cid, CategoryProperty categoryProperty) throws JdException, ApiException {
        List<CategoryPropertyValue> categoryPropertyValues =
                categoryBaseService.pullCategoryPropertyValueList(aid, cid, categoryProperty.getPropertyId(), categoryProperty.getId());
        if (null != categoryPropertyValues && categoryPropertyValues.size() != 0) {
            categoryPropertyMapper.insertCategoryPropertyValue(categoryPropertyValues);
            logger.info("======= inserted: " + categoryProperty.getPropertyId() + "--" + categoryProperty.getPropertyName());
        }
    }

    /**
     * 根据类目主键ID查询类目属性列表，并将属性值集进行关联
     *
     * @param categoryId FK
     * @return
     */
    public List<CategoryProperty> findCategoryPropertyListByCategoryId(long categoryId) {
        List<CategoryProperty> categoryProperties = categoryPropertyMapper.findCategoryPropertyByCategoryId(categoryId);

        for (CategoryProperty categoryProperty : categoryProperties) {
            List<CategoryPropertyValue> propertyValues = categoryPropertyMapper.findCategoryPropertyValueByPropId(categoryProperty.getId());
            categoryProperty.setCategoryPropertyValues(propertyValues);
        }
        return categoryProperties;
    }

    /**
     * 根据属性主键ID查询属性值列表
     *
     * @param propertyId FK
     * @return
     */
    public List<CategoryPropertyValue> findCategoryPropValListByPropId(long propertyId) {
        return categoryPropertyMapper.findCategoryPropertyValueByPropId(propertyId);
    }
}
