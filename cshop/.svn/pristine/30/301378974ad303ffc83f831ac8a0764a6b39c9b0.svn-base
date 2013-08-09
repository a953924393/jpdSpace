package com.jingpaidang.cshop.filter;

import com.jingpaidang.cshop.domain.user.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: JackChan
 * Date: 7/23/13
 * Time: 2:31 PM
 */
public class loginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        HttpSession session = httpServletRequest.getSession();

        if(session == null) {

            String redirectUrl = httpServletRequest.getContextPath() + "/user/user!loginPage.action";

            httpServletResponse.sendRedirect(redirectUrl);

        } else {

            User user = (User) session.getAttribute("user");

            String requestURL = httpServletRequest.getRequestURI();

            if(user != null || this.validateURL(requestURL)) {

                filterChain.doFilter(servletRequest, servletResponse);

            } else {

                String redirectUrl = httpServletRequest.getContextPath() + "/user/user!loginPage.action";

                httpServletResponse.sendRedirect(redirectUrl);
            }
        }


    }

    @Override
    public void destroy() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    private boolean validateURL(String url) {


        if(url.startsWith("/user")
                || url.startsWith("/css")
                || url.startsWith("/html")
                || url.startsWith("/images")
                || url.startsWith("/img")
                || url.startsWith("/js")
                || url.startsWith("/jsp")
                || url.matches("^/.*\\.jsp$")) {

            return true;
        }
        return false;
    }
}
