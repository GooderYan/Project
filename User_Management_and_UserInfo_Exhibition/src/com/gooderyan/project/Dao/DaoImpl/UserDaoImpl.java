package com.gooderyan.project.Dao.DaoImpl;

import com.gooderyan.project.Utils.DruidUtils;
import org.springframework.jdbc.core.JdbcTemplate;

/*
* 实现UserDao接口方法
* */
public class UserDaoImpl {
    private static JdbcTemplate template = new JdbcTemplate(DruidUtils.getDataSource());

}
