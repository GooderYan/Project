package com.gooderyan.project.Web.Servlet;

import com.gooderyan.project.Service.ServiceImpl.UserServicImpl;
import com.gooderyan.project.Service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

/*
* 单个/多个用户删除
*   1. 获取请求体中用户id
*   2. 传递至Service执行删除方法
* */
@WebServlet("/deleteUserServlet")//默认路径
public class DeleteUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //请求消息中的userid的Map集合
        Map<String, String[]> parameterMap = req.getParameterMap();
        Set<String> keys = parameterMap.keySet();
        for (String key : keys) {
            if (key.equals("userid")){
                String[] userid = parameterMap.get(key);
            }
        }

        UserService service = new UserServicImpl();
        int count = service.deleteUsers(parameterMap);

        if (count == 1){
            req.getSession().setAttribute("currentPage", req.getParameter("currentPage"));
            req.getRequestDispatcher("/listServlet").forward(req, resp);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
