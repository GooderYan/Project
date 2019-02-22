package com.gooderyan.project.Domain;

import java.util.List;

//对分页查询提供Bean对象支持，封装页面与数据库中的相关数据
public class PageBean<T> {
    private int currentPage; //当前页面
    private final int ROWS = 5; //每页显示记录数
    private int totalPages; //总页码数
    private List<T> users; //存储UserBean对象的集合
    private int totalCount; //数据库中的总记录数

    public PageBean() {
    }

    public PageBean(int currentPage, int totalPages, List<T> users, int totalCount) {
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.users = users;
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "currentPage=" + currentPage +
                ", ROWS=" + ROWS +
                ", totalPages=" + totalPages +
                ", users=" + users +
                ", totalCount=" + totalCount +
                '}';
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getROWS() {
        return ROWS;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<T> getUsers() {
        return users;
    }

    public void setUsers(List<T> users) {
        this.users = users;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
