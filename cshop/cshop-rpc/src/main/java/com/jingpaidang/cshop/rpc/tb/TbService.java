package com.jingpaidang.cshop.rpc.tb;

import com.jingpaidang.cshop.common.utils.JSONUtils;
import com.jingpaidang.cshop.common.utils.StringUtil;
import com.jingpaidang.cshop.common.utils.ValidateUtil;
import com.jingpaidang.cshop.dao.user.AccountMapper;
import com.jingpaidang.cshop.domain.user.Account;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.Item;
import com.taobao.api.domain.Sku;
import com.taobao.api.domain.Task;
import com.taobao.api.domain.User;
import com.taobao.api.internal.util.WebUtils;
import com.taobao.api.request.*;
import com.taobao.api.response.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.net.ssl.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.*;

@Service("tbService")
public class TbService {
    private static final String URL = "http://gw.api.taobao.com/router/rest";
    //授权uri
    private static final String AUTHURI = "https://oauth.taobao.com/token";
    @Value("${taobao.redirect.uri}")
    private String REDIRECT_URI;
    @Value("${taobao.app.key}")
    private String APP_KEY;
    @Value("${taobao.app.secret}")
    private String APP_SECRET;


    @Resource
    private AccountMapper accountMapper;


    public User getUser(String sessionKey) throws ApiException {
        TaobaoClient client = new DefaultTaobaoClient(URL, APP_KEY, APP_SECRET);

        UserSellerGetRequest userSellerGetRequest = new UserSellerGetRequest();

        userSellerGetRequest.setFields("user_id,nick,sex,seller_credit,type,has_more_pic,item_img_num,item_img_size,prop_img_num,prop_img_size,auto_repost,promoted_type,status,alipay_bind,consumer_protection,avatar,liangpin,sign_food_seller_promise,has_shop,is_lightning_consignment,has_sub_stock,is_golden_seller,vip_info,magazine_subscribe,vertical_market,online_gaming");

        return client.execute(userSellerGetRequest, sessionKey).getUser();
    }


    /**
     * 获取商品属性信息
     *
     * @param cid
     * @return
     */
    public ItempropsGetResponse getCategoryAttribute(Long cid) throws ApiException {
        TaobaoClient client = new DefaultTaobaoClient(URL, APP_KEY, APP_SECRET);


        ItempropsGetRequest req = new ItempropsGetRequest();
        req.setCid(cid);
        req.setFields("pid, name, must, multi, prop_values,required,is_key_prop");

        return client.execute(req);

    }

    /**
     * @param flag 1L表示淘宝，， 2L表示天猫
     * @param cid
     * @param pvs  属性和属性值 id串，格式例如"pid1;pid2"或"pid1:vid1;pid2:vid2"或"pid1;pid2:vid2"
     * @return
     */

    public ItempropvaluesGetResponse getCategoryAttributeValue(Long cid, String pvs, Long flag) throws ApiException {
        TaobaoClient client = new DefaultTaobaoClient(URL, APP_KEY, APP_SECRET);


        ItempropvaluesGetRequest req = new ItempropvaluesGetRequest();
        req.setCid(cid);
        req.setFields("cid,pid,prop_name,vid,name,name_alias,status,sort_order");
        req.setType(flag);
        req.setPvs(pvs);

        ItempropvaluesGetResponse attributeValue = null;
        attributeValue = client.execute(req);

        return attributeValue;
    }


    public List<Item> getItems(Long cid, String sessionKey, String queryKey, Integer sale) throws IOException, ApiException {
        ItemsOnsaleGetResponse itemsOnsale = null;
        ItemsInventoryGetResponse itemsInventory = null;
        List<Item> items = new ArrayList<Item>();
        if (sale == null) {
            itemsOnsale = getItemsOnsale(sessionKey, queryKey);
            itemsInventory = getItemsInventory(sessionKey, queryKey);

        } else if (sale == 0) {

            itemsInventory = getItemsInventory(sessionKey, queryKey);
        } else if (sale == 1) {
            itemsOnsale = getItemsOnsale(sessionKey, queryKey);
        }
        //todo 判断是否为空，如果为空，则直接返回空

        if (itemsOnsale != null && itemsOnsale.getTotalResults() > 0) {

            items = itemsOnsale.getItems();
        }
        if (itemsInventory != null && itemsInventory.getTotalResults() > 0) {

            items.addAll(itemsInventory.getItems());
        }


        Iterator<Item> iterator = items.iterator();

        //迭代删除不符合条件的item
        while (iterator.hasNext()) {
            Item item = iterator.next();

            if (item.getCid().longValue() != cid.longValue()) {
                iterator.remove();
            }
        }
        return items;
    }

    /**
     * @throws
     * @Title:getAllItems
     * @Description:TODO(查询店铺的商品)
     * @param:@param sessionKey
     * @param:@return
     * @return:List<Item>
     * @Create: 2013-7-3 下午4:50:34
     * @Author : Alex
     */
    public List<Item> getAllItems(String sessionKey) throws IOException, ApiException {
        ItemsOnsaleGetResponse itemsOnsale = null;
        ItemsInventoryGetResponse itemsInventory = null;
        List<Item> items = new ArrayList<Item>();
        itemsOnsale = getItemsOnsale(sessionKey);
        itemsInventory = getItemsInventory(sessionKey);

        if (itemsOnsale != null && itemsOnsale.getTotalResults() > 0) {
            ItemsListGetResponse itemListGetResponse = getItemsList(itemsOnsale.getItems(), sessionKey);
            if (itemListGetResponse != null) {
                items = itemListGetResponse.getItems();
            }
        }
        if (itemsInventory != null && itemsInventory.getTotalResults() > 0) {
            ItemsListGetResponse itemListGetResponse = getItemsList(itemsInventory.getItems(), sessionKey);
            if (itemListGetResponse != null) {
                items.addAll(itemListGetResponse.getItems());
            }
        }
        return items;
    }

    /**
     * @throws
     * @Title:getItemsList
     * @Description:TODO()
     * @param:@param items
     * @param:@param sessionKey
     * @param:@return
     * @return:ItemsListGetResponse
     * @Create: 2013-7-4 上午10:01:21
     * @Author : Alex
     */
    private ItemsListGetResponse getItemsList(List<Item> items, String sessionKey) throws IOException, ApiException {
        StringBuffer sb = new StringBuffer();
        for (Item item : items) {
            sb.append(item.getNumIid());
            sb.append(",");
        }
        String ids = sb.toString().substring(0, sb.toString().length() - 1);
        return getItemsByNumiid(sessionKey, ids);
    }

    /**
     * 通过商品获得该商品的所有sku并返回
     *
     * @param sessionKey
     * @param item
     * @return
     */
    public List<Sku> getSkus(String sessionKey, Item item) throws ApiException {

        List<Sku> skus = item.getSkus();
        List<Sku> skusAll = new ArrayList<Sku>();

        TaobaoClient client = new DefaultTaobaoClient(URL, APP_KEY, APP_SECRET);


        ItemSkuGetRequest req = new ItemSkuGetRequest();
        req.setFields("sku_id,iid,properties,quantity,price,outer_id,created,modified,status");
        req.setNumIid(item.getNumIid());
        for (Sku sku : skus) {
            req.setSkuId(sku.getSkuId());
            ItemSkuGetResponse execute = client.execute(req, sessionKey);
            skusAll.add(execute.getSku());

        }
        return skusAll;
    }

    /**
     * 该方法根据商品号得到商品的信息 可用于单个商品和批量获得商品信息
     *
     * @param sessionKey
     * @param numIids    多个numiids用 ","隔开
     * @return
     */
    public ItemsListGetResponse getItemsByNumiid(String sessionKey,
                                                 String numIids) throws IOException, ApiException {

        TaobaoClient client = new DefaultTaobaoClient(URL, APP_KEY, APP_SECRET);
        ItemsListGetRequest req = new ItemsListGetRequest();
        req.setFields("detail_url,num_iid,title,props_name,nick,type,item_weight,cid,seller_cids,props,input_pids,input_str,desc,pic_url,num,valid_thru,list_time,delist_time,stuff_status,location,price,post_fee,express_fee,ems_fee,has_discount,freight_payer,has_invoice,has_warranty,has_showcase,modified,increment,approve_status,postage_id,product_id,auction_point,property_alias,item_img,prop_img,sku,video,outer_id,is_virtual");
        req.setNumIids(numIids);
        ItemsListGetResponse response = client.execute(req, sessionKey);
        if (response.getErrorCode() != null) {
            String s = refreshToken(sessionKey);
            response = getItemsByNumiid(s, numIids);
        }
        return response;
    }


    /**
     * 查询淘宝用户的发布过商品的类目,此方法不应常用。获得类目后保存至本地数据库
     *
     * @param sessionKey
     * @return
     */
    public Map<Long, String> cateList(String sessionKey, String q) throws IOException, ApiException {
        Map<Long, String> map = new HashMap<Long, String>();

        List<Item> items = this.getItemsOnsale(sessionKey, q).getItems();

        // 将所有的商品都查询出来放在一个集合中
        items.addAll(this.getItemsInventory(sessionKey, q).getItems());

        for (Item item : items) {
            if (map.get(item.getCid()) == null) {
                ItemcatsGetResponse cates = this.getCates(item.getCid()
                        .toString(), null);

                map.put(item.getCid(), cates.getItemCats().get(0).getName());
            }
        }

        return map;
    }

    /**
     * 得到类目信息，如果输入了父类目pid，则得到该父类目的所有子类目信息 如果输入了cids，则得到该子类目的信息
     *
     * @param cids ，多个cid用","隔开
     * @return
     */
    public ItemcatsGetResponse getCates(String cids, Long pid) throws ApiException {
        TaobaoClient client = new DefaultTaobaoClient(URL, APP_KEY, APP_SECRET);
        ItemcatsGetRequest req = new ItemcatsGetRequest();
        req.setFields("cid,parent_cid,name,is_parent");
        if (cids != null) {
            req.setCids(cids);
        }
        if (pid != null) {
            req.setParentCid(pid);
        }
        ItemcatsGetResponse response = client.execute(req);

        return response;
    }

    /**
     * 返回卖家的所有在售的商品，返回的信息只包含fields内属性，若需要其他属性，请调用该类的 getItemsByNumiid()
     *
     * @param sessionKey
     * @param q          商品标题，like 查询
     * @return
     */
    public ItemsOnsaleGetResponse getItemsOnsale(String sessionKey, String q) throws IOException, ApiException {
        TaobaoClient client = new DefaultTaobaoClient(URL, APP_KEY, APP_SECRET);
        ItemsOnsaleGetRequest req = new ItemsOnsaleGetRequest();
        req.setFields("approve_status,num_iid,title,nick,type,cid,pic_url,num,props,valid_thru,list_time,price,has_discount,has_invoice,has_warranty,has_showcase,modified,delist_time,postage_id,seller_cids,outer_id");

        if (ValidateUtil.isValidated(q)) {
            req.setQ(q);
        }
        ItemsOnsaleGetResponse response = client.execute(req, sessionKey);
        if (response.getErrorCode() != null) {
            String s = refreshToken(sessionKey);
            response = getItemsOnsale(s, q);
        }
        return response;
    }

    /**
     * @throws
     * @Title:getItemsOnsale
     * @Description:TODO(返回卖家的所有在售的商品)
     * @param:@param sessionKey
     * @param:@return
     * @return:ItemsOnsaleGetResponse
     * @Create: 2013-7-3 下午4:53:53
     * @Author : Alex
     */
    public ItemsOnsaleGetResponse getItemsOnsale(String sessionKey) throws IOException, ApiException {
        TaobaoClient client = new DefaultTaobaoClient(URL, APP_KEY, APP_SECRET);
        ItemsOnsaleGetRequest req = new ItemsOnsaleGetRequest();
        req.setFields("num_iid ");

        ItemsOnsaleGetResponse response = client.execute(req, sessionKey);
        if (response.getErrorCode() != null) {
            String s = refreshToken(sessionKey);
            response = getItemsOnsale(s);
        }
        return response;
    }

    /**
     * 返回卖家的所有库存中的商品，返回的信息只包含fields内属性，若需要其他属性，请调用该类的 getItemsByNumiid()
     *
     * @param sessionKey
     * @param q          商品标题，like 查询
     * @return
     */
    public ItemsInventoryGetResponse getItemsInventory(String sessionKey,
                                                       String q) throws ApiException, IOException {
        TaobaoClient client = new DefaultTaobaoClient(URL, APP_KEY, APP_SECRET);
        ItemsInventoryGetRequest req = new ItemsInventoryGetRequest();
        req.setFields("approve_status,num_iid,title,nick,type,cid,pic_url,num,props,valid_thru,list_time,price,has_discount,has_invoice,has_warranty,has_showcase,modified,delist_time,postage_id,seller_cids,outer_id");
        if (ValidateUtil.isValidated(q)) {
            req.setQ(q);
        }
        ItemsInventoryGetResponse response = client
                .execute(req, sessionKey);
        if (response.getErrorCode() != null) {
            String s = refreshToken(sessionKey);
            response = getItemsInventory(s, q);
        }
        return response;
    }

    /**
     * @throws
     * @Title:getItemsInventory
     * @Description:TODO(返回卖家的所有库存中的商品)
     * @param:@param sessionKey
     * @param:@return
     * @return:ItemsInventoryGetResponse
     * @Create: 2013-7-3 下午4:55:23
     * @Author : Alex
     */
    public ItemsInventoryGetResponse getItemsInventory(String sessionKey) throws IOException, ApiException {
        TaobaoClient client = new DefaultTaobaoClient(URL, APP_KEY, APP_SECRET);
        ItemsInventoryGetRequest req = new ItemsInventoryGetRequest();
        req.setFields("num_iid ");

        ItemsInventoryGetResponse response = client.execute(req, sessionKey);
        if (response.getErrorCode() != null) {
            String s = refreshToken(sessionKey);
            response = getItemsInventory(s);
        }
        return response;
    }

    /**
     * 获取商家的所有授权类目，适用于天猫卖家，如果是淘宝卖家，将返回空数据
     *
     * @return
     */
    public ItemcatsAuthorizeGetResponse getCategorysOfUser(String sessionKey) throws IOException, ApiException {
        TaobaoClient client = new DefaultTaobaoClient(URL, APP_KEY, APP_SECRET);
        ItemcatsAuthorizeGetRequest req = new ItemcatsAuthorizeGetRequest();
        req.setFields("brand.vid, brand.name, item_cat.cid, item_cat.name, item_cat.status,item_cat.sort_order,item_cat.parent_cid,item_cat.is_parent, xinpin_item_cat.cid, xinpin_item_cat.name, xinpin_item_cat.status, xinpin_item_cat.sort_order, xinpin_item_cat.parent_cid, xinpin_item_cat.is_parent");

        ItemcatsAuthorizeGetResponse response = client.execute(req,
                sessionKey);
        if (response.getErrorCode() != null) {
            String s = refreshToken(sessionKey);
            response = getCategorysOfUser(s);
        }
        return response;
    }

    /**
     * 异步获取已经提交后的结果，返回一个下载url
     *
     * @param taskId
     */
    public Task getResult(Long taskId) throws ApiException {
        TaobaoClient client = new DefaultTaobaoClient(URL, APP_KEY, APP_SECRET);
        TopatsResultGetRequest req = new TopatsResultGetRequest();
        req.setTaskId(taskId);
        TopatsResultGetResponse rsp = client.execute(req);
        return rsp.getTask();
    }

    /**
     * 用于定时刷新accessToken，一般 刷新频率一般1/24h
     *
     * @param accessToken
     * @return 刷新后的access_token
     */
    public String refreshToken(String accessToken) throws IOException {

        Account account = accountMapper.findByAccessToken(accessToken);
        String refreshToken = account.getPlatformRefreshToken();


        Map<String, String> param = new HashMap<String, String>();
        param.put("grant_type", "refresh_token");
        param.put("refresh_token", refreshToken);
        param.put("client_id", APP_KEY);
        param.put("client_secret", APP_SECRET);
        param.put("scope", "item");
        param.put("view", "web");
        String responseJson = WebUtils.doPost(AUTHURI, param, 3000, 3000);
        Map jsonMap = JSONUtils.toObject(responseJson, HashMap.class);

        updateAccount(jsonMap);

        accessToken = (String) jsonMap.get("access_token");

        return accessToken;
    }

    public void updateAccount(Map<String, Object> map) {
        Account account = new Account();
        account.setPlatformLoginId(map.get("taobao_user_id").toString());
        account.setPlatformLoginName(map.get("taobao_user_nick").toString());
        account.setPlatformAccessToken(map.get("access_token").toString());
        account.setPlatformRefreshToken(map.get("refresh_token").toString());
        account.setAccessTokenExpireTime(map.get("expires_in").toString());
        account.setRefreshTokenExpireTime(map.get("re_expires_in").toString());

        Account byPlatformLoginId = accountMapper.findByPlatformLoginId(account.getPlatformLoginId());
        account.setModifyTime(new Date());
        account.setId(byPlatformLoginId.getId());
        accountMapper.update(account);
    }


    /**
     * 根据授权得到的code，state 取得授权信息
     *
     * @param code
     * @param state
     * @return {
     *         "w2_expires_in": 1800,
     *         "taobao_user_id": "85078597",
     *         "taobao_user_nick": "duoyuxianzi2008",
     *         "w1_expires_in": 86400,
     *         "re_expires_in": 86400,
     *         "r2_expires_in": 86400,
     *         "expires_in": 86400,
     *         "token_type": "Bearer",
     *         "refresh_token": "62021007ZZ07e9dff5b62fce6f28e137909f5eaa8b4defd85078597",
     *         "access_token": "62017009ZZ4297e40a9fedadbd84ab4557641f883fdd1ad85078597",
     *         "r1_expires_in": 86400}
     */
    public Map<String, Object> getAccessToken(String code, String state) throws NoSuchAlgorithmException, KeyManagementException, IOException {
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public void checkClientTrusted(X509Certificate[] certs,
                                           String authType) {
            }

            public void checkServerTrusted(X509Certificate[] certs,
                                           String authType) {
            }

        }};

        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection
                .setDefaultSSLSocketFactory(sc.getSocketFactory());

        // Create all-trusting host name verifier
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
        // Install the all-trusting host verifier
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

        URL url = new URL(
                AUTHURI + "?grant_type=authorization_code&client_id="
                        + APP_KEY + "&client_secret=" + APP_SECRET
                        + "&code=" + code + "&redirect_uri=" + REDIRECT_URI);
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url
                .openConnection();
        httpsURLConnection.setConnectTimeout(30000);
        httpsURLConnection.setReadTimeout(30000);
        httpsURLConnection.setDoOutput(true);
        httpsURLConnection.setDoInput(true);
        httpsURLConnection.setUseCaches(false);
        httpsURLConnection.setRequestMethod("POST");

        httpsURLConnection.connect();

        int responseCode = httpsURLConnection.getResponseCode();
        InputStream input = null;
        if (responseCode == 200) {
            input = httpsURLConnection.getInputStream();
        } else {
            input = httpsURLConnection.getErrorStream();
        }
        String s = StringUtil.inputStream2String(input);

        Map<String, Object> jsonMap = JSONUtils.toObject(s, Map.class);
        return jsonMap;
    }
}
