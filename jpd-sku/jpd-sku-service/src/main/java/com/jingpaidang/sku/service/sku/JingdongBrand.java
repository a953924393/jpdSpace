package com.jingpaidang.sku.service.sku;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: jackChen
 * Date: 7/11/13
 * Time: 1:19 PM
 */
public class JingdongBrand {
    private static final Logger logger = LoggerFactory.getLogger(JingdongBrand.class);

    public List<String> getConn(String queryKey, int pageNum) throws Exception {

        String address = "http://search.jd.com/Search?keyword=" + queryKey + "&enc=utf-8&area=1&qr=&qrst=UNEXPAND&et=&rt=1&sttr=&area=1&click=&psort=&page=" + pageNum;

        List<String> stringList = new ArrayList<String>();
        Document doc = Jsoup.connect(address)
                .data("query", "Java")
                .userAgent("Mozilla")
                .cookie("auth", "token")
                .timeout(1000000)
                .post();

        Elements skus = doc.select("div.p-name");

        for(Element element : skus) {
            String sku1 = element.child(0).text();
            stringList.add(sku1);
        }

        return stringList;
    }


    public Map<String, String> getBrandAddress(String queryKey, String brand, int begin, int end) throws Exception{
        logger.info("开始查找=============关键词========"+queryKey + "====品牌======" + brand);

        int pageNum ;
        int count = 0;

        if(end > 1000) {
            end = 1000;
        }


        for(pageNum =begin; pageNum <= end; pageNum++) {
            logger.info("===>正在查找第" + pageNum + "页");

            if(pageNum == 101) {
                break;
            }
            List<String> conn = this.getConn(queryKey, pageNum);
            count = this.isEqual(brand, conn);

            if(count <= 36) {
                logger.info("已经查找到===============" + brand);

                break;
            }
        }


        Map<String, String> map = new HashMap<String, String>();

        map.put("pageNum", pageNum + "");
        map.put("tr", (count%4==0?(count/4):(count/4+1)) + "");
        map.put("td", (count%4==0?4:count%4) + "");
        return map;
    }


    public int isEqual(String queryBrand, List<String> titles) {

        int count = 1;
        for(String title : titles) {
            if(!title.toUpperCase().contains(queryBrand.toUpperCase())){
                count ++ ;
            } else {
                break;
            }
        }

        return count;
    }


    @Test
    public void testGetConn() throws Exception {
        String queryKey = "手机";

        int pageNum = 1;

        String brand = "三星";

        JingdongBrand jingdongBrand = new JingdongBrand();

        jingdongBrand.getBrandAddress(queryKey,brand,1,2);
    }
}
