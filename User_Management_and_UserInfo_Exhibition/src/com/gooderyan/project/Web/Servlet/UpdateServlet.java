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

/*
* 用户修改
*   1. 获取提交修改信息
*   2. 传递给Service进行修改
*   3. 反馈修改信息
* */
@WebServlet("/updateServlet")//默认路径
public class UpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8"); //设置请求消息编码格式
        HttpSession session = req.getSession();
        //获取session中存储的当前页和用户id
        String currentPage = (String) session.getAttribute("currentPage");
        String userid = (String) session.getAttribute("userid");
        Map<String, String[]> map = req.getParameterMap(); //获取用户提交的修改信息
        UserService service = new UserServicImpl();
        int count = service.updateUser(map, userid);
        //判断返回值，反馈执行结果
        if (count == 1){
            //修改成功, 返回列表界面，展示修改后的数据
            //listServlet中无法获取到currentPage参数，尝试使用session
//            req.setAttribute("currentPage", currentPage);
            session.setAttribute("currentPage", currentPage);
            req.getRequestDispatcher("/listServlet").forward(req, resp);
        } else {
            //修改失败，转发至echoServlet，设置错误信息
            req.setAttribute("Error", "用户数据修改失败");
            req.setAttribute("userid", userid);
            req.getRequestDispatcher("/echoServlet").forward(req, resp);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
