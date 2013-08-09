package com.jingpaidang.toolSystem.servlet;

import com.jingpaidang.toolSystem.service.JDSkuService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Created with IntelliJ IDEA. User: jackchen Date: 7/8/13 Time: 9:38 AM To
 * change this template use File | Settings | File Templates.
 */
public class SkuSearchServlet extends HttpServlet {
    // 第二步：覆盖doGet()方法
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();

        // 第三步：获取HTTP请求中的参数信息
        String sku = request.getParameter("sku");
        String queryKey = request.getParameter("queryKey");

        JDSkuService jingdongSku = new JDSkuService();

        Map<String, Integer> map;
        String json;
        try {

            if(!jingdongSku.isQueryKey(queryKey)) {

                json = new String ("{status:'keyIsNotExist'}");

            }   else {

                map = jingdongSku.getSkuLocation(queryKey, sku);


                if (map == null) {

                    json = new String("{status:'skuIsNotExist'}");

                } else {

                    int pageNum = map.get("pageNum");

                    int tr = map.get("tr");

                    int td = map.get("td");

                    json = new String("{status:'skuIsExist'," + "pageNum:" + pageNum + ",tr:" + tr + ",td:" + td + "}");

                }

            }


            writer.print(json);


        } catch (Exception e) {

            e.printStackTrace(); // To change body of catch statement use File |

            writer.print("{status:'keyIsNotExist'}");                        // Settings | File Templates.
        } finally {

            writer.close();

        }


    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException, ServletException {

        this.doPost(request, response);
    }
}
