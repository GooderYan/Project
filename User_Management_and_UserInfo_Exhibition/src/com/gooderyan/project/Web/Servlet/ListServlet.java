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
import java.util.Map;

/*
* 用户信息列表分页展示：
* 获取当前页码数和用户输入数据，计算总页数
* */
@WebServlet("/listServlet")//默认路径
public class ListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 修改请求消息字符集, 为实现条件查询，获取请求体参数
        req.setCharacterEncoding("utf-8");
        UserService service = new UserServicImpl();
        //2. 先从session中获取当前页码
        HttpSession session = req.getSession();
        String currentPage = (String) session.getAttribute("currentPage");

        //2-1.获取用户提交参数，为实现条件分页查询。
        /*
        * 因为分页查询的当前页是从list.jsp页面中的导航条中获取，条件查询按钮第一次查询无法从请求域中获取当前页。尝试：将第一次查询当前页置为1
        * 注：分页查询的当前页码不能封装在session域中，只能封装在请求域中
        * */
        Map<String, String[]> map = req.getParameterMap();
        //3. 创建空的PageBean对象
        PageBean<UserBean> pageBean = new PageBean<>();
        //若session中没有currentPage属性，则从request域中获取
        if (currentPage != null){
            /*
            * 若不为null，则请求消息为updateServlet转发过来。
            * 获取完之后应立刻删除该属性，否则页面将会被锁定
            * */
            session.removeAttribute("currentPage");
            pageBean = service.show(map, currentPage);
        } else {
            /*
             * 若session中没有currentPage属性，则请求消息不是从updateServelt转发
             * 执行查询方法
             * */
            currentPage = req.getParameter("currentPage");
            pageBean = service.show(map, currentPage);
            req.setAttribute("user", map);
        }

        //将获取到PageBean对象返回至list页码展示
        req.setAttribute("PageBean", pageBean);
        req.getRequestDispatcher("/list.jsp").forward(req, resp);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
