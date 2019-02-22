package com.gooderyan.project.test;

import com.gooderyan.project.Utils.DruidUtils;
import org.junit.Test;

public class test {
    /*
    * 连接池连接测试
    * */
    @Test
    public void dsTest(){
        System.out.println(DruidUtils.getDataSource());
    }
}
