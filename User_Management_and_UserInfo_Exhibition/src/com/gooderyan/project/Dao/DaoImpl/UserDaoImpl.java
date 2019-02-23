package com.gooderyan.project.Dao.DaoImpl;

import com.gooderyan.project.Dao.UserDao;
import com.gooderyan.project.Utils.DruidUtils;
import org.springframework.jdbc.core.JdbcTemplate;

/*
* 实现UserDao接口方法
* */
public class UserDaoImpl implements UserDao {
    private static JdbcTemplate template = new JdbcTemplate(DruidUtils.getDataSource());

    /*
    * 连接MySQL验证用户名和密码
    * */
    @Override
    public int checkLogin(String username, String password) {
        //生成sql语句
        String sql = "select count(id) from user where username = ? and password = ?";
        /*
        * 执行DQL查询语句
        * 1：成功
        * 0：失败
        * */
        return template.queryForObject(sql, Integer.class, username, password);
    }
}
