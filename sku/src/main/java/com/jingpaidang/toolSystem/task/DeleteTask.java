package com.jingpaidang.toolSystem.task;

import com.jingpaidang.toolSystem.dao.KeywordDao;
import com.jingpaidang.toolSystem.dao.SkuDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;

/**
 * Created with IntelliJ IDEA.
 * User: JackChan
 * Date: 8/5/13
 * Time: 10:06 AM
 */
public class DeleteTask extends TimerTask {
    private static final Logger logger = LoggerFactory.getLogger(DeleteTask.class);

    @Override
    public void run() {

        SkuDao skuDao = new SkuDao();

        skuDao.deleteSkuByTime();


        KeywordDao keywordDao = new KeywordDao();

        keywordDao.deleteKeywordByTime();


    }
}
