package com.jingpaidang.cshop.dao.category;

import com.jingpaidang.cshop.domain.category.CategoryProperty;
import com.jingpaidang.cshop.domain.category.CategoryPropertyValue;

import java.util.List;

/**
 * Mapper of category property.
 *
 * @author Thomson Tang
 * @version 1.0-SNAPSHOT
 * @date 6/26/13
 */
public interface CategoryPropertyMapper {

    /**
     * Insert category property.
     *
     * @param categoryProperty
     */
    public void insertCategoryProperty(CategoryProperty categoryProperty);

    /**
     * Batch insert category properties.
     *
     * @param categoryProperties
     */
    public void insertCategoryProperties(List<CategoryProperty> categoryProperties);

    /**
     * Batch insert category property values.
     *
     * @param categoryPropertyValues
     */
    public void insertCategoryPropertyValue(List<CategoryPropertyValue> categoryPropertyValues);

    /**
     * 根据类目主键ID查询类目属性
     *
     * @param categoryId
     * @return
     */
    public List<CategoryProperty> findCategoryPropertyByCategoryId(long categoryId);

    /**
     * Query category property values by property id.
     *
     * @param propertyId
     * @return
     */
    public List<CategoryPropertyValue> findCategoryPropertyValueByPropId(long propertyId);

    /**
     * Query category property by id.
     *
     * @param id
     * @return
     */
    public CategoryProperty findCategoryPropertyById(long id);

    /**
     * Query category property value by id.
     *
     * @param id
     * @return
     */
    public CategoryPropertyValue findCategoryPropertyValueById(long id);

    public int countCategoryProperty(CategoryProperty categoryProperty);
}
