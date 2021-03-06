package com.gooderyan.project.Service.ServiceImpl;


import com.gooderyan.project.Dao.DaoImpl.UserDaoImpl;
import com.gooderyan.project.Dao.UserDao;
import com.gooderyan.project.Domain.PageBean;
import com.gooderyan.project.Domain.UserBean;
import com.gooderyan.project.Service.UserService;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

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

    /*
    * 获取本页展示的用户数据
    * 实现分页查询
    * */
    @Override
    public PageBean<UserBean> show(Map<String, String[]> map, String _currentPage) {
        PageBean<UserBean> pageBean = new PageBean<>();

        //获取总的数据数
        int totalCounts = getTotalCounts();
        //获取总页码数
        int totalPages = getTotalPages(pageBean);
        int currentPage;
        if (_currentPage == null){
            currentPage = 1;
        } else {
            currentPage = Integer.valueOf(_currentPage);
        }

        //对当前页码数校验
        if (currentPage < 1){
            currentPage = 1;
        }
        if (currentPage > totalPages){
            currentPage = totalPages;
        }

        //本页数据开始索引
        int startIndex = (currentPage - 1) * pageBean.getROWS();
        //获取本页所要展示的数据的list集合
        List<UserBean> list = userDao.getUsers(startIndex, pageBean.getROWS(), map);
        //将数据封装进pageBean
        pageBean.setTotalCount(totalCounts);
        pageBean.setTotalPages(totalPages);
        pageBean.setUsers(list);
        pageBean.setCurrentPage(currentPage);
        return pageBean;
    }

    /*
    * 获取数据数据总页数
    * */
    public int getTotalPages(PageBean<UserBean> pageBean ) {
        int totalCounts = getTotalCounts();
        return (totalCounts % pageBean.getROWS()) == 0 ? (totalCounts / pageBean.getROWS()) : (totalCounts / pageBean.getROWS() + 1);
    }

    /*
    * show方法重载，用于条件查询
    * */
//    @Override
//    public PageBean<UserBean> show(Map<String, String[]> map) {
//        //1. 单独获取当前页
//        String _currentPage = map.get("currentPage")[0];
//        int currentPage;
//        if (_currentPage == null){
//            currentPage = 1;
//        } else {
//            currentPage = Integer.valueOf(_currentPage);
//        }
//        PageBean<UserBean> pageBean = new PageBean<>();
//        //2. 获取总页数和总数据数
//        int totalCounts = getTotalCounts();
//        int totalPages = getTotalPages(pageBean);
//        //3. 生成开始索引
//        int index = (currentPage - 1) * pageBean.getROWS();
//        //4. 将开始索引数，map集合，rows作为参数调用UserDao查询方法, 返回存储用户数据的List<>集合
//        List<UserBean> list = userDao.searchUser(index, pageBean.getROWS(), map);
//        //5. 将获得的参数封装进PageBean对象中
//        pageBean.setCurrentPage(currentPage);
//        pageBean.setUsers(list);
//        pageBean.setTotalPages(totalPages);
//        pageBean.setTotalCount(totalCounts);
//
//        return pageBean;
//    }

    /*
    * 获取服务器内的数据总数
    * */
    @Override
    public int getTotalCounts() {
        return userDao.getTotalCounts();
    }

    /*
    * 查找对应id的用户数据
    * */
    @Override
    public UserBean searchUser(String userid) {
        if (userid != null){
            int id = Integer.valueOf(userid);
            //调用UserDao中方法进行查询
            return userDao.searchUser(id);
        } else {
            return null;
        }
    }

    /*
    * 修改用户信息
    * */
    @Override
    public int updateUser(Map<String, String[]> map, String userid) {
        UserBean user = new UserBean();
        try {
            //将用户信息封装进UserBean对象
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //将user对象传递给UserDao执行DML语句
        return userDao.updateUser(user, userid);
    }

    /*
    * 删除单个或多个用户
    * */
    @Override
    public int deleteUsers(Map<String, String[]> parameterMap) {
        String[] userid = parameterMap.get("userid");
        int count = userDao.deleteUsers(userid);
        if (count == userid.length){
            return 1;
        } else {
            return 0;
        }
    }

    /*
    * 添加用户
    * */
    @Override
    public int addUser(UserBean user) {
        return userDao.addUser(user);
    }


}
