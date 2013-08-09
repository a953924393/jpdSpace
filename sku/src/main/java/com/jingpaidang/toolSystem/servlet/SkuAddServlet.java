package com.jingpaidang.toolSystem.servlet;

import com.jingpaidang.toolSystem.service.JDSkuService;
import com.jingpaidang.toolSystem.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: jackchen Date: 7/8/13 Time: 9:38 AM To
 * change this template use File | Settings | File Templates.
 */
public class SkuAddServlet extends HttpServlet {
    // 第二步：覆盖doGet()方法
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html");

        // 第三步：获取HTTP请求中的参数信息
        String keywordList = request.getParameter("keywordList");

        System.out.println(keywordList);

        List<String> keywords = StringUtils.split(keywordList);

        JDSkuService jdSkuService = new JDSkuService();

        jdSkuService.addKeywordAndSku(keywords);



    }



    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException, ServletException {

        this.doPost(request, response);
    }
}
