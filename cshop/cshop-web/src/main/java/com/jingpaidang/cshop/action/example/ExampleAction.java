package com.jingpaidang.cshop.action.example;

import com.jd.open.api.sdk.JdException;
import com.jingpaidang.cshop.action.admin.BaseAction;
import com.jingpaidang.cshop.domain.example.Example;
import com.jingpaidang.cshop.service.example.ExampleService;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;

/**
 * example action.
 *
 * @author Thomson Tang
 * @version 1.0-SNAPSHOT
 * @date 5/28/13
 */

@ParentPackage("example")
public class ExampleAction extends BaseAction {

    private static final long serialVersionUID = -2055488766048093449L;

    private static final Logger logger = LoggerFactory.getLogger(ExampleAction.class);


    private List<Example> examples;

    @Resource
    private ExampleService exampleService;

    /**
     * Query the example list.
     *
     * @return String
     */
    public String queryList() {
        examples = exampleService.getAllExamples(page);
        return LIST;
    }

    private void setPageBar() {
        if (null != examples && examples.size() > 0) {
            this.setDisplayPageBar(true);
        } else {
            this.setDisplayPageBar(false);
        }
    }

    public String testJdServiceProxy(){
        try {
            exampleService.testJdServiceProxy();
        } catch (JdException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }

    public List<Example> getExamples() {
        return examples;
    }

    public void setExamples(List<Example> examples) {
        this.examples = examples;
    }
}
