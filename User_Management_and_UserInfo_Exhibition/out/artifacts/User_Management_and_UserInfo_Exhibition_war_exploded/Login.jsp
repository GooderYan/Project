<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>管理员登录</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <script type="text/javascript">
    </script>
	  <script>
		  window.onload = function (ev) {
		  	//实现验证码的点击切换
		  	var vcode = document.getElementById("vcode");
		  	vcode.onclick = function (ev1) {
		  		vcode.src = "${pageContext.servletContext.contextPath}/IdentifyCodeServlet?identifyCodeAddTime=" +
						new Date().getTime();
			}
		  }
	  </script>
  </head>
  <body>
  	<div class="container" style="width: 400px;">
  		<h3 style="text-align: center;">管理员登录</h3>
        <form action="${pageContext.servletContext.contextPath}/loginServlet" method="post">
	      <div class="form-group">
	        <label for="user">用户名：</label>
	        <input type="text" name="username" class="form-control" id="user" placeholder="请输入用户名"/>
	      </div>
	      
	      <div class="form-group">
	        <label for="password">密码：</label>
	        <input type="password" name="password" class="form-control" id="password" placeholder="请输入密码"/>
	      </div>
	      
	      <div class="form-inline">
	        <label for="vcode">验证码：</label>
	        <input type="text" name="identifyCode" class="form-control" id="identifyCode" placeholder="请输入验证码"
				   style="width: 120px;"/>
				<img src="${pageContext.request.contextPath}/IdentifyCodeServlet" title="看不清点击刷新"id="vcode"/>
	      </div>
	      <hr/>
	      <div class="form-group" style="text-align: center;">
	        <input class="btn btn btn-primary" type="submit" value="登录">
	       </div>
	  	</form>
		<%
			System.out.println(request.getAttribute("identifyCodeAddTime"));
		%>
		<!-- 出错显示的信息框 -->
			<c:if test="${not empty requestScope.Error}">
				<div class="alert alert-warning alert-dismissible" role="alert">
						<button type="button" class="close" data-dismiss="alert" >
							<span>&times;</span></button>
						<strong>${requestScope.Error}</strong>
				</div>
				<%
					request.removeAttribute("Error");
				%>
			</c:if>
  	</div>
  </body>
</html>