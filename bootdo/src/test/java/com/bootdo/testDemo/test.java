package com.bootdo.testDemo;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2018/7/26.
 */
public class test {
    public static void main(String[] args) {
        String fqsj = "2018-06-13";
        String jssj = "2018-11-15";
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            //当前时间
            String nowStr = sf.format(new Date());
            Date now = sf.parse(nowStr);
            //开始时间
            Date start = sf.parse(fqsj);
            //结束时间
            Date end = sf.parse(jssj);
            if(null == start || null == end) {
                System.out.println("");
            }
            //当前时间-开始时间
            double a = (double)(now.getTime() - start.getTime())/(1000*3600*24);
            //结束时间-开始时间
            double b = (double)(end.getTime() - start.getTime())/(1000*3600*24);
            //结果
            double c = a/b;
            BigDecimal bigDecimal = new BigDecimal(c);
            double budgetSchedule = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            if (c>1) {//结果值大于1显示100%进度
                System.out.println("100%");
            } else {
                System.out.println(budgetSchedule*100 +"%");
            }
        }catch(Exception e){
            System.out.println("");
        }
    }
}
