package com.jingpaidang.cshop.service;

import com.jd.open.api.sdk.JdException;
import com.jd.open.api.sdk.domain.category.Category;
import com.jd.open.api.sdk.domain.sellercat.ShopCategory;
import com.jd.open.api.sdk.domain.ware.Ware;
import com.jd.open.api.sdk.response.category.CategorySearchResponse;
import com.jd.open.api.sdk.response.sellercat.SellerCatsGetResponse;
import com.jd.open.api.sdk.response.ware.WareListResponse;
import com.jingpaidang.cshop.common.constant.BasicConstant;
import com.jingpaidang.cshop.dao.category.ItemCategoryMapper;
import com.jingpaidang.cshop.dao.user.AccountMapper;
import com.jingpaidang.cshop.domain.category.ItemCategory;
import com.jingpaidang.cshop.domain.user.Account;
import com.jingpaidang.cshop.domain.ware.WareItem;
import com.jingpaidang.cshop.rpc.jd.JdService;
import com.jingpaidang.cshop.rpc.tb.TbService;
import com.taobao.api.ApiException;
import com.taobao.api.domain.Item;
import com.taobao.api.domain.ItemCat;
import com.taobao.api.domain.Sku;
import com.taobao.api.response.*;
import com.taobao.api.response.ItemcatsGetResponse;
import com.taobao.api.response.ItemsListGetResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * @author jackchen
 */
@Service("commonServcie")
public class PlatformRpcService {
    private static final Logger logger = LoggerFactory.getLogger(PlatformRpcService.class);

    @Resource
    private TbService tbService;

    @Resource
    private JdService jdService;

    @Resource
    private AccountMapper accountMapper;

    @Resource
    private ItemCategoryMapper itemCategoryMapper;


    /**
     * 根据传入的商品ids获得skus， 可以传入单个或者多个商品
     *
     * @param accountId
     * @param wareIds
     * @return
     */
    public Map<Long, List<Sku>> findSkusByWareId(int accountId, String wareIds) throws IOException, ApiException {
        Map<Long, List<Sku>> listMap = new HashMap<Long, List<Sku>>();

        Account account = accountMapper.findAccountById(accountId);

        ItemsListGetResponse itemsListGetResponse = tbService.getItemsByNumiid(account.getPlatformAccessToken(), wareIds);

        List<Item> items = itemsListGetResponse.getItems();

        for (Item item : items) {

            List<Sku> skus = tbService.getSkus(account.getPlatformAccessToken(), item);

            listMap.put(item.getNumIid(), skus);
        }

        return listMap;
    }

    public List<ShopCategory> findShopCategories(int accountId) throws JdException {
        Account account = accountMapper.findAccountById(accountId);
        if (account.getPlatformFlag() == BasicConstant.PLATFORM_JING_DONG) {

            SellerCatsGetResponse shopCategory = jdService.getShopCategory(account.getPlatformAccessToken());

            return selectShopCategories(shopCategory.getShopCatList());

        } else if (account.getPlatformFlag() == BasicConstant.PLATFORM_TAO_BAO) {
            return null;
        }
        return null;
    }

    /**
     * 从自定义类目中选择能够发布商品的类目进行显示
     *
     * @param shopCategories
     * @return
     */
    private List<ShopCategory> selectShopCategories(List<ShopCategory> shopCategories) {

        List<ShopCategory> shopCategoryList = new ArrayList<ShopCategory>(shopCategories);
        for (ShopCategory shopCategory : shopCategories) {
            if (!shopCategory.getParent()) {
                long parentId = shopCategory.getParentId();

                Iterator<ShopCategory> categoryIterator = shopCategoryList.iterator();
                while (categoryIterator.hasNext()) {
                    ShopCategory category = categoryIterator.next();

                    if (category.getCid() == parentId) {
                        categoryIterator.remove();
                        break;
                    }

                }
            }
        }

        System.err.println("-----------------");
        return shopCategoryList;
    }

    /**
     * 传入查询条件map
     * map的格式为
     * key= accountId
     * key= sale
     * key= queryKey
     * key= categoryId
     * key= moved
     * key=
     *
     * @return 从各平台查询到的商品数据集合
     */
    public List<WareItem> findWareItems(Map<String, Object> conditionMap) throws IOException, ApiException, JdException {
        Integer accountId = null;
        Integer sale = null;
        String queryKey = null;
        Long categoryId = null;
        Long itemId = null;
//        int moved = (Integer)conditionMap.get("moved");


        if (conditionMap.get("accountId") != null) {
            accountId = (Integer) conditionMap.get("accountId");
        }
        if (conditionMap.get("sale") != null) {
            sale = (Integer) conditionMap.get("sale");
        }
        if (conditionMap.get("queryKey") != null && conditionMap.get("queryKey").toString() != "") {
            queryKey = conditionMap.get("queryKey").toString();
        }
        if (conditionMap.get("categoryId") != null) {
            categoryId = Long.parseLong(conditionMap.get("categoryId").toString());
        }
        if (conditionMap.get("itemId") != null) {
            itemId = Long.parseLong(conditionMap.get("itemId").toString());
        }

        Account account = accountMapper.findAccountById(accountId);
        String accessToken = account.getPlatformAccessToken();
        Integer flag = account.getPlatformFlag();

        if (flag == BasicConstant.PLATFORM_TAO_BAO || flag == BasicConstant.PLATFORM_TIAN_MAO) {
            //如果有商品编号，按照商品编号查询
            if (itemId != null) {
                ItemsListGetResponse itemsListGetResponse = tbService.getItemsByNumiid(accessToken, itemId.toString());
                List<WareItem> wareItems = tbItemList2JshopWareList(itemsListGetResponse.getItems());
                return wareItems;

                //如果有关键字，按照关键子查询
            } else {
                List<Item> items = tbService.getItems(categoryId, accessToken, queryKey, sale);

                return tbItemList2JshopWareList(items);
            }
        } else if (flag == BasicConstant.PLATFORM_JING_DONG) {
            if (itemId != null) {
                WareListResponse wareList = jdService.getWareList(accessToken, itemId.toString());

                List<WareItem> wareItems = jdItemList2JshopWareList(wareList.getWareList());
                return wareItems;

                //如果有关键字，按照关键子查询
            } else {
                List<Item> items = tbService.getItems(categoryId, accessToken, queryKey, sale);

                return tbItemList2JshopWareList(items);
            }
        }

        return null;
    }

    /**
     * @throws
     * @Title:findAllWareItems
     * @Description:TODO(根据店铺ID查询所有商品)
     * @param:@param accountId
     * @param:@return
     * @return:List<WareItem>
     * @Create: 2013-7-3 下午4:46:54
     * @Author : Alex
     */
    public List<WareItem> findAllWareItems(int accountId) throws IOException, ApiException {
        Account account = accountMapper.findAccountById(accountId);
        String accessToken = account.getPlatformAccessToken();
        Integer flag = account.getPlatformFlag();

        if (flag == BasicConstant.PLATFORM_TAO_BAO || flag == BasicConstant.PLATFORM_TIAN_MAO) {
            List<Item> items = tbService.getAllItems(accessToken);
            return tbItemList2JshopWareList(items, account);
        }
        return null;
    }

    /**
     * 得到授权信息
     *
     * @param flag
     * @param code
     * @param state
     * @return
     */
    public Map<String, Object> getOauthMsg(Integer flag, String code, String state) throws NoSuchAlgorithmException, KeyManagementException, IOException {
        Map<String, Object> oauthMsg = null;
        if (flag == 1 || flag == 2) {
            oauthMsg = tbService.getAccessToken(code, state);

        } else if (flag == 3) {
            oauthMsg = jdService.getAccessToken(code, state);
        }

        oauthMsg.put("flag", flag.toString());
        return oauthMsg;
    }

    /**
     * 查询用户的发布过商品的类目,map<cid,name> cid 为
     *
     * @param sessionKey
     * @param flag       平台标志
     * @return 1:淘宝
     *         2:天猫
     *         3:京东
     */
    public List<ItemCategory> downLoadItemCategory(String sessionKey, Integer flag) throws IOException, ApiException, JdException {
        List<ItemCategory> itemCategoryList = new ArrayList<ItemCategory>();
        if (flag == BasicConstant.PLATFORM_TAO_BAO) {
            Map<Long, ItemCategory> itemMap = new HashMap<Long, ItemCategory>();
            List<Item> items = new ArrayList<Item>();

            items = tbService.getItemsOnsale(sessionKey, null).getItems();

            // 将所有的商品都查询出来放在一个集合中


            List<Item> items1 = tbService.getItemsInventory(sessionKey, null).getItems();

            if (items1 != null && items1.size() > 0) {

                items.addAll(items1);
            }

            for (Item item : items) {
                if (itemMap.get(item.getCid()) == null) {
                    ItemcatsGetResponse cates = tbService.getCates(item.getCid()
                            .toString(), null);

                    //map.put(item.getCid().toString(), cates.getItemCats().get(0).getName());
                    itemMap.put(item.getCid(), getItemCategoryTb(cates));
                    logger.info("=========类目全名：" + getCateFullNameOfTb(item.getCid()));
                }
            }

            itemCategoryList = new ArrayList<ItemCategory>(itemMap.values());

        } else if (flag == BasicConstant.PLATFORM_TIAN_MAO) {
            ItemcatsAuthorizeGetResponse categorysOfUser = tbService.getCategorysOfUser(sessionKey);
            List<ItemCat> itemCats = categorysOfUser.getSellerAuthorize().getItemCats();
            Map<Long, ItemCategory> categoryMap = new HashMap<Long, ItemCategory>();

            Map<Long, ItemCat> itemCatMap = new HashMap<Long, ItemCat>();
            for (ItemCat itemCat : itemCats) {
                getItemCatTm(itemCat, itemCatMap);
                getItemCategoryMapTm(itemCatMap, categoryMap);
//                this.getCateFullNameOfTm(itemCat, strings);
            }

            itemCategoryList = new ArrayList<ItemCategory>(categoryMap.values());

        } else if (flag == BasicConstant.PLATFORM_JING_DONG) {
            CategorySearchResponse categorysOfUser = jdService.getCategorysOfUser(sessionKey);
            List<Category> category = categorysOfUser.getCategory();

            Map<Integer, Category> categoryMap = new HashMap<Integer, Category>();
            for (Category category1 : category) {
                categoryMap.put(category1.getId(), category1);
            }

            itemCategoryList = new ArrayList<ItemCategory>();
            for (Category cate : category) {
                if (!cate.isParent()) {
                    itemCategoryList.add(getItemCategoryJd(cate, categoryMap));
                }
            }

        }

        return itemCategoryList;
    }

    /**
     * @throws
     * @Title:getItemCatTm
     * @Description:TODO(获取所有类目)
     * @param:@param itemCat
     * @param:@param itemCatMap
     * @return:void
     * @Create: 2013-7-8 下午4:04:11
     * @Author : Alex
     */
    private void getItemCatTm(ItemCat itemCat, Map<Long, ItemCat> itemCatMap) throws ApiException {
        if (itemCat.getIsParent()) {
            itemCatMap.put(itemCat.getCid(), itemCat);
            ItemcatsGetResponse cates = tbService.getCates(null, itemCat.getCid());

            List<ItemCat> itemCats = cates.getItemCats();

            for (ItemCat cat : itemCats) {
                getItemCatTm(cat, itemCatMap);
            }
        } else {
            itemCatMap.put(itemCat.getCid(), itemCat);
        }
    }

    /**
     * @throws
     * @Title:getItemCategoryMapTm
     * @Description:TODO(获取类目)
     * @param:@param itemCatMap
     * @param:@param categoryMap
     * @return:void
     * @Create: 2013-7-8 下午4:05:08
     * @Author : Alex
     */
    private void getItemCategoryMapTm(Map<Long, ItemCat> itemCatMap, Map<Long, ItemCategory> categoryMap) {
        Set<Map.Entry<Long, ItemCat>> set = itemCatMap.entrySet();
        for (Iterator<Map.Entry<Long, ItemCat>> it = set.iterator(); it.hasNext(); ) {
            Map.Entry<Long, ItemCat> entry = (Map.Entry<Long, ItemCat>) it.next();
            Long cid = entry.getKey();
            ItemCat itemCat = entry.getValue();
            if (!itemCat.getIsParent()) {
                StringBuffer fullName = new StringBuffer(itemCat.getName());
                String name = getCateFullNameOfTm(itemCat, fullName, itemCatMap);
                categoryMap.put(cid, this.getItemCategoryTm(itemCat, name));
            }
        }
    }

    /**
     * @throws
     * @Title:getCateFullNameOfTm
     * @Description:TODO(获取类目名称，组成的串)
     * @param:@param itemCat
     * @param:@param fullName
     * @param:@param itemCatMap
     * @param:@return
     * @return:String
     * @Create: 2013-7-8 下午4:07:03
     * @Author : Alex
     */
    private String getCateFullNameOfTm(ItemCat itemCat, StringBuffer fullName, Map<Long, ItemCat> itemCatMap) {
        if (itemCat.getParentCid().longValue() == 0) {
            return fullName.toString();
        } else {
            ItemCat pItemCat = itemCatMap.get(itemCat.getParentCid());
            fullName.insert(0, ">>");
            fullName.insert(0, pItemCat.getName());
            getCateFullNameOfTm(pItemCat, fullName, itemCatMap);
        }
        return fullName.toString();
    }

    /**
     * @throws
     * @Title:getItemCategoryTm
     * @Description:TODO(设置类目bean)
     * @param:@param itemCat
     * @param:@param name
     * @param:@return
     * @return:ItemCategory
     * @Create: 2013-7-8 下午4:07:39
     * @Author : Alex
     */
    private ItemCategory getItemCategoryTm(ItemCat itemCat, String name) {
        ItemCategory itemCategory = new ItemCategory();
        itemCategory.setCategoryId(itemCat.getCid());
        itemCategory.setCategoryName(name);
        itemCategory.setCreateTime(new Date());
        itemCategory.setParent(itemCat.getIsParent());
        itemCategory.setParentId(itemCat.getParentCid());
        itemCategory.setPlatformFlag(BasicConstant.PLATFORM_TIAN_MAO);
        return itemCategory;
    }

    private ItemCategory getItemCategoryJd(Category cate, Map<Integer, Category> categoryMap) {
        ItemCategory itemCategory = new ItemCategory();
        itemCategory.setCategoryId(cate.getId());
        itemCategory.setCategoryName(getCateFullNameOfJd(cate, categoryMap));
        itemCategory.setCreateTime(new Date());
        itemCategory.setParent(cate.isParent());
        itemCategory.setParentId(cate.getFid());
        itemCategory.setPlatformFlag(BasicConstant.PLATFORM_JING_DONG);
        return itemCategory;
    }

    public String getCateFullNameOfJd(Category cate, Map<Integer, Category> categoryMap) {
        while (cate.getFid() != 0) {
            StringBuffer fullName = new StringBuffer();
            Category category = categoryMap.get(cate.getFid());
            String parentName = getCateFullNameOfJd(category, categoryMap);

            fullName.append(parentName).append(">>").append(cate.getName());
            return fullName.toString();
        }
        return cate.getName();
    }


    private List<WareItem> jdItemList2JshopWareList(List<Ware> items) {
        List<WareItem> wareItems = new ArrayList<WareItem>();
        WareItem wareItem = new WareItem();
        for (Ware item : items) {
            wareItem.setItemId(item.getWareId());
            wareItem.setItemName(item.getTitle());
            wareItem.setStorage(item.getStockNum());
            wareItem.setImg(item.getLogo());
            wareItem.setUrl(null);
            wareItem.setPrice(item.getJdPrice());
            String categoryName = itemCategoryMapper.findItemCategoryByCidPFlag(new ItemCategory(item.getCategoryId(), BasicConstant.PLATFORM_JING_DONG)).getCategoryName();

            wareItem.setCategoryName(categoryName);
            wareItem.setMoved(false);
            wareItems.add(wareItem);
        }
        return wareItems;
    }

    /**
     * 淘宝的ItemList转变为系统ItemList
     *
     * @param items
     * @return
     */
    private List<WareItem> tbItemList2JshopWareList(List<Item> items) {
        List<WareItem> wareItems = new ArrayList<WareItem>();
        WareItem wareItem;
        for (Item item : items) {
            wareItem = new WareItem();

            wareItem.setItemId(item.getNumIid());
            wareItem.setItemName(item.getTitle());
            wareItem.setStorage(item.getNum());
            wareItem.setImg(item.getPicUrl());
            wareItem.setUrl(item.getDetailUrl());
            wareItem.setPrice(item.getPrice());
            String categoryName = itemCategoryMapper.findItemCategoryByCidPFlag(new ItemCategory(item.getCid(), BasicConstant.PLATFORM_TAO_BAO)).getCategoryName();

            wareItem.setCategoryName(categoryName);
            wareItem.setMoved(false);
            wareItems.add(wareItem);
        }
        return wareItems;
    }

    /**
     * 淘宝的ItemList转变为系统ItemList
     *
     * @param items
     * @return
     */
    private List<WareItem> tbItemList2JshopWareList(List<Item> items, Account account) {
        List<WareItem> wareItems = new ArrayList<WareItem>();
        WareItem wareItem = null;
        for (Item item : items) {
            wareItem = new WareItem();

            wareItem.setItemId(item.getNumIid());
            wareItem.setItemName(item.getTitle());
            wareItem.setStorage(item.getNum());
            wareItem.setImg(item.getPicUrl());
            wareItem.setUrl(item.getDetailUrl());
            wareItem.setPrice(item.getPrice());
            Map<String, Object> paras = new HashMap<String, Object>();
            paras.put("platformFlag", account.getPlatformFlag());
            paras.put("categoryId", item.getCid());
            long categoryId = itemCategoryMapper.getItemCategoryByAccountIdAndCategoryId(paras).getId();        //本系统类目ID
            wareItem.setCategoryId(categoryId);
            wareItem.setCreated(new Date());
            wareItem.setDescription(item.getDesc());
            wareItem.setJshopAccountId(account.getId());
            wareItem.setJshopUserId(account.getJshopUserId());
            wareItem.setModified(new Date());
            wareItem.setOuterId(item.getOuterId());
            wareItem.setStatus(item.getApproveStatus());
            String categoryName = itemCategoryMapper.findItemCategoryByCidPFlag(new ItemCategory(item.getCid(), account.getPlatformFlag())).getCategoryName();

            wareItem.setCategoryName(categoryName);
            wareItem.setMoved(false);
            wareItems.add(wareItem);
        }
        return wareItems;
    }

    /**
     * 取得淘宝最低级类目的所有上级类目并拼接
     *
     * @param cid
     * @return
     */
    private String getCateFullNameOfTb(long cid) throws ApiException {
        ItemcatsGetResponse itemCatsResponse = tbService.getCates(cid + "", null);
        ItemCat itemCat = itemCatsResponse.getItemCats().get(0);
        while (itemCat.getParentCid() != 0l) {
            StringBuffer fullName = new StringBuffer();
            String parentName = getCateFullNameOfTb(itemCat.getParentCid());
            fullName.append(parentName).append(">>").append(itemCat.getName());
            return fullName.toString();
        }
        return itemCat.getName();
    }

    /**
     * 取得该平台商家所有的类目
     *
     * @param cates
     * @return
     */
    private ItemCategory getItemCategoryTb(ItemcatsGetResponse cates) throws ApiException {
        ItemCategory itemCategory = new ItemCategory();
        itemCategory.setCategoryId(cates.getItemCats().get(0).getCid());
        itemCategory.setCategoryName(getCateFullNameOfTb(itemCategory.getCategoryId()));
        itemCategory.setCreateTime(new Date());
        itemCategory.setParent(cates.getItemCats().get(0).getIsParent());
        itemCategory.setParentId(cates.getItemCats().get(0).getParentCid());
        itemCategory.setPlatformFlag(BasicConstant.PLATFORM_TAO_BAO);
        return itemCategory;
    }
}
