package com.gooderyan.project.Web.Servlet;

import com.gooderyan.project.Domain.PageBean;
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
import java.util.List;

/*
* 用户信息列表分页展示：
* 获取当前页码数和用户输入数据，计算总页数
* */
@WebServlet("/listServlet")//默认路径
public class ListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String currentPage = (String) session.getAttribute("currentPage"); //获取当前页码
        //若session中没有currentPage属性，则从request域中获取
        if (currentPage == null){
            /*
            * 若为null，则不是updateServlet转发过来
            * */
            currentPage = req.getParameter("currentPage");
        } else {
            /*
            * 若为null，则为updateServlet转发过来。
            * 获取完之后应立刻删除该属性，否则页面将会被锁定
            * */
            session.removeAttribute("currentPage");
        }

        UserService service = new UserServicImpl();
        PageBean<UserBean> pageBean = service.show(currentPage);
        //将获取到PageBean对象返回至list页码展示
        req.setAttribute("PageBean", pageBean);
        req.getRequestDispatcher("/list.jsp").forward(req, resp);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
