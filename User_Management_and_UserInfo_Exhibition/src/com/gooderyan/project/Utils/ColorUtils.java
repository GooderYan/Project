package com.gooderyan.project.Utils;

import java.awt.*;
import java.util.Random;

public class ColorUtils {
    private static Color color;

    public static Color getColor(Random ran){
        return color = new Color(ran.nextInt(255), ran.nextInt(255), ran.nextInt(255));
    }
}
