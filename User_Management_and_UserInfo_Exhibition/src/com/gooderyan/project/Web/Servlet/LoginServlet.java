package com.gooderyan.project.Web.Servlet;

import com.gooderyan.project.Service.ServiceImpl.UserServicImpl;
import com.gooderyan.project.Service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Set;

/*
 * 登录逻辑的实现
 * 需：
 *   1. 获取请求中验证码的提交时间，若超时直接返回
 *   2. 获取验证码与session中的验证码进行校验，若错误，直接范湖
 *   3. 获取提交用户名和密码，若错误，直接返回
 *   4. 以上步骤都通过，跳转到index.jsp页面
 * */
@WebServlet("/loginServlet")//默认路径
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long time = System.currentTimeMillis(); //生成获取请求的时间
        long addTime = (long) req.getSession().getAttribute("identifyCodeAddTime");
        //校验验证码是否超时
        if ((addTime - time) > 1000) {
            req.setAttribute("Error", "验证码已超时");
            req.getRequestDispatcher("/Login.jsp").forward(req, resp);
            return;
        }
        //获取session中存储的验证码
        String code = (String) req.getSession().getAttribute("identifyCode");
        //获取用户输入验证码
        String identifyCode = (String) req.getParameter("identifyCode");
        if (identifyCode.equals("")) {
            req.setAttribute("Error", "请输入验证码");
            req.getRequestDispatcher("/Login.jsp").forward(req, resp);
            return;
        }
        //获取用户提交数据
        String username = (String) req.getParameter("username");
        String password = (String) req.getParameter("password");
        //校验输入验证码
        if (code.equalsIgnoreCase(identifyCode)) {
            //成功Service层，验证用户名和密码
            UserService userService = new UserServicImpl();
            int flag = userService.checkLogin(username, password);
            if (flag == 1) {
                //登录成功，保存用户信息后，跳转到WELCOME.jsp页面
                req.getSession().setAttribute("username", username);
                String lastDate = new SimpleDateFormat("yyyy年MM月dd日").format(new Date());
                req.getSession().setAttribute("lastDate", lastDate);
                resp.sendRedirect(req.getContextPath() + "/WELCOME.jsp");
                return;
            } else {
                req.setAttribute("Error", "用户名密码错误，请重新输入");
            }
        } else {
            //失败
            req.setAttribute("Error", "验证码错误，请重新输入");
        }

    //登录失败
        req.getRequestDispatcher("/Login.jsp").

    forward(req, resp);

}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
