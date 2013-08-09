package com.jingpaidang.sku.controller.sku;

import com.jingpaidang.sku.common.util.StringUtils;
import com.jingpaidang.sku.controller.base.BaseController;
import com.jingpaidang.sku.service.sku.JDSkuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: JackChan
 * Date: 8/9/13
 * Time: 3:26 PM
 */
@Controller
@RequestMapping("sku")
public class SkuController extends BaseController {
    @Resource
    private JDSkuService jdSkuService;

    @RequestMapping(value = "addSku", method = RequestMethod.POST)
    public String addSku(HttpServletRequest request) {
        String keywordList = request.getParameter("keywordList");

        List<String> keywords = StringUtils.split(keywordList);

        jdSkuService.addKeywordAndSku(keywords);
        return null;
    }

    @RequestMapping("searSkuPage")
    public String searSkuPage() {
        return "sku/searchSku";
    }


}
