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

public class LoginServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		this.doPost(request, response);
	}
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)throws IOException, ServletException  {
		request.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        response. setCharacterEncoding("UTF-8");
		String username = request.getParameter("username") ;
		String password = CommonUtil.md5(request.getParameter("password")) ;
		
		User  user = UserAPI.getUserByUserEmailAndPassword(username, password) ;
		PrintWriter out = response.getWriter() ;
		HttpSession session = request.getSession() ;
		if(user==null){
			out.print("{msg:\"用户名或密码错误！\"}") ;
			return ;
		}else{
			session.setAttribute("user", user.getUserEmail()) ;
			session.setMaxInactiveInterval(60*60);
			out.print("{msg:\"success\"}");
			return ;
		}
		
	}
}
