package com.jingpaidang.toolSystem.service;

import com.jingpaidang.toolSystem.domain.Keyword;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: JackChan
 * Date: 7/17/13
 * Time: 10:43 AM
 */
public class JingdongSkuThread implements Runnable{


    public JingdongSkuThread(){};

    @Override
    public void run() {

            JDSkuService jingdongSku = new JDSkuService();
            System.err.println("开始任务");
            try {
                for(Keyword queryKey : queryKeyList) {

                    jingdongSku.addSkus(queryKey);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    private List<Keyword> queryKeyList;

    public List<Keyword> getQueryKeyList() {
        return queryKeyList;
    }

    public void setQueryKeyList(List<Keyword> queryKeyList) {
        this.queryKeyList = queryKeyList;
    }
}
