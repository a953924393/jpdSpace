package com.jingpaidang.toolSystem.task;

import com.jingpaidang.toolSystem.domain.Keyword;
import com.jingpaidang.toolSystem.service.JDSkuService;
import com.jingpaidang.toolSystem.service.JingdongSkuThread;
import com.jingpaidang.toolSystem.util.PropUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: JackChan
 * Date: 7/17/13
 * Time: 9:31 AM
 */
public class SaveSkuTask extends TimerTask {
    private static final Logger logger = LoggerFactory.getLogger(SaveSkuTask.class);

    @Override
    public void run() {
        JDSkuService jdSkuService = new JDSkuService();
        jdSkuService.saveSkuTask();
    }
}



