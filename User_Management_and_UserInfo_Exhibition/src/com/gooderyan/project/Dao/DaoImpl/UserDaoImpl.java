package com.gooderyan.project.Dao.DaoImpl;

import com.gooderyan.project.Dao.UserDao;
import com.gooderyan.project.Domain.UserBean;
import com.gooderyan.project.Utils.DruidUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

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

    /*
    * 获取数据库总数据数
    * */
    @Override
    public int getTotalCounts() {
        String sql = "select count(id) from user";
        return template.queryForObject(sql, Integer.class);
    }

    /*
    * 获取本页所要展示的数据
    * */
    @Override
    public List<UserBean> getUsers(int startIndex, int rows) {
        String sql = "select * from user limit ?, ?";
        return template.query(sql, new BeanPropertyRowMapper<>(UserBean.class), startIndex, rows);
    }

    /*
    * 查找数据库内对应id的用户数据
    * */
    @Override
    public UserBean searchUser(int id) {
        String sql = "select id, NAME, gender, age, address, tel, email from user where 1 = 1 and id = ?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<>(UserBean.class), id);
    }

    /*
    * 修改用户信息
    * */
    @Override
    public int updateUser(UserBean user, String userid) {
        String sql = "update user set gender = ?, age = ?, address = ?, tel = ?, email = ? where id = ?";
        int count = template.update(sql, user.getGender(), user.getAge(), user.getAddress(), user.getTel(),
                user.getEmail(), Integer.valueOf(userid));

        return count;
    }

}
