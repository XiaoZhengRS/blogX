package com.xiaozhengkeji.blog.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DaysTool {
    public static String getDays(){
        Date day=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(day);
    }
}
