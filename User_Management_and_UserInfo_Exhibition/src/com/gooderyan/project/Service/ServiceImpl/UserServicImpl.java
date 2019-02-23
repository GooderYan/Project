package com.gooderyan.project.Service.ServiceImpl;


import com.gooderyan.project.Dao.DaoImpl.UserDaoImpl;
import com.gooderyan.project.Dao.UserDao;
import com.gooderyan.project.Service.UserService;

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
}
