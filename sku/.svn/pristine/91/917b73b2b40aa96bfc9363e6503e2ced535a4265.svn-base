package com.jingpaidang.toolSystem.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jingpaidang.pub.operauser.api.UserAPI;
import com.jingpaidang.pub.operauser.client.User;
import com.jingpaidang.toolSystem.common.CommonUtil;

public class IsUserEmailExistServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		this.doPost(request, response);
	}
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)throws IOException, ServletException  {
		request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response. setCharacterEncoding("UTF-8");
		String userEmail = request.getParameter("userEmail") ;
		
		boolean flag = UserAPI.isUserExistByUserEmail(userEmail) ;
		PrintWriter out = response.getWriter() ;
		HttpSession session = request.getSession() ;
		out.print("{msg:"+flag+"}");
	}
}
