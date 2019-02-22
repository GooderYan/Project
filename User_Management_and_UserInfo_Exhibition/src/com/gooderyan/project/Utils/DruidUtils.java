package com.gooderyan.project.Utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/*
* Druid工具类，提供DataSource对象
* */
public class DruidUtils {
    private static DataSource ds;

    /*
    * 加载配置文件，初始化ds
    * */
    static{
        try {
        Properties pro = new Properties();
        pro.load(DruidUtils.class.getClassLoader().getResourceAsStream("Druid.properties"));
        ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    * 对外提供DataSource对象
    * */
    public static DataSource getDataSource(){
        return ds;
    }
}
