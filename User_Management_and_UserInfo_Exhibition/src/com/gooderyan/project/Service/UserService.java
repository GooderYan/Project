package com.gooderyan.project.Service;

import com.gooderyan.project.Domain.PageBean;
import com.gooderyan.project.Domain.UserBean;

import java.util.List;

//规定UserService中的方法规范
public interface UserService {
    /*
    * 验证用户名，密码
    * */
    int checkLogin(String username, String passoword);

    //获取本业所展示的用户数据
    PageBean<UserBean> show(String currentPage);

    //获取数据库的总页数
    int getTotalCounts();
}
