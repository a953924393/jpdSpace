package com.jingpaidang.sku.service.sku;

import com.jingpaidang.sku.common.constant.BaseConstant;
import com.jingpaidang.sku.dao.keyword.KeywordMapper;
import com.jingpaidang.sku.dao.sku.SkuMapper;
import com.jingpaidang.sku.domain.keyword.Keyword;
import com.jingpaidang.sku.domain.sku.Sku;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: jackchen
 * Date: 7/8/13
 * Time: 8:27 AM
 * To change this template use File | Settings | File Templates.
 */


@Service
public class JDSkuService {

    private static final Logger logger = LoggerFactory.getLogger(JDSkuService.class);

    @Resource
    private KeywordMapper keywordDao;

    @Resource
    private SkuMapper skuDao;


    public JDSkuService() {

    }

    private List<Keyword> findUseKeywords() {


        List<Keyword> keywordList = keywordDao.findAllKeyword();

        Iterator<Keyword> iterator = keywordList.iterator();

        while (iterator.hasNext()) {
            Keyword next = iterator.next();

            if("unuserful".equals(next.getStatus()) && next.getNumber()<5) {
                iterator.remove();
            }

        }

        return keywordList;
    }

    public void saveSkuTask() {
        List<Keyword> keywordList = findUseKeywords();

        List<Keyword> strings;

        int i = 0;

        strings = new CopyOnWriteArrayList<Keyword>();

        for (Keyword s : keywordList) {

            strings.add(s);

            i++;

            if (i % 50 == 0 || i == keywordList.size()) {

                newThread(strings);

                strings.clear();
            }
        }

    }

    private void newThread(List<Keyword> strings) {
        CopyOnWriteArrayList<Keyword> keywords = new CopyOnWriteArrayList<Keyword>(strings);
        JingdongSkuThread jingdongSkuThread = new JingdongSkuThread();
        jingdongSkuThread.setQueryKeyList(keywords);

        new Thread(jingdongSkuThread).start();
    }


    /**
     * 手动添加关键词，并查找sku保存到库中
     *
     * @param strings1
     */
    public void addKeywordAndSku(List<String> strings1) {


        for (String keyString : strings1) {
            Keyword keywordByName = keywordDao.findKeywordByName(keyString);
            if (keywordByName == null) {
                Keyword keyword = new Keyword();
                keyword.setKeyName(keyString);
                keyword.setCreateTime(new Date());
                keywordDao.addKeyword(keyword);
            }
        }

        List<Keyword> keywordList = keywordDao.findKeywords(strings1);

        List<Keyword> strings = new CopyOnWriteArrayList<Keyword>();

        int i = 0;

        for (Keyword s : keywordList) {

            strings.add(s);

            i++;

            if (i % 3 == 0 || i == keywordList.size()) {

                newThread(strings);

                strings.clear();
            }
        }
    }

    public void addSkus(Keyword keywordByName) throws Exception {


//        skuDao.deleteSkuByKid(keywordByName.getId());
        logger.info("keyword====" + keywordByName.getKeyName() + "======begin save");

        for (int i = 1; i < 51; i++) {


            List<String> skus = this.getConn(keywordByName.getKeyName(), i);

            if (skus.size() == 0) {
                break;
            }

            List<Sku> skuList = this.sku2Obj(skus, i, keywordByName.getId());

            skuDao.addSkuList(skuList);
            if (skus.size() < 36)
                break;
        }
        logger.info("keyword====" + keywordByName.getKeyName() + "======end save");

    }

    /**
     * 保存不存在的keyword
     *
     * @param keywords
     * @return
     */

    public List<Keyword> saveAndFindKeywords(List<String> keywords) {
        List<String> keyStrings = new ArrayList<String>();

        for (String keyword : keywords) {
            Keyword keywordByName = keywordDao.findKeywordByName(keyword);
            if (keywordByName == null) {
                keyStrings.add(keyword);
            }
        }

        keywordDao.addKeywordList(keyStrings);

        List<Keyword> allKeyword = keywordDao.findAllKeyword();
        return allKeyword;
    }


    /**
     * 通过关键词name查询关键词
     *
     * @param queryKey
     * @return
     */
    private Keyword findKeywordByName(String queryKey) {
        Keyword keywordByName = keywordDao.findKeywordByName(queryKey);

        if (keywordByName != null) {
            return keywordByName;
        }
        Keyword keyword = new Keyword();
        keyword.setKeyName(queryKey);


        keywordDao.addKeyword(keyword);
        keywordByName = keywordDao.findKeywordByName(queryKey);

        return keywordByName;
    }


    /**
     * 将sku转变为对象便于保存
     *
     * @param skuNums
     * @param pageNum
     * @param keyId
     * @return
     */
    private List<Sku> sku2Obj(List<String> skuNums, int pageNum, int keyId) {
        List<Sku> skuList = new ArrayList<Sku>();

        Sku sku = null;

        int i = 1;

        for (String skuNum : skuNums) {

            sku = new Sku();

            sku.setSkuNum(skuNum);

            sku.setKeyId(keyId);

            sku.setLocation(i + (pageNum - 1) * 36);

            skuList.add(sku);

            i++;
        }

        return skuList;
    }

    /**
     * 获得链接返回sku集合
     *
     * @param queryKey
     * @param pageNum
     * @return
     * @throws Exception
     */
    public List<String> getConn(String queryKey, int pageNum) throws Exception {
        String encode = URLEncoder.encode(queryKey, "utf-8");


        String address = "http://search.jd.com/Search?keyword=" + encode + "&enc=utf-8&area=1&qr=&qrst=UNEXPAND&et=&rt=1&sttr=" + "&area=1&click=&psort=&page=" + pageNum;

        List<String> stringList = new ArrayList<String>();
        Document doc = Jsoup.connect(address)
                .data("query", "Java")
                .userAgent("Mozilla")
                .timeout(10000000)
                .post();


        Elements skus = doc.select("li[sku]");

        for (Element element : skus) {
            String sku1 = element.attr("sku");
            stringList.add(sku1);
        }

        return stringList;
    }

    public int isEqual(String querySku, List<String> skus) {

        int count = 1;
        for (String sku : skus) {
            if (!querySku.equals(sku)) {
                count++;
            } else {
                break;
            }
        }

        return count;
    }

    public boolean isQueryKey(String queryKey) {
        Keyword keywordByName = keywordDao.findKeywordByName(queryKey);

        if (keywordByName == null || keywordByName.getId() == 0) {

            keywordByName = new Keyword();

            keywordByName.setCreateTime(new Date());
            keywordByName.setKeyName(queryKey);
            keywordByName.setStatus(BaseConstant.UNUSEFUL);
            keywordDao.addKeyword(keywordByName);
            return false;
        } else {
            keywordByName.setUpdateTime(new Date());
            keywordByName.setNumber(keywordByName.getNumber() + 1);
            keywordDao.updateKeyword(keywordByName);
            return true;
        }
    }

    /**
     * 查询sku的位置
     *
     * @param queryKey
     * @param sku
     * @return
     * @throws Exception
     */
    public Map<String, Integer> getSkuLocation(String queryKey, String sku) throws Exception {
        List<Sku> skuBySkuNumAndKey = skuDao.findSkuBySkuNumAndKey(queryKey, sku);

        Sku sku1;

        if (skuBySkuNumAndKey.size() != 0) {

            sku1 = skuBySkuNumAndKey.get(0);
            int location = sku1.getLocation();

            Map<String, Integer> map = new HashMap<String, Integer>();

            map.put("pageNum", location / 36 + 1);
            int count = location % 36;

            map.put("tr", count / 4 + 1);
            map.put("td", count % 4 == 0 ? 4 : count % 4);

            return map;
        } else {
            return null;
        }

    }

    /**
     * 使用字符串解析，
     *
     * @param queryKey
     * @param sku
     * @return
     * @throws Exception
     */
    @Deprecated
    public Map<String, Integer> getSkuAddress(String queryKey, String sku) throws Exception {
        JDSkuService jingdongSku = new JDSkuService();
        logger.info("开始查找=========================》");
        int pageNum;
        int count = 0;

        for (pageNum = 1; pageNum < 1000; pageNum++) {
            logger.info("===>正在查找第" + pageNum + "页");

            List<String> conn = jingdongSku.getConn(queryKey, pageNum);
            count = jingdongSku.isEqual(sku, conn);

            if (count <= 36) {
                break;
            }
        }
        logger.info("已经查找到=====================》");

        Map<String, Integer> map = new HashMap<String, Integer>();

        map.put("pageNum", pageNum);
        map.put("tr", count / 4 + 1);
        map.put("td", count % 4 == 0 ? 4 : count % 4);
        return map;
    }

    public Map<String, String> getSkuAddress(String queryKey, String sku, int begin, int end) throws Exception {
        JDSkuService jingdongSku = new JDSkuService();
        logger.info("开始查找=======关键词=====" + queryKey + "========sku" + sku);

        int pageNum;
        int count = 0;

        if (end > 1000) {
            end = 1000;
        }


        for (pageNum = begin; pageNum <= end; pageNum++) {
            logger.info("===>正在查找第" + pageNum + "页");

            if (pageNum == 101) {
                break;
            }
            List<String> conn = jingdongSku.getConn(queryKey, pageNum);
            count = jingdongSku.isEqual(sku, conn);

            if (count <= 36) {
                logger.info("已经查找到===============" + queryKey);

                break;
            }
        }


        Map<String, String> map = new HashMap<String, String>();

        map.put("pageNum", pageNum + "");
        map.put("tr", count / 4 + 1 + "");
        map.put("td", (count % 4 == 0 ? 4 : count % 4) + "");
        return map;
    }

    public void saveKeyList(List<String> keyNameList) {
        keywordDao.addKeywordList(keyNameList);
    }


}
