package com.gooderyan.project.Web.Servlet;

import com.gooderyan.project.Domain.UserBean;
import com.gooderyan.project.Service.ServiceImpl.UserServicImpl;
import com.gooderyan.project.Service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/*
* 用户数据回显
*   1. 获取请求参数中的userid
*   2. 查找对应id的数据
*   3. 封装后转发至update.jsp
* */
@WebServlet("/echoServlet")//默认路径
public class EchoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求消息中的userid
        String userid = req.getParameter("userid");
        //获取请求消息中的当前页
        String currentPage = req.getParameter("currentPage");
        //查找对应id的用户数据
        UserService service = new UserServicImpl();
        UserBean user = service.searchUser(userid);
        //将用户数据封装进session
        HttpSession session = req.getSession();
        session.setAttribute("user", user);
        session.setAttribute("userid", userid);
        session.setAttribute("currentPage", currentPage);
        //转发至update.jsp页面
        req.getRequestDispatcher("/update.jsp").forward(req, resp);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
