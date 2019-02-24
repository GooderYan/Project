package com.gooderyan.project.Dao;

import com.gooderyan.project.Domain.UserBean;

import java.util.List;

/*
* 提供对数据库执行DML, DQL的方法规范
* */
public interface UserDao {
    /*
    * 连接MySQL验证用户名和密码
    * */
    int checkLogin(String username, String password);

    /*
     * 获取数据库的总数据数
     * */
    int getTotalCounts();

    /*
    * 获取本页所要展示的数据
    * */
    List<UserBean> getUsers(int startIndex, int rows);

    /*
    * 查找数据库内对应id的用户数据
    * */
    UserBean searchUser(int id);

    /*
    * 修改用户信息
    * */
    int updateUser(UserBean user, String userid);

    /*
    * 删除单个或多个用户
    * */
    int deleteUsers(String[] userid);

    /*
    * 添加用户
    * */
    int addUser(UserBean user);
}
