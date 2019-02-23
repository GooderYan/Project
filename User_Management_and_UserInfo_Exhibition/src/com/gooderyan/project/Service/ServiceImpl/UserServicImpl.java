package com.gooderyan.project.Service.ServiceImpl;


import com.gooderyan.project.Dao.DaoImpl.UserDaoImpl;
import com.gooderyan.project.Dao.UserDao;
import com.gooderyan.project.Domain.PageBean;
import com.gooderyan.project.Domain.UserBean;
import com.gooderyan.project.Service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

//实现UserService接口中的方法
public class UserServicImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    /*
    * 验证输入用户名，密码
    * */
    @Override
    public int checkLogin(String username, String password) {
        return userDao.checkLogin(username, password);
    }

    /*
    * 获取本页展示的用户数据
    * */
    @Override
    public PageBean<UserBean> show(String _currentPage) {
        PageBean<UserBean> pageBean = new PageBean<>();

        //获取总的数据数
        int totalCounts = getTotalCounts();
        //获取总页码数
        int totalPages = (totalCounts % pageBean.getROWS()) == 0 ? (totalCounts / pageBean.getROWS()) : (totalCounts / pageBean.getROWS() + 1);
        int currentPage;
        if (_currentPage == null){
            currentPage = 1;
        } else {
            currentPage = Integer.valueOf(_currentPage);
        }

        //对当前页码数校验
        if (currentPage < 1){
            currentPage = 1;
        }
        if (currentPage > totalPages){
            currentPage = totalPages;
        }


        //本页数据开始索引
        int startIndex = (currentPage - 1) * pageBean.getROWS();
        //获取本页所要展示的数据的list集合
        List<UserBean> list = userDao.getUsers(startIndex, pageBean.getROWS());
        //将数据封装进pageBean
        pageBean.setTotalCount(totalCounts);
        pageBean.setTotalPages(totalPages);
        pageBean.setUsers(list);
        pageBean.setCurrentPage(currentPage);
        return pageBean;
    }

    @Override
    public int getTotalCounts() {
        return userDao.getTotalCounts();
    }


}
