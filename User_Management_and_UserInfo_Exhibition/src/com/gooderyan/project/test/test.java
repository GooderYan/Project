package com.gooderyan.project.test;

import com.gooderyan.project.Dao.DaoImpl.UserDaoImpl;
import com.gooderyan.project.Dao.UserDao;
import com.gooderyan.project.Utils.DruidUtils;
import org.junit.Test;

public class test {
    private UserDao userDao = new UserDaoImpl();
    /*
    * 连接池连接测试
    * */
    @Test
    public void dsTest(){
        System.out.println(DruidUtils.getDataSource());
    }

    /*
    * UserDaoImpl登录方法测试
    * */
    @Test
    public void loginTest(){
        String username = "321";
        String password = "123";
        int i = userDao.checkLogin(username, password);
        System.out.println(i);
    }
}
