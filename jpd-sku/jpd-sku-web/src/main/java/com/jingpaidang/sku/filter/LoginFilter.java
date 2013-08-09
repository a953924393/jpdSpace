package com.jingpaidang.sku.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {
	
	public void init(FilterConfig filterConfig) throws ServletException {
		
	 }

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		HttpSession session = servletRequest.getSession();

		// 获得用户请求的URI
		String path = servletRequest.getRequestURI();
		// 从session里用户信息
		String userEmail = (String) session.getAttribute("user");
		// 登陆页面无需过滤
		if (path.indexOf("/css/")>-1 || path.indexOf("/colorbox/")>-1||path.indexOf("/images/")>-1||
				path.indexOf("/js/")>-1 || path.indexOf("/user")>-1) {
			chain.doFilter(servletRequest, servletResponse);
			return;
		}


        // 判断如果没有取到用户信息,就跳转到登陆页面
        if (userEmail == null || "".equals(userEmail)) {
            // 跳转到登陆页面
            servletResponse.sendRedirect("/user/loginPage");
        } else {
            // 已经登陆,继续此次请求
            if(path.equals("/sku/addSkuPage") || path.equals("/sku/addSku")) {
                if("everyone@jingpaidang.com".equals(userEmail)) {
                    chain.doFilter(request, response);

                    return ;
                }  else {
                    servletResponse.sendRedirect("/user/loginPage");
                }

            }  else {
                chain.doFilter(request, response);

            }
		}
	}
}
