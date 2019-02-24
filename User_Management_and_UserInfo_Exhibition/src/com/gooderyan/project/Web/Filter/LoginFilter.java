package com.gooderyan.project.Web.Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/*
* 登录过滤，验证发送请求的客户端是否已经登录
*   1. 将ServletRequest转为HttpServletRequest
*   2. 获取请求访问的资源路径
*   3. 对资源路径进行判断，请求访问非需要验证的资源，直接放行
*   4. 对请求访问需要验证的资源进行验证
* */
@WebFilter("/*")//默认拦截所有
public class LoginFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        String path = request.getRequestURI();
        System.out.println(path);
        if (path.contains("Login.jsp") || path.contains("loginServlet") || path.contains("IdentifyCodeServlet") || path.contains("/js/") || path.contains("/css/") || path.contains("/fonts/")){
//            访问非需登录验证资源，直接放行
            chain.doFilter(req, resp);
        } else {
//            需要进行登录验证
//            1. 检查请求访问的session中是否有username属性
            HttpSession session = request.getSession();
            String username = (String) session.getAttribute("username");
//            2. 验证username是否为空
            if (username != null){
                //已经登录，放行
                chain.doFilter(req, resp);
            } else {
                //没有登录，设置请求错误信息转发到登录界面
                request.setAttribute("Error", "请登录后尝试");
                request.getRequestDispatcher("/Login.jsp").forward(request, resp);
            }
        }

    }

    //初始化
    public void init(FilterConfig config) throws ServletException {

    }

    //销毁
    public void destroy() {
    }
}
