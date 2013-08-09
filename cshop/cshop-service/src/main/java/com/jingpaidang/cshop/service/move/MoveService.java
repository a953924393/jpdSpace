package com.jingpaidang.cshop.service.move;

import com.jd.open.api.sdk.JdException;
import com.jd.open.api.sdk.request.ware.WareAddRequest;
import com.jd.open.api.sdk.response.ware.WareAddResponse;
import com.jingpaidang.cshop.common.constant.BasicConstant;
import com.jingpaidang.cshop.common.utils.ImageUtil;
import com.jingpaidang.cshop.common.utils.JSONUtils;
import com.jingpaidang.cshop.common.utils.ValidateUtil;
import com.jingpaidang.cshop.dao.category.CategoryPropertyMapper;
import com.jingpaidang.cshop.dao.category.ItemCategoryMapper;
import com.jingpaidang.cshop.dao.convert.ConvertRuleMapper;
import com.jingpaidang.cshop.dao.move.MoveHistoryMapper;
import com.jingpaidang.cshop.dao.user.AccountMapper;
import com.jingpaidang.cshop.dao.user.UserMapper;
import com.jingpaidang.cshop.domain.category.CategoryProperty;
import com.jingpaidang.cshop.domain.category.CategoryPropertyValue;
import com.jingpaidang.cshop.domain.category.ItemCategory;
import com.jingpaidang.cshop.domain.convert.ConvertRuleDetail;
import com.jingpaidang.cshop.domain.convert.PlatformConvertRule;
import com.jingpaidang.cshop.domain.move.MoveHistory;
import com.jingpaidang.cshop.domain.user.Account;
import com.jingpaidang.cshop.domain.ware.WareItem;
import com.jingpaidang.cshop.rpc.jd.JdService;
import com.jingpaidang.cshop.rpc.tb.TbService;
import com.taobao.api.ApiException;
import com.taobao.api.domain.Item;
import com.taobao.api.response.ItemsListGetResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

/**
 * 商品一键搬家服务
 *
 * @author Thomson Tang
 * @version 1.0-SNAPSHOT
 * @date 6/17/13
 */
@Service("moveService")
public class MoveService {
    private static final Logger logger = LoggerFactory.getLogger(MoveService.class);

    @Resource
    private TbService tbService;
    @Resource
    private JdService jdService;
    @Resource
    private AccountMapper accountMapper;
    @Resource
    private ItemCategoryMapper itemCategoryMapper;
    @Resource
    private CategoryPropertyMapper categoryPropertyMapper;
    @Resource
    private MoveHistoryMapper moveHistoryMapper ;


    /**
     * 根据条件查询要搬家的商品列表
     *
     * @param wareItem
     * @return items will moving
     */
    public List<WareItem> findWareItems4Moving(WareItem wareItem) {
        logger.info("=======>> start finding items for moving...............");
        return null;
    }

    @Resource
    private ConvertRuleMapper convertRuleMapper;

    /**
     * 从淘宝搬家到京东
     *
     * @param itemIds
     * @param ruleId
     * @param shopCategoryId
     */
    public Map<WareAddRequest, WareAddResponse> moveTb2Jd(String itemIds, Long ruleId, String shopCategoryId) throws JdException, IOException, ApiException {
        PlatformConvertRule platformConvertRule = convertRuleMapper.findPlatformConvertRuleById(ruleId);

        long srcAccountId = platformConvertRule.getSrcAccountId();

        long targetAccountId = platformConvertRule.getTargetAccountId();

        Account srcAccount = accountMapper.findAccountById((int) srcAccountId);

        Account targetAccount = accountMapper.findAccountById((int) targetAccountId);

        ItemsListGetResponse tbItemsListResponse = tbService.getItemsByNumiid(srcAccount.getPlatformAccessToken(), itemIds);

        List<Item> tbItemsListResponseItems = tbItemsListResponse.getItems();

        Map<WareAddRequest, WareAddResponse> responseMap = new HashMap<WareAddRequest, WareAddResponse>();

        for(Item item : tbItemsListResponseItems) {
            WareAddRequest wareAddRequest = wareTb2Jd(item, platformConvertRule, shopCategoryId);

            WareAddResponse wareAddResponse = jdService.addWare(wareAddRequest, targetAccount.getPlatformAccessToken());
            addMoveHistory(srcAccount,targetAccount,item,wareAddResponse,ruleId,shopCategoryId);
            responseMap.put(wareAddRequest, wareAddResponse);

            
        }

        return  responseMap;
    }

    /**
     * 
     * @Title:addMoveHistory 
     * @Description:TODO(添加搬家记录) 
     * @param:@param srcAccount
     * @param:@param targetAccount
     * @param:@param item
     * @param:@param wareAddResponse
     * @param:@param ruleId
     * @param:@param shopCategoryId 
     * @return:void 
     * @throws 
     * @Create: 2013-7-23 上午11:03:53
     * @Author : Alex
     */
    private void addMoveHistory(Account srcAccount,Account targetAccount,Item item,WareAddResponse wareAddResponse,Long ruleId,String shopCategoryId){
    	MoveHistory moveHistory = new MoveHistory();
    	moveHistory.setItemId(item.getNumIid());
    	moveHistory.setItemName(item.getTitle()) ;
    	moveHistory.setImg(item.getPicUrl());
    	moveHistory.setUrl(item.getDetailUrl());
    	moveHistory.setPrice(item.getPrice());
    	String categoryName = itemCategoryMapper.findItemCategoryByCidPFlag(new ItemCategory(item.getCid(), srcAccount.getPlatformFlag())).getCategoryName();
    	moveHistory.setCategoryName(categoryName);
    	Map<String, Object> paras = new HashMap<String, Object>();
        paras.put("platformFlag", srcAccount.getPlatformFlag());
        paras.put("categoryId", item.getCid());
        long categoryId = itemCategoryMapper.getItemCategoryByAccountIdAndCategoryId(paras).getId();        //本系统类目ID
    	moveHistory.setCategoryId(categoryId);
    	moveHistory.setJshopAccountId(srcAccount.getId());
    	moveHistory.setJshopUserId(srcAccount.getJshopUserId());
    	moveHistory.setDescription(item.getDesc());
    	moveHistory.setCreated(new Date());
    	moveHistory.setModified(new Date());
    	moveHistory.setTargetAccountId(targetAccount.getId());
    	moveHistory.setTargetShopName(targetAccount.getPlatformShopName());
    	if("0".equals(wareAddResponse.getCode())){
    		moveHistory.setStatus(BasicConstant.MOVEHISTORY_STATUS_SUCCESS);
    	}else{
    		moveHistory.setStatus(BasicConstant.MOVEHISTORY_STATUS_FAIL);
    		Map<String, Map> jsonMap = JSONUtils.toObject(wareAddResponse.getMsg(), Map.class);
    		moveHistory.setStatusReason(""+ jsonMap.get("error_response").get("zh_desc"));
    	}
    	moveHistory.setRuleId(ruleId);
    	moveHistory.setShopCategoryId(shopCategoryId);
    	moveHistoryMapper.insert(moveHistory);
    }
    /**
     * 淘宝商品转换为京东商品
     *
     * @param item
     * @param platformConvertRule
     * @return
     */

    public WareAddRequest wareTb2Jd(Item item, PlatformConvertRule platformConvertRule, String shopCateId) {
        WareAddRequest addRequest = new WareAddRequest();

        addRequest.setShopCategory(shopCateId);

        addRequest.setTitle(item.getTitle());  //商品名

        addRequest.setMarketPrice(item.getPrice());      //市场价

        addRequest.setJdPrice(item.getPrice());          //京东价

        addRequest.setItemNum(item.getOuterId());        //商家外部id，或者说货号

        ItemCategory itemCategoryById = itemCategoryMapper.findItemCategoryById((int) platformConvertRule.getTargetCategoryId());

        addRequest.setCid(itemCategoryById.getCategoryId() + "");

        addRequest.setLength("10");

        addRequest.setWide("10");

        addRequest.setHigh("10");

        if(ValidateUtil.isValidated(item.getItemWeight()))   {

            addRequest.setWeight(item.getItemWeight());                                  //重量

        } else {
            addRequest.setWeight("5");
        }

        addRequest.setNotes(item.getDesc());
        //商品描述，详情页
        addRequest.setWareImage(ImageUtil.downloadImage(item.getPicUrl()));        //主图，

        addRequest.setStockNum(item.getNum().toString());      //所有商品sku库存之和

        String props = item.getProps();

        String attrs = tbProps2JdAttributes(props, platformConvertRule);

        addRequest.setAttributes(attrs);


        return addRequest;
    }


    /**
     * 淘宝属性转化为京东属性信息
     *
     * @param props
     * @param platformConvertRule
     * @return
     */
    private String tbProps2JdAttributes(String props, PlatformConvertRule platformConvertRule) {
        Map<Long, Long> propsMap = splitProps(props);

        Set<Map.Entry<Long, Long>> propsSet = propsMap.entrySet();

        List<ConvertRuleDetail> ruleDetails = convertRuleMapper.findConvertRuleDetailByRuleId(platformConvertRule.getId());

        Map<Long, Long> attrsMap = new HashMap<Long, Long>();

        for (Map.Entry<Long, Long> entry : propsSet) {

            Long key = entry.getKey();

            Long value = entry.getValue();



            for (ConvertRuleDetail ruleDetail : ruleDetails) {

                long propertyId = ruleDetail.getSrcPropertyId();

                long propertyValueId = ruleDetail.getSrcPropertyValueId();

                long targetPropertyId = ruleDetail.getTargetPropertyId();

                long targetPropertyValueId = ruleDetail.getTargetPropertyValueId();

                if(targetPropertyId != -1 && targetPropertyValueId != -1) {
/*
                    //如果源类目中不存在对应的属性和属性值信息，但是在目标中含有的情况
                    if(propertyId == -1 || propertyValueId == -1) {
                        getAttrsMap(attrsMap, ruleDetail);
                    } else if (propertyId != -1 && propertyValueId != -1) {

                        CategoryProperty srcPropertyId = categoryPropertyMapper.findCategoryPropertyById(propertyId);

                        CategoryPropertyValue srcPropertyValueId = categoryPropertyMapper.findCategoryPropertyValueById(propertyValueId);

                        if (key.equals(srcPropertyId.getPropertyId()) && value.equals(srcPropertyValueId.getPropertyValueId())) {

                            getAttrsMap(attrsMap, ruleDetail);

                        }
                    }*/

                    getAttrsMap(attrsMap, ruleDetail);

                }
            }

        }


        return mergeAttrs(attrsMap);

    }

    /**
     * 取出目标类目的属性和属性值
     * @param attrsMap
     * @param ruleDetail
     */

    private void getAttrsMap(Map<Long, Long> attrsMap, ConvertRuleDetail ruleDetail) {
        long propertyId1 = ruleDetail.getTargetPropertyId();

        long propertyValueId1 = ruleDetail.getTargetPropertyValueId();

        CategoryProperty targetPropertyId = categoryPropertyMapper.findCategoryPropertyById(propertyId1);

        CategoryPropertyValue targetPropertyValueId = categoryPropertyMapper.findCategoryPropertyValueById(propertyValueId1);

        //将目标属性和属性值放入到map集合中
        attrsMap.put(targetPropertyId.getPropertyId(), targetPropertyValueId.getPropertyValueId());
    }


    /**
     * 拼接属性和属性值，京东适用
     * @param longMap    key 为属性， value为属性值
     * @return
     */
    private String mergeAttrs(Map<Long, Long> longMap) {
        StringBuffer stringBuffer = new StringBuffer();

        Set<Map.Entry<Long, Long>> entrySet = longMap.entrySet();

        for(Map.Entry<Long, Long> entry : entrySet) {

            Long key = entry.getKey();

            Long value = entry.getValue();



            stringBuffer.append("|").append(key.toString()).append(":").append(value.toString());
        }


        return stringBuffer.toString().substring(1);

    }

    private Map<Long, Long> splitProps(String props) {

        Map<Long, Long> propsMap = new HashMap<Long, Long>();
        ;

        String[] split = props.trim().split(";");

        for (String string : split) {
            String[] split1 = string.split(":");
            propsMap.put(Long.parseLong(split1[0]), Long.parseLong(split1[1]));
        }
        return propsMap;
    }


}
