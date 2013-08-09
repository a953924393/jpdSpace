package com.jingpaidang.cshop.dao.category;

import com.jingpaidang.cshop.domain.category.AccountCategory;
import com.jingpaidang.cshop.domain.category.ItemCategory;

import java.util.List;
import java.util.Map;

/**
 * 商品类目Mapper
 *
 * @author Thomson Tang
 * @version 1.0-SNAPSHOT
 * @date 6/19/13
 */
public interface ItemCategoryMapper {

    /**
     * 根据主键查询商品类目
     *
     * @param id primary key
     * @return ItemCategory entity
     */
    public ItemCategory findItemCategoryById(int id);

    /**
     * 根据类目ID和平台标志查询商品类目
     *
     * @param itemCategory required(categoryId platformFlag)
     * @return
     */
    public ItemCategory findItemCategoryByCidPFlag(ItemCategory itemCategory);

    /**
     * 根据账户ID查询店铺账户所对应的商品类目
     *
     * @return
     */
    public List<ItemCategory> findItemCategoriesByAccountId(long accountId);

    /**
     * 根据类目信息查询商品类目
     *
     * @param itemCategory
     * @return categories
     */
    public List<ItemCategory> findItemCategories(ItemCategory itemCategory);

    /**
     * 新增商品类目
     *
     * @param itemCategory instance of ItemCategory
     */
    public void insertItemCategory(ItemCategory itemCategory);

    /**
     * 批量新增商品类目
     *
     * @param itemCategoryList (list of ItemCategory instances)
     */
    public void insertItemCategories(List<ItemCategory> itemCategoryList);

    /**
     * 根据类目ID统计类目的数量
     *
     * @param itemCategory {categoryId, platformFlag}
     * @return int (amount of category)
     */
    public int countItemCategoryByCategoryId(ItemCategory itemCategory);

    /**
     * 新增账户类目关系, 对应该账户及其包含的商品类目
     *
     * @param accountCategories
     */
    public void insertAccountCategories(List<AccountCategory> accountCategories);

    /**
     * 统计账户类目间映射关联的数量
     *
     * @param accountCategory
     * @return
     */
    public int countAccountCategory(AccountCategory accountCategory);


    /**
     * 根据账户ID统计该店铺下的商品类目数量
     *
     * @param accountId 账户ID
     * @return 商品类目数
     */
    public int countAccountCategoryByAccountId(long accountId);

    /**
     * @throws
     * @Title:getItemCategoryByAccountIdAndCategoryId
     * @Description:TODO(根据账户Id和类目Id获取类目)
     * @param:@param paras    查询条件
     * @param:@return
     * @return:ItemCategory
     * @Create: 2013-7-3 下午5:21:58
     * @Author : Alex
     */
    public ItemCategory getItemCategoryByAccountIdAndCategoryId(Map<String, Object> paras);
}
