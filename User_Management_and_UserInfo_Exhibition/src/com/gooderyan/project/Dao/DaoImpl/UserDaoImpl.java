package com.gooderyan.project.Dao.DaoImpl;

import com.gooderyan.project.Dao.UserDao;
import com.gooderyan.project.Domain.UserBean;
import com.gooderyan.project.Utils.DruidUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    public List<UserBean> getUsers(int startIndex, int rows, Map<String, String[]> map) {
        //1. 生成sql语句模板
        StringBuilder sql = new StringBuilder("select id, NAME, gender, age, address, tel, email from user where 1 = " +
            "1 ");
        //2. 生成List集合，存储输入数据
        //泛型使用Object, 因为limit分页语句的条件值要求为int, 但其他的条件值都为String。
        List<Object> list = new ArrayList<>();
        //3. 遍历Map获取请求消息参数
        Set<String> keys = map.keySet();
        for (String key : keys) {
            //排除掉当前页，获取其他输入数据
            if (!key.equals("currentPage") && map.get(key)[0].length() > 0){
                sql.append(" and " + key + " like ?");
                list.add("%" + map.get(key)[0] + "%");
            }
        }
        //3. 完成sql拼接
        sql.append(" limit ?, ?");
        list.add(startIndex);
        list.add(rows);
        //4. 执行DQL语句，返回查询结果
        //注意：为查询语句传参时，需要将集合转换为数组
        return template.query(sql.toString(), new BeanPropertyRowMapper<>(UserBean.class), list.toArray());
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

    /*
    * 删除单个或多个用户
    * */
    @Override
    public int deleteUsers(String[] userid) {
        StringBuilder sql = new StringBuilder("delete from user where id in (");
        for (String s : userid) {
            sql.append(s + ",  ");
        }
        //删除最后一个逗号，并补全右括号
        sql.delete(sql.length() - 3, sql.length());
        sql.append(")");

        return template.update(sql.toString());
    }

    /*
    * 添加用户
    * */
    @Override
    public int addUser(UserBean user) {
        //手动添加null值，不能作为字符串添加
        String sql = "insert into user values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return template.update(sql, user.getId(), user.getName(), user.getGender(), user.getAge(), user.getAddress(),
            user.getTel(), user.getEmail(), user.getUsername(), user.getPassword());
    }
}
