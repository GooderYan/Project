package com.gooderyan.project.Service;

import com.gooderyan.project.Domain.PageBean;
import com.gooderyan.project.Domain.UserBean;

import java.util.Map;

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

    /*
    * 查找对应id的用户数据
    * */
    UserBean searchUser(String userid);

    /*
    * 修改用户信息
    * */
    int updateUser(Map<String, String[]> map, String userid);

    /*
    * 删除单个或多个用户
    * */
    int deleteUsers(Map<String, String[]> parameterMap);

}
