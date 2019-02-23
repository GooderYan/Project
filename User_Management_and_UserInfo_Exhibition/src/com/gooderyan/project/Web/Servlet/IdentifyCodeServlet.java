package com.gooderyan.project.Web.Servlet;

import com.gooderyan.project.Utils.ColorUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Random;

/*
* 生成验证码
* */
@WebServlet("/IdentifyCodeServlet")//默认路径
public class IdentifyCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Random ran  = new Random();
        int width = 80;
        int height = 40;

        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics pen = bi.getGraphics();

        pen.setColor(Color.lightGray); //设置验证码背景色
        pen.fillRect(0, 0, width, height);

        String codeKeys = "abcdefghjklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder  codeOfSession = new StringBuilder();//存储生成的验证码

        int widthOld = 0; //保存上一个验证码生成的X坐标
        /*
        * 随机获取codeKey里的关键字, 写入验证码图片，并保存至codeOfSession
        * */
        pen.setFont(new Font("宋体", Font.BOLD, 20));// 设置字体
        for (int i = 1; i <=4; i++){
            //生成验证码字符的X坐标，并判断与上个字符的距离
            int widthNew = ran.nextInt(10*i) + widthOld;
            while (widthNew < 5 || widthNew > 45){
                widthNew = ran.nextInt(10*i) + widthOld;
            }
            while ((widthNew - widthOld) < 10 && widthOld != 0){
                widthNew = ran.nextInt(10*i) + widthOld;
            }

            //生成Y坐标
            int heightNew = ran.nextInt(20) + 10 ;
            while (heightNew > 30 || heightNew < 15){
                heightNew = ran.nextInt(20) + 10 ;
            }

            String code = codeKeys.charAt(ran.nextInt(codeKeys.length())) + "";
            codeOfSession.append(code);
            code = URLEncoder.encode(code);//格式转换，防止乱码
            pen.setColor(ColorUtils.getColor(ran));

            pen.drawString(code, widthNew, heightNew);
            widthOld = widthNew;
        }

        //随机生成干扰线
        for (int i = 0; i < ran.nextInt(15) + 3; i++){
            pen.setColor(ColorUtils.getColor(ran));
            pen.drawLine(ran.nextInt(width), ran.nextInt(height), ran.nextInt(width), ran.nextInt(height));
        }
        //将图片输出到页面
        ImageIO.write(bi, "jpg", resp.getOutputStream());
        //将生成的验证码存入session
        HttpSession session = req.getSession();
        session.setAttribute("identifyCode", codeOfSession.toString());
        //将生成验证码的时间存入session
        Long time = System.currentTimeMillis();
        session.setAttribute("identifyCodeAddTime", time);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
