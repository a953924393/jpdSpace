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
@Deprecated
public class SkuServlet extends HttpServlet {
	// 第二步：覆盖doGet()方法
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
		PrintWriter writer = response.getWriter();

		// 第三步：获取HTTP请求中的参数信息
		String sku = request.getParameter("sku");
		String queryKey = request.getParameter("queryKey");
		String begin = request.getParameter("begin");
		String end = request.getParameter("end");
		int beginPage = 1;
		int endPage = 100;
		if(begin != null && !"".equals(begin.trim())){
			beginPage = Integer.parseInt(begin);

            if(beginPage < 1)
                beginPage = 1;
		}
		if(end != null && !"".equals(end.trim())){
			endPage = Integer.parseInt(end);
		}

		JDSkuService jingdongSku = new JDSkuService();

		Map<String, String> map = null;
		try {
			map = jingdongSku.getSkuAddress(queryKey.trim(), sku.trim(),beginPage,endPage);
			
			String pageNum = map.get("pageNum");
			
			String tr = map.get("tr");
			String td = map.get("td");

			String json = new String("{" + "pageNum:" + pageNum + ",tr:" + tr + ",td:" + td + "}");
			
			
			writer.print(json);
		} catch (Exception e) {
			e.printStackTrace(); // To change body of catch statement use File |
			writer.print("{" + "查询出错" + "}");						// Settings | File Templates.
		} finally {
			writer.close();
		}

		
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		this.doPost(request, response);
	}
}
