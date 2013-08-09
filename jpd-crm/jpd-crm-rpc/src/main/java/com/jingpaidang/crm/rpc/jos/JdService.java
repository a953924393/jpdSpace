package com.jingpaidang.crm.rpc.jos;

import com.jd.open.api.sdk.DefaultJdClient;
import com.jd.open.api.sdk.JdClient;
import com.jd.open.api.sdk.JdException;
import com.jd.open.api.sdk.request.category.CategoryAttributeSearchRequest;
import com.jd.open.api.sdk.request.category.CategoryAttributeValueSearchRequest;
import com.jd.open.api.sdk.request.category.CategorySearchRequest;
import com.jd.open.api.sdk.request.order.OrderSearchRequest;
import com.jd.open.api.sdk.request.sellercat.SellerCatsGetRequest;
import com.jd.open.api.sdk.request.ware.WareAddRequest;
import com.jd.open.api.sdk.request.ware.WareDelistingGetRequest;
import com.jd.open.api.sdk.request.ware.WareListRequest;
import com.jd.open.api.sdk.request.ware.WareListingGetRequest;
import com.jd.open.api.sdk.response.category.CategoryAttributeSearchResponse;
import com.jd.open.api.sdk.response.category.CategoryAttributeValueSearchResponse;
import com.jd.open.api.sdk.response.category.CategorySearchResponse;
import com.jd.open.api.sdk.response.order.OrderSearchResponse;
import com.jd.open.api.sdk.response.sellercat.SellerCatsGetResponse;
import com.jd.open.api.sdk.response.ware.WareAddResponse;
import com.jd.open.api.sdk.response.ware.WareDelistingGetResponse;
import com.jd.open.api.sdk.response.ware.WareListResponse;
import com.jd.open.api.sdk.response.ware.WareListingGetResponse;
import com.jingpaidang.crm.common.utils.JSONUtils;
import com.jingpaidang.crm.common.utils.StringUtil;
import com.jingpaidang.crm.common.utils.ValidateUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * @author jackchen
 * @version 1.0-SNAPSHOT
 */
@Service("jdService")
public class JdService {

    //private static final String REDIRECT_URI = "http://cshop.jingpaidang.com:8080/user/user!enterSystem.action?flag=3";
    @Value("${jd.url}")
    private String URL;
    @Value("${jd.redirect.uri}")
    private String REDIRECT_URI;
    @Value("${jd.app.key}")
    private String APP_KEY;
    @Value("${jd.app.secret}")
    private String APP_SECRET;
    @Value("${jd.authurl}")
    private String AUTHURL;
    //TODO 增加sku的方法

//    @Resource
//    private AccountMapper accountMapper;
//

    public SellerCatsGetResponse getShopCategory(String accessToken) throws JdException {
        JdClient client = new DefaultJdClient(URL, accessToken, APP_KEY,
                APP_SECRET);

        SellerCatsGetRequest request = new SellerCatsGetRequest();
        SellerCatsGetResponse response = client.execute(request);
        return response;
    }


    /**
     * 获取京东商家的所有授权类目
     *
     * @param
     * @return
     */
    public CategorySearchResponse getCategorysOfUser(String accessToken) throws JdException {
        JdClient client = new DefaultJdClient(URL, accessToken, APP_KEY,
                APP_SECRET);

        CategorySearchRequest request = new CategorySearchRequest();
        CategorySearchResponse response = client.execute(request);
        return response;
    }

    /**
     * 获取京东商家类目的属性信息
     */
    public CategoryAttributeSearchResponse getCategoryAttribute(String accessToken, String cid) throws JdException {
        JdClient client = new DefaultJdClient(URL, accessToken, APP_KEY,
                APP_SECRET);

        CategoryAttributeSearchRequest request = new CategoryAttributeSearchRequest();

        // 类目id
        request.setCid(cid);
        request.setFields("aid,name,is_key_prop,is_sale_prop,status,att_type,input_type,is_req,is_fet,cid");
        CategoryAttributeSearchResponse response = client.execute(request);

        if (response.getCode() != "0" && !response.getCode().equals("0")) {
            throw new JdException();
        }

        return response;
    }

    /**
     * @param avs 属性和属性值 id串，格式例如(aid)或(aid;aid)或(aid:vid)或(aid:vid;aid:vid)或(aid;aid:vid)
     *            获取京东商家类目的属性值信息
     */
    public CategoryAttributeValueSearchResponse getCategoryAttributeValue(String accessToken, String avs) throws JdException {
        JdClient client = new DefaultJdClient(URL, accessToken, APP_KEY,
                APP_SECRET);

        CategoryAttributeValueSearchRequest request = new CategoryAttributeValueSearchRequest();

        // 属性id
        request.setAvs(avs);
        request.setFields("aid,vid,name");
        CategoryAttributeValueSearchResponse response = client
                .execute(request);
        return response;
    }

    /**
     * 获取京东多个商品数据 根据wareIds
     */
    public WareListResponse getWareList(String accessToken, String wareIds) throws JdException {
        JdClient client = new DefaultJdClient(URL, accessToken, APP_KEY,
                APP_SECRET);

        WareListRequest wareListRequest = new WareListRequest();

        wareListRequest.setWareIds(wareIds);
        WareListResponse res = client.execute(wareListRequest);
        return res;
    }

    /**
     * 得到上架商品的信息
     *
     * @param accessToken
     * @param cid         分类id，可有可无
     * @param page        第多少页
     * @param pageSize    每页显示数量
     * @return
     */
    public WareListingGetResponse getWareList(String accessToken, String cid, String page, String pageSize) throws JdException {
        JdClient client = new DefaultJdClient(URL, accessToken, APP_KEY,
                APP_SECRET);

        WareListingGetRequest wareListRequest = new WareListingGetRequest();

        if (ValidateUtil.isValidated(cid)) {
            wareListRequest.setCid(cid);
        }
        wareListRequest.setPage(page);
        wareListRequest.setPageSize(pageSize);
        WareListingGetResponse res = client.execute(wareListRequest);
        return res;
    }

    /**
     * 得到下架商品的信息
     *
     * @param accessToken
     * @param cid         分类id，可有可无
     * @param page        第多少页
     * @param pageSize    每页显示数量
     * @return
     */
    public WareDelistingGetResponse getWareDeList(String accessToken, String cid, String page, String pageSize) throws JdException {
        JdClient client = new DefaultJdClient(URL, accessToken, APP_KEY,
                APP_SECRET);

        WareDelistingGetRequest wareListRequest = new WareDelistingGetRequest();

        if (ValidateUtil.isValidated(cid)) {
            wareListRequest.setCid(cid);
        }
        wareListRequest.setPage(page);
        wareListRequest.setPageSize(pageSize);
        WareDelistingGetResponse res = client.execute(wareListRequest);
        return res;
    }


    /**
     * 添加单个京东商品
     */
    public WareAddResponse addWare(String accessToken, WareAddRequest request) throws JdException {
        JdClient client = new DefaultJdClient(URL, accessToken, APP_KEY,
                APP_SECRET);
        WareAddResponse wareAddResponse = client.execute(request);

        return wareAddResponse;
    }

    /**
     * 单个添加商品
     *
     * @param wareMap     属性值map集合， key为属性名，value为属性值
     * @param accessToken
     * @param wareImage   商品主图
     * @return
     */
    public WareAddResponse addWare(String accessToken, Map<String, String> wareMap,
                                   byte[] wareImage) throws JdException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, ClassNotFoundException {
        JdClient client = new DefaultJdClient(URL, accessToken, APP_KEY,
                APP_SECRET);

        WareAddRequest wareAddRequest = new WareAddRequest();
        // 利用反射调用方法
        Class clazz = Class
                .forName("com.jd.open.api.sdk.request.ware.WareAddRequest");
        for (String attName : wareMap.keySet()) {
            if (ValidateUtil.isValidated(attName)) {
                // 获得方法名
                String setName = "set" + attName.substring(0, 1).toUpperCase()
                        + attName.substring(1);
                Method method = clazz.getMethod(setName, String.class);
                method.invoke(wareAddRequest, wareMap.get(attName));
            }
        }
        wareAddRequest.setWareImage(wareImage);
        WareAddResponse response = client.execute(wareAddRequest);

        return response;
    }
    /**
     * 
     * @Title:getOrderSearchResponse 
     * @Description:TODO(获取商家订单信息) 
     * @param:@param accessToken
     * @param:@param startDate
     * @param:@param endDate
     * @param:@param orderState
     * @param:@param page
     * @param:@param pageSize
     * @param:@return
     * @param:@throws JdException 
     * @return:OrderSearchResponse 
     * @throws 
     * @Create: 2013-8-7 下午3:29:58
     * @Author : Alex
     */
    public OrderSearchResponse getOrderSearchResponse(String accessToken,String startDate,String endDate,
    		String orderState,String page,String pageSize) throws JdException{
    	JdClient client = new DefaultJdClient(URL, accessToken, APP_KEY,
                APP_SECRET);
    	OrderSearchRequest request = new OrderSearchRequest();
    	request.setStartDate(startDate);
    	request.setEndDate(endDate);
    	request.setOrderState(orderState);
    	request.setPage(page);
    	request.setPageSize(pageSize);
    	request.setOptionalFields("vender_id,pay_type,order_total_price,order_payment,order_seller_price,freight_price,seller_discount," +
    			"order_state,order_state_remark,delivery_type,invoice_info,order_remark,order_start_time,order_end_time,item_info_list," +
    			"coupon_detail_list,return_order,vender_remark,balance_used,modified,pin,consignee_info");
    	OrderSearchResponse response = client.execute(request);
    	return response;
    }
}
