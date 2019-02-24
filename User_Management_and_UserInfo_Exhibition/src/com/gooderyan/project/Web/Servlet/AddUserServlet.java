package com.gooderyan.project.Web.Servlet;

import com.gooderyan.project.Domain.PageBean;
import com.gooderyan.project.Domain.UserBean;
import com.gooderyan.project.Service.ServiceImpl.UserServicImpl;
import com.gooderyan.project.Service.UserService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/*
* 添加用户
*  1.修改请求消息字符集
*  2. 获取提交参数的map集合
*  3. 使用BeanUtils将map集合封装进userBean对象
*  4. 调用Service.addUser()方法
*  5. 获取返回值
*  6. 请求转发至listServlet
* */
@WebServlet("/addUserServlet")//默认路径
public class AddUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        Map<String, String[]> map = req.getParameterMap();
        UserBean user = new UserBean();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        UserService service = new UserServicImpl();
        service.addUser(user);
        HttpSession session = req.getSession();
        session.setAttribute("currentPage", service.getTotalPages(new PageBean<UserBean>())+"");
        req.getRequestDispatcher("/listServlet").forward(req, resp);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
