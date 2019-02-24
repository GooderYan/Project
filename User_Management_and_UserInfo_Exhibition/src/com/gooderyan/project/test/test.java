package com.gooderyan.project.test;

import com.gooderyan.project.Dao.DaoImpl.UserDaoImpl;
import com.gooderyan.project.Dao.UserDao;
import com.gooderyan.project.Domain.UserBean;
import com.gooderyan.project.Utils.DruidUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /*
    * 测试UserDaoImpl.getUsers()
    * */
    @Test
    public void testGetUser(){
        Map<String, String[]> map = new HashMap<>();
        map.put("name", new String[]{"李"});
        List<UserBean> users = userDao.getUsers(1, 5, map);
        System.out.println(users);
    }

    /*
    * 测试UserDaoImpl.searchUser
    * */
    @Test
    public void testSearchUser(){
        UserBean userBean = userDao.searchUser(36);
        System.out.println(userBean);
    }

    /*
    * 测试UserDaoImpl.addUser
    * */
    @Test
    public void testaddUser(){
        UserBean userBean = new UserBean();
        userBean.setName("山驴逼");
        userBean.setAge(18);
        userBean.setAddress("asdfadsfa");
        userBean.setTel("176324");
        userDao.addUser(userBean);
    }

}
