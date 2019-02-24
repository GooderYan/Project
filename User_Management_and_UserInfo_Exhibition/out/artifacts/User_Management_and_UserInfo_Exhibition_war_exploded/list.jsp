<%@ page contentType="text/html; charset=UTF-8"  language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>用户信息管理系统</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>
    <script>
        window.onload = function (ev) {
            //删除选中功能：
            document.getElementById("deleteSelected").onclick = function (ev1) {
                //防止空指针异常，判断是否有被选中
                var flag = false;
                //提高用户体验，确认框确认
                if (confirm("确认删除？")){
                    //获取所有选择框
                    var usersId = document.getElementsByClassName("idBox");
                    for (var i = 0; i < usersId.length; i++) {
                        if(usersId[i].checked){
                            //只要有一个状态为checked，便将flag置为true，并结束循环
                            flag = true;
                            break;
                        }
                    }
                    //只要是true, 便提交表单
                    if(flag){
                        document.getElementById("deleteForm").submit();
                    }
                }
            }

            //删除单个用户
            document.getElementById("deleteSingle").onclick = function (ev1) {
                if (confirm("确认删除?")){
                    this.submit();
                }
            }
        }
    </script>
</head>
<body>
<div class="container">
    <h3 style="text-align: center">用户信息列表</h3>
    <div style="float: right; margin: 10px">
        <a class="btn btn-primary" href="${pageContext.servletContext.contextPath}/add.jsp">添加</a>
        <a class="btn btn-primary" href="#" id="deleteSelected">删除选中</a>
    </div>

    <%--
        分页条件查询：
        分页的关键在于导航条组件上的每一个按钮绑定添加到请求域中，从输入框获取的用户输入的查询条件
    --%>
    <form class="form-inline" action="${pageContext.servletContext.contextPath}/listServlet">
        <div class="form-group">
            <label for="exampleInputName2">姓名</label>
            <input type="text" class="form-control" id="exampleInputName2" value="${requestScope.user.name[0]}"
                   placeholder="请输入查找姓名"
                   name="name">
        </div>
        <div class="form-group">
            <label for="exampleInputEmail2">地址</label>
            <input type="text" class="form-control" id="exampleInputEmail2" value="${requestScope.user.address[0]}"
                   placeholder="请输入查找地址"
                   name="address">
        </div>
        <div class="form-group">
            <label for="exampleInputEmail2">电话</label>
            <input type="text" class="form-control" id="exampleInputEmail3" value="${requestScope.user.tel[0]}"
                   placeholder="请输入查找电话"
                   name="tel">
        </div>
        <button type="submit" class="btn btn-default">查找</button>
    </form>
    <table border="1" class="table table-bordered table-hover">
        <tr class="success">
            <th>
                <div class="checkbox">
                    <label>
                        <input type="checkbox" id="blankCheckbox" aria-label="...">
                    </label>
                </div>
            </th>
            <th>编号</th>
            <th>姓名</th>
            <th>性别</th>
            <th>年龄</th>
            <th>籍贯</th>
            <th>Tel</th>
            <th>邮箱</th>
            <th>操作</th>
        </tr>
        <%--循环遍历当前页所要展示的用户数据--%>
        <form action="${pageContext.servletContext.contextPath}/deleteUserServlet?currentPage=${requestScope.PageBean.currentPage}" method="post"
              id="deleteForm">
        <c:forEach var="i" items="${requestScope.PageBean.users}" varStatus="s">
        <tr>
            <td>
                <div class="checkbox">
                    <label>
                        <input type="checkbox" value="${requestScope.PageBean.users[s.index].id}" name="userid"
                               class="idBox">
                            <%--选择框，选中后获取该用户id--%>
                    </label>
                </div>
            </td>
            <td>${s.count}</td><%--数据索引--%>
            <td>${i.name}</td><%--用户名--%>
            <td>${i.gender}</td><%--用户性别--%>
            <td>${i.age}</td><%--用户年龄--%>
            <td>${i.address}</td><%--地址--%>
            <td>${i.tel}</td><%--电话--%>
            <td>${i.email}</td><%--email--%>
            <td><a class="btn btn-default btn-sm"
                   href="${pageContext.servletContext.contextPath}/echoServlet?userid=${requestScope.PageBean.users[s.index].id}&currentPage=${requestScope.PageBean.currentPage}">修改
            </a>&nbsp;
                <a class="btn btn-default btn-sm"
                   href="${pageContext.servletContext.contextPath}/deleteUserServlet?userid=${requestScope.PageBean.users[s.index].id}&currentPage=${requestScope.PageBean.currentPage}"
                   id="deleteSingle">删除
                </a></td>
        </tr>
        </c:forEach>
        </form>
    </table>
    <%--
        页码条组件
    --%>
    <nav aria-label="Page navigation">
        <ul class="pagination">
            <%--判断本页是否是第一页，是，则不允许再点击--%>
            <c:if test="${requestScope.PageBean.currentPage == 1}">
            <li class="disabled">
            </c:if>
            <c:if test="${requestScope.PageBean.currentPage != 1}">
                <li>
            </c:if>
                <%--点击跳转上一页--%>
                <a href="${pageContext.servletContext.contextPath}/listServlet?currentPage=${requestScope.PageBean.currentPage - 1}&
                        name=${requestScope.user.name[0]}&tel=${requestScope.user.tel[0]}&address
                        =${requestScope.user.address[0]}" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
                <%--循环遍历，总页码--%>
        <c:forEach var="i" begin="1" end="${requestScope.PageBean.totalPages}">
                <%--当前页码数高亮--%>
            <c:if test="${requestScope.PageBean.currentPage == i}">
                <li class="active">
            </c:if>
            <c:if test="${requestScope.PageBean.currentPage != i}">
                <li>
            </c:if>
                <%--显示页数--%>
                    <a
                        href="${pageContext.servletContext.contextPath}/listServlet?currentPage=${i}&
                        name=${requestScope.user.name[0]}&tel=${requestScope.user.tel[0]}&address
                        =${requestScope.user.address[0]}">${i}</a>
                </c:forEach>
            </li>
            <li>
                <%--点击跳转下一页--%>
                <a href="${pageContext.servletContext.contextPath}/listServlet?currentPage=${requestScope.PageBean.currentPage + 1}&
                        name=${requestScope.user.name[0]}&tel=${requestScope.user.tel[0]}&address
                        =${requestScope.user.address[0]}" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </ul>
    </nav>
</div>
</body>
</html>
