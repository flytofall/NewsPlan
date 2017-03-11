package com.xprinter.newsplan.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

/**
 * Created by Charlie on 2017/3/11.
 * 将long类date转换为String类型
 */

public class DateFormatter {
    /**
     * 知乎日期转换
     * @param date
     * @return
     */
    public String ZhihuDailyDateFormat(long date){
        String sDate;
        Date  d=new Date(date+24*60*60*1000);
        SimpleDateFormat formatter=new SimpleDateFormat("yyyMMdd");
        sDate=formatter.format(d);
        return sDate;
    }

    /**
     * 豆瓣日期转换
     * @param date
     * @return
     */
    public String DoubanDateFormat(long date){

        String sDate;
        Date d=new Date(date);
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        sDate=format.format(d);
        return sDate;
    }

}
