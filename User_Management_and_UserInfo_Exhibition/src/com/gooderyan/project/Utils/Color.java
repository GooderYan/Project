package com.gooderyan.project.Utils;

import java.awt.*;
import java.util.Random;

/*
* 生成验证码中的随机颜色获取
* */
public class ColorUtils {
    private static Color color;

    public static Color getColor(Random ran){
       return color = new Color(ran.nextInt(255), ran.nextInt(255), ran.nextInt(255));
    }
}
