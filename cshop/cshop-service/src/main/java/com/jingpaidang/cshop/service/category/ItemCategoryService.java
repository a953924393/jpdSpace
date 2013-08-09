package com.jingpaidang.cshop.service.category;

import com.jingpaidang.cshop.dao.category.ItemCategoryMapper;
import com.jingpaidang.cshop.dao.user.AccountMapper;
import com.jingpaidang.cshop.domain.category.AccountCategory;
import com.jingpaidang.cshop.domain.category.ItemCategory;
import com.jingpaidang.cshop.domain.user.Account;
import com.jingpaidang.cshop.service.PlatformRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * 商品类目service
 *
 * @author Thomson Tang
 * @version 1.0-SNAPSHOT
 * @date 6/20/13
 */
@Service("itemCategoryService")
@Transactional(value = "transactionManager", rollbackFor = {Throwable.class})
public class ItemCategoryService {
    private static final Logger logger = LoggerFactory.getLogger(ItemCategoryService.class);
    private static final int IS_NOT_EXISTED = 0;

    @Resource
    private ItemCategoryMapper itemCategoryMapper;
    @Resource
    private AccountMapper accountMapper;
    @Resource
    private PlatformRpcService platformRpcService;

    /**
     * 根据账户ID查询该账户下的商品类目
     *
     * @param accountId 店铺账户ID
     * @return ItemCategoryList
     */
    public List<ItemCategory> findItemCategoriesByAccountId(int accountId) throws Exception {
        if (isAccountCategoriesNotDownloaded(accountId)) {
            createItemCategoryList(accountId); //首次进入系统时，需要先下载商品类目
        }

        return itemCategoryMapper.findItemCategoriesByAccountId(accountId);
    }

    /**
     * 根据账户ID查询该账户下的商品类目
     *
     * @param accountId 店铺账户ID
     * @return ItemCategoryList
     */
    public List<ItemCategory> downloadItemCategories(int accountId) throws Exception {
        createItemCategoryList(accountId);
        return itemCategoryMapper.findItemCategoriesByAccountId(accountId);
    }

    /**
     * 调用平台服务获取店铺的商品类目，并插入到本地库中
     *
     * @param accountId 店铺账户ID
     */
    public void createItemCategoryList(int accountId) throws Exception {
        Account account = accountMapper.findAccountById(accountId);
        List<ItemCategory> itemCategories = platformRpcService.downLoadItemCategory(account.getPlatformAccessToken(), account.getPlatformFlag());
        insertItemCategories(accountId, itemCategories);
    }

    /**
     * 判断该账户下的商品类目是否还未下载
     *
     * @param accountId 店铺账户ID
     * @return boolean
     */
    private boolean isAccountCategoriesNotDownloaded(long accountId) {
        int accountCategoryAmount = itemCategoryMapper.countAccountCategoryByAccountId(accountId);
        return accountCategoryAmount == IS_NOT_EXISTED;
    }

    /**
     * 新增商品类目
     *
     * @param itemCategories 商品类目集
     */
    public void insertItemCategories(long accountId, List<ItemCategory> itemCategories) throws Exception {
        logger.info("=========>> 开始新增商品类目...........");
        List<AccountCategory> notExistedAccountCategories = new ArrayList<AccountCategory>(); //用来存放账户-类目对应关系

        try {
            Iterator<ItemCategory> iterator = itemCategories.iterator();
            while (iterator.hasNext()) {
                ItemCategory itemCategory = iterator.next();
                if (isItemCategoryNotExisted(itemCategory)) {
                    itemCategoryMapper.insertItemCategory(itemCategory); //新增库中不存在的商品类目
                    notExistedAccountCategories.add(new AccountCategory(accountId, itemCategory.getId()));
                } else if (isAccountCategoryNotExisted(new AccountCategory(accountId, itemCategory.getCategoryId()))) {
                    ItemCategory itemCategory1 = itemCategoryMapper.findItemCategoryByCidPFlag(itemCategory);
                    notExistedAccountCategories.add(new AccountCategory(accountId, itemCategory1.getId()));
                }
            }

            //新增账户和类目的对应关系
            if (notExistedAccountCategories.size() > 0) {
                itemCategoryMapper.insertAccountCategories(notExistedAccountCategories);
                logger.info("==========<< 新增账户类目映射关系完成，数量：" + notExistedAccountCategories.size() + "个。");
            }
        } catch (Exception ex) {
            throw new Exception("新增商品类目失败！", ex);
        }
    }

    /**
     * 查询该商品类目是否不存在
     *
     * @param itemCategory (categoryId and platformFlag are required)
     * @return boolean
     */
    private boolean isItemCategoryNotExisted(ItemCategory itemCategory) {
        int amount = itemCategoryMapper.countItemCategoryByCategoryId(itemCategory);
        return (amount == IS_NOT_EXISTED);
    }

    /**
     * 查询账户类目映射关系是否存在
     *
     * @param accountCategory
     * @return
     */
    private boolean isAccountCategoryNotExisted(AccountCategory accountCategory) {
        int amount = itemCategoryMapper.countAccountCategory(accountCategory);
        return (amount == IS_NOT_EXISTED);
    }
}
