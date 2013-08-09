package com.jingpaidang.cshop.action.move;

import com.jd.open.api.sdk.JdException;
import com.jd.open.api.sdk.domain.sellercat.ShopCategory;
import com.jd.open.api.sdk.request.ware.WareAddRequest;
import com.jd.open.api.sdk.response.ware.WareAddResponse;
import com.jingpaidang.cshop.action.admin.BaseAction;
import com.jingpaidang.cshop.common.utils.JSONUtils;
import com.jingpaidang.cshop.domain.category.ItemCategory;
import com.jingpaidang.cshop.domain.user.Account;
import com.jingpaidang.cshop.domain.user.User;
import com.jingpaidang.cshop.domain.ware.WareItem;
import com.jingpaidang.cshop.service.PlatformRpcService;
import com.jingpaidang.cshop.service.UserService;
import com.jingpaidang.cshop.service.category.ItemCategoryService;
import com.jingpaidang.cshop.service.move.MoveService;
import com.jingpaidang.cshop.service.wares.WaresService;
import com.taobao.api.ApiException;
import com.taobao.api.domain.Sku;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 一键搬家action
 *
 * @author jackchen
 */
@ParentPackage("move")
public class MoveAction extends BaseAction {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(MoveAction.class);

    /**
     * 搬家商品列表
     */
    public static final String SRC_ITEM_LIST = "move_src_item_list";
    public static final String TARGET_SHOP_LIST = "move_target_shop_list";
    public static final String SRC_ITEM_TABLE = "common/move_src_item_table";

    @Resource
    private UserService userService;
    @Resource
    private PlatformRpcService rpcService;
    @Resource
    private ItemCategoryService itemCategoryService;
    @Resource
    private MoveService moveService;
    @Resource
    private WaresService waresService;

    //令牌
    private String accessToken;
    //平台标志位
    private Integer flag;
    //账户Id，平台id
    private int accountId;
    private String sale;
    //商品是否已经搬家   1  0
    private Integer moved;
    //商品标题关键字
    private String queryKey;
    //分类id
    private Long categoryId;
    //商品集合
    private List<WareItem> wareItems;
    //
    private String wareIds;
    //商品的平台id串   333223,4243344,443344
    private String itemIds;
    //规则id
    private Long ruleId;

    //是否为源网店或目标网店
    private Integer src;
    //商店的自定义分类id，主要用于京东
    private String shopCategoryId;
    private Integer cid;


    public String moveItems() {
        try {
            Map<WareAddRequest, WareAddResponse> responseMap = moveService.moveTb2Jd(itemIds, ruleId, shopCategoryId);
        } catch (IOException e) {
            return ajax("-1");
        } catch (ApiException e) {
            return  ajax("-1");

        } catch (JdException e) {
            return ajax("-3");
        }

        return "move_movesuccess";
    }


    /**
     * 接受商品ids，格式为   23344,44343,544545
     * Map<Long, List<Sku>>   key 为商品的id。value为sku集合
     *
     * @return
     */


    public String skus() {
        Map<Long, List<Sku>> skusByWareId = null;
        try {
            skusByWareId = rpcService.findSkusByWareId(accountId, wareIds);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ApiException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return this.ajax(skusByWareId);
    }

    /**
     * 跳转到搬家的首页
     *
     * @return ITEM_LIST
     */
    public String srcShopItemList() {
        return SRC_ITEM_LIST;
    }

    public String findItemList() {
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("accountId", accountId);
        map.put("sale", sale);
        map.put("moved", moved);
        map.put("queryKey", queryKey);
        map.put("categoryId", categoryId);
        map.put("cid", cid);
        if (itemIds != null && !itemIds.equals("")) {
            map.put("itemIds", itemIds.split(","));
        }
        wareItems = waresService.getWareItemList(map);
        return SRC_ITEM_TABLE;
    }

    /**
     * 用户源店铺账户查询
     *
     * @return
     */
    public String accounts() {
        Integer uid = ((User) this.getSession("user")).getId();
        List<Account> accounts = userService.findAllAccounts(uid, src);


        logger.info("get accounts(size: " + accounts.size() + ") over!");
        return this.ajax(accounts);
    }

    /**
     * 店铺分类类目
     *
     * @return
     */
    public String shopCategories() {

        List<ShopCategory> shopCategories = null;
        try {
            shopCategories = rpcService.findShopCategories(accountId);
        } catch (JdException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        String json = JSONUtils.toJson(shopCategories);

        return this.ajax(json);
    }

    /**
     * 店铺商品类目查询
     *
     * @return
     */
    public String categories() {
        String json = "";
        try {
            List<ItemCategory> itemCategoryList = itemCategoryService.findItemCategoriesByAccountId(accountId);
            json = JSONUtils.toJson(itemCategoryList);
        } catch (Exception e) {
            logger.error("********** call findItemCategoriesByAccountId error.", e);
        }
        return this.ajax(json);
    }

    /**
     * 目标网店列表展示
     *
     * @return
     */
    public String targetShopList() {
        logger.info("accountId: " + accountId);
        logger.info("categoryId: " + categoryId);
        logger.info("items: " + itemIds);
        return TARGET_SHOP_LIST;
    }

    /**
     * 店铺类目查询，直接从平台查询
     *
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


    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getSale() {
        return sale;
    }

    public void setSale(String sale) {
        this.sale = sale;
    }

    public Integer getMoved() {
        return moved;
    }

    public void setMoved(Integer moved) {
        this.moved = moved;
    }

    public String getQueryKey() {
        return queryKey;
    }

    public void setQueryKey(String queryKey) {
        this.queryKey = queryKey;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public List<WareItem> getWareItems() {
        return wareItems;
    }

    public void setWareItems(List<WareItem> wareItems) {
        this.wareItems = wareItems;
    }

    public String getWareIds() {
        return wareIds;
    }

    public void setWareIds(String wareIds) {
        this.wareIds = wareIds;
    }

    public String getItemIds() {
        return itemIds;
    }

    public void setItemIds(String itemIds) {
        this.itemIds = itemIds;
    }

    public Long getRuleId() {
        return ruleId;
    }

    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }

    public String getShopId() {
        return shopCategoryId;
    }

    public void setShopId(String shopId) {
        this.shopCategoryId = shopId;
    }


    public Integer getCid() {
        return cid;
    }


    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getSrc() {
        return src;
    }

    public void setSrc(Integer src) {
        this.src = src;
    }

}
