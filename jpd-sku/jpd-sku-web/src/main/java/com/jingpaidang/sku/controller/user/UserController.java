package com.jingpaidang.sku.controller.user;

import com.jingpaidang.pub.operauser.api.UserAPI;
import com.jingpaidang.pub.operauser.client.User;
import com.jingpaidang.pub.operauser.util.XMLUtil;
import com.jingpaidang.sku.common.util.CommonUtil;
import com.jingpaidang.sku.controller.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: JackChan
 * Date: 8/9/13
 * Time: 3:32 PM
 */
@Controller
@RequestMapping("user")
public class UserController extends BaseController {

    @RequestMapping("loginPage")
    public String loginPage(){
        return "user/login";
    }

    @RequestMapping("registerPage")
    public String registerPage() {
        return "user/register";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public
    @ResponseBody
    void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = CommonUtil.md5(request.getParameter("password"));

        User user = UserAPI.getUserByUserEmailAndPassword(username, password);

        HttpSession session = request.getSession();
        if (user == null) {
            out.print("{msg:\"用户名或密码错误！\"}");
        } else {
            session.setAttribute("user", user.getUserEmail());
            session.setMaxInactiveInterval(60 * 60);
            out.print("{msg:\"success\"}");
        }

    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public
    @ResponseBody
    void register(HttpServletRequest request, HttpServletResponse response, User user) throws IOException {
        user.setCreateTime(XMLUtil.convertToXMLGregorianCalendar(new Date()));
        user.setModifyTime(XMLUtil.convertToXMLGregorianCalendar(new Date()));
        user.setOperator("sku");
        Integer id = UserAPI.addUser(user);
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        boolean flag = false;
        if (id != null && id.intValue() > 0) {
            session.setAttribute("user", user.getUserEmail());
            session.setMaxInactiveInterval(60 * 60);
            flag = true;
        }
        out.print("{msg:" + flag + "}");
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        response.sendRedirect("/");
    }

    @RequestMapping("isUserEmailExist")
    public@ResponseBody void isUserEmailExist(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        String userEmail = request.getParameter("userEmail");

        boolean flag = UserAPI.isUserExistByUserEmail(userEmail);
        PrintWriter out = response.getWriter();
        out.print("{msg:" + flag + "}");
    }

}
