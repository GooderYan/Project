package com.gooderyan.project.Service;

//规定UserService中的方法规范
public interface UserService {
    /*
    * 验证用户名，密码
    * */
    int checkLogin(String username, String passoword);
}
