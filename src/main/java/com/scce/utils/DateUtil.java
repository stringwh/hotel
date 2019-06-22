package com.scce.utils;

/**
 * @program: IdeaProjects
 * @description:
 * @author: Lxy
 * @create: 2019-06-18 14:12
 **/

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author kevin
 * java8 计算两个日期之间的天数
 */
public class DateUtil {

    /**
     * 通过时间秒毫秒数判断两个时间的间隔
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDaysByMillisecond(Date date1,Date date2)
    {
        int day=0;
        System.out.println(date1+"@@@@"+date2);
        System.out.println(date2.getTime()+"@@@@"+date1.getTime());
        double days =(double) (date2.getTime() - date1.getTime())/(1000*3600*24);
        System.out.println(days+"*********");
       if (days%1!=0){
        day =(int)days+1;
        }else {
           day= (int) days;
       }

        return day;
    }

    public static Date StringToDate(String str){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date  startDate= null;
        try {
            startDate = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return startDate;
    }

}
