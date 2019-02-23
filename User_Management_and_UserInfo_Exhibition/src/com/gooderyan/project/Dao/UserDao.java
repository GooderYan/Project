package com.gooderyan.project.Dao;

/*
* 提供对数据库执行DML, DQL的方法规范
* */
public interface UserDao {
    /*
    * 连接MySQL验证用户名和密码
    * */
    int checkLogin(String username, String password);
}
