<%@page language="java" contentType="text/html; charset=UTF-8"  %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
    <head>
        <!-- 指定字符集 -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>修改用户</title>

        <link href="css/bootstrap.min.css" rel="stylesheet">
        <script src="js/jquery-2.1.0.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container" style="width: 400px;">
        <h3 style="text-align: center;">修改联系人</h3>
        <form action="${pageContext.servletContext.contextPath}/updateServlet"
              method="post">
          <div class="form-group">
            <label for="name">姓名：</label>
            <input type="text" class="form-control" id="name" name="name"  readonly="readonly"
                   value="${sessionScope.user.name}" />
          </div>

          <div class="form-group">
            <label>性别：</label>
              <c:if test="${sessionScope.user.gender == '男'}">
              <input type="radio" name="gender" value="男" checked />男
                <input type="radio" name="gender" value="女"  />女
              </c:if>
              <c:if test="${sessionScope.user.gender == '女'}">
                  <input type="radio" name="gender" value="男" />男
                  <input type="radio" name="gender" value="女"  checked />女
              </c:if>
          </div>

          <div class="form-group">
            <label for="age">年龄：</label>
            <input type="text" class="form-control" id="age"  name="age" value="${sessionScope.user.age}" />
          </div>

          <div class="form-group">
            <label>籍贯：</label>
             <select name="address" class="form-control" >
                 <c:if test="${empty sessionScope.user.address}">
                     <option value="广东">广东</option>
                     <option value="广西">广西</option>
                     <option value="湖南">湖南</option>
                     <option value="北京">北京</option>
                     <option value="山东">山东</option>
                     <option value="河北">河北</option>
                     <option value="吉林">吉林</option>
                     <option value="河南">河南</option>
                     <option value="湖北">湖北</option>
                     <option value="香港">香港</option>
                     <option value="台湾">台湾</option>
                 </c:if>
                 <c:if test="${sessionScope.user.address == '广东'}">
                <option value="广东" selected>广东</option>
                <option value="广西">广西</option>
                <option value="湖南">湖南</option>
                <option value="北京">北京</option>
                <option value="山东">山东</option>
                <option value="河北">河北</option>
                <option value="吉林">吉林</option>
                <option value="河南">河南</option>
                <option value="湖北">湖北</option>
                <option value="香港">香港</option>
                <option value="台湾">台湾</option>
                 </c:if>
                 <c:if test="${sessionScope.user.address == '广西'}">
                     <option value="广东" >广东</option>
                     <option value="广西"selected>广西</option>
                     <option value="湖南">湖南</option>
                     <option value="北京">北京</option>
                     <option value="山东">山东</option>
                     <option value="河北">河北</option>
                     <option value="吉林">吉林</option>
                     <option value="河南">河南</option>
                     <option value="湖北">湖北</option>
                     <option value="香港">香港</option>
                     <option value="台湾">台湾</option>
                 </c:if>
                 <c:if test="${sessionScope.user.address == '湖南'}">
                     <option value="广东" >广东</option>
                     <option value="广西">广西</option>
                     <option value="湖南" selected>湖南</option>
                     <option value="北京">北京</option>
                     <option value="山东">山东</option>
                     <option value="河北">河北</option>
                     <option value="吉林">吉林</option>
                     <option value="河南">河南</option>
                     <option value="湖北">湖北</option>
                     <option value="香港">香港</option>
                     <option value="台湾">台湾</option>
                 </c:if>
                 <c:if test="${sessionScope.user.address == '北京'}">
                     <option value="广东" >广东</option>
                     <option value="广西">广西</option>
                     <option value="湖南">湖南</option>
                     <option value="北京" selected>北京</option>
                     <option value="山东">山东</option>
                     <option value="河北">河北</option>
                     <option value="吉林">吉林</option>
                     <option value="河南">河南</option>
                     <option value="湖北">湖北</option>
                     <option value="香港">香港</option>
                     <option value="台湾">台湾</option>
                 </c:if>
                 <c:if test="${sessionScope.user.address == '山东'}">
                     <option value="广东" >广东</option>
                     <option value="广西">广西</option>
                     <option value="湖南">湖南</option>
                     <option value="北京">北京</option>
                     <option value="山东" selected>山东</option>
                     <option value="河北">河北</option>
                     <option value="吉林">吉林</option>
                     <option value="河南">河南</option>
                     <option value="湖北">湖北</option>
                     <option value="香港">香港</option>
                     <option value="台湾">台湾</option>
                 </c:if>
                 <c:if test="${sessionScope.user.address == '河北'}">
                     <option value="广东" >广东</option>
                     <option value="广西">广西</option>
                     <option value="湖南">湖南</option>
                     <option value="北京">北京</option>
                     <option value="山东">山东</option>
                     <option value="河北" selected>河北</option>
                     <option value="吉林">吉林</option>
                     <option value="河南">河南</option>
                     <option value="湖北">湖北</option>
                     <option value="香港">香港</option>
                     <option value="台湾">台湾</option>
                 </c:if>
                 <c:if test="${sessionScope.user.address == '吉林'}">
                     <option value="广东" >广东</option>
                     <option value="广西">广西</option>
                     <option value="湖南">湖南</option>
                     <option value="北京">北京</option>
                     <option value="山东">山东</option>
                     <option value="河北">河北</option>
                     <option value="吉林" selected>吉林</option>
                     <option value="河南">河南</option>
                     <option value="湖北">湖北</option>
                     <option value="香港">香港</option>
                     <option value="台湾">台湾</option>
                 </c:if>
                 <c:if test="${sessionScope.user.address == '河南'}">
                     <option value="广东" >广东</option>
                     <option value="广西">广西</option>
                     <option value="湖南">湖南</option>
                     <option value="北京">北京</option>
                     <option value="山东">山东</option>
                     <option value="河北">河北</option>
                     <option value="吉林" >吉林</option>
                     <option value="河南" selected>河南</option>
                     <option value="湖北">湖北</option>
                     <option value="香港">香港</option>
                     <option value="台湾">台湾</option>
                 </c:if>
                 <c:if test="${sessionScope.user.address == '湖北'}">
                     <option value="广东" >广东</option>
                     <option value="广西">广西</option>
                     <option value="湖南">湖南</option>
                     <option value="北京">北京</option>
                     <option value="山东">山东</option>
                     <option value="河北">河北</option>
                     <option value="吉林" >吉林</option>
                     <option value="河南" >河南</option>
                     <option value="湖北" selected>湖北</option>
                     <option value="香港">香港</option>
                     <option value="台湾">台湾</option>
                 </c:if>
                 <c:if test="${sessionScope.user.address == '香港'}">
                     <option value="广东" >广东</option>
                     <option value="广西">广西</option>
                     <option value="湖南">湖南</option>
                     <option value="北京">北京</option>
                     <option value="山东">山东</option>
                     <option value="河北">河北</option>
                     <option value="吉林" >吉林</option>
                     <option value="河南" >河南</option>
                     <option value="湖北" >湖北</option>
                     <option value="香港" selected>香港</option>
                     <option value="台湾">台湾</option>
                 </c:if>
                 <c:if test="${sessionScope.user.address == '台湾'}">
                     <option value="广东" >广东</option>
                     <option value="广西">广西</option>
                     <option value="湖南">湖南</option>
                     <option value="北京">北京</option>
                     <option value="山东">山东</option>
                     <option value="河北">河北</option>
                     <option value="吉林" >吉林</option>
                     <option value="河南" >河南</option>
                     <option value="湖北" >湖北</option>
                     <option value="香港" >香港</option>
                     <option value="台湾" selected>台湾</option>
                 </c:if>
            </select>
          </div>

          <div class="form-group">
            <label for="tel">电话：</label>
            <input type="text" class="form-control" name="tel" id="tel" value="${sessionScope.user.tel}"/>
          </div>

          <div class="form-group">
            <label for="email">Email：</label>
            <input type="text" class="form-control" name="email" id="email" value="${sessionScope.user.email}"/>
          </div>

             <div class="form-group" style="text-align: center">
                <input class="btn btn-primary" type="submit" value="提交" />
                <input class="btn btn-default" type="reset" value="重置" />
                 <a id="back" href="${pageContext.servletContext.contextPath}/listServlet?currentPage
                 =${requestScope.PageBean.currentPage}">
                <input class="btn btn-default" type="button" value="返回"/></a>
             </div>
        </form>
        </div>
    </body>
</html>