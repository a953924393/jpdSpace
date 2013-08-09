package com.jingpaidang.cshop.action.Category;

import com.jingpaidang.cshop.action.admin.BaseAction;
import com.jingpaidang.cshop.domain.category.ItemCategory;
import com.jingpaidang.cshop.service.category.ItemCategoryService;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: jackchen
 * Date: 7/5/13
 * Time: 12:44 PM
 */
@ParentPackage("category")

public class CategoryAction extends BaseAction{
    @Resource
    private ItemCategoryService itemCategoryService;
    private static final Logger logger = LoggerFactory.getLogger(CategoryAction.class);

    private int accountId;
    /**
     * 下载商品类目
     * @return
     */
    public String downloadCategories() {
        try {

            List<ItemCategory> categories = itemCategoryService.downloadItemCategories(accountId);
            return this.ajax(categories);
        } catch (Exception e) {
            logger.error("******** call downloadItemCategories error.", e);
            return this.ajax(Status.error, "下载类目失败！");
        }
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
}
