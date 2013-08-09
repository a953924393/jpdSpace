package com.jingpaidang.crm.service.example;

import com.jingpaidang.crm.dao.example.ExampleMapper;
import com.jingpaidang.crm.domain.example.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: JackChan
 * Date: 8/6/13
 * Time: 10:41 AM
 */
@Service
public class ExampleService {
    private Logger logger = LoggerFactory.getLogger(ExampleService.class);

    @Resource
    private ExampleMapper exampleMapper;

    public List<Example> findAllExamples() {
        List<Example> examples =  exampleMapper.selectAll();

        return examples;
    }



    public void insert(Example example) {
        exampleMapper.insert(example);
    }

    public String testAop(String a, int b, boolean c,Example example) {

        return a + b + c + example;
    }

}
