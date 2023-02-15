package ybs.otherProblems;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @BelongsProject: Brush-a-question
 * @BelongsPackage: ybs.otherProblems
 * @Author: alicebercouli_i
 * @Date: 2022/11/8 11:44
 * @Description:
 */
public class Main {

    public static void main(String[] args) {
        Problems problems = new Problems();
        String str1 = "2345678";
        String str2 = "92356";
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        System.out.print(str1 + " * " + str2 + " = ");

        problems.covertData(s1, str1.length());
        problems.covertData(s2, str2.length());

        problems.multiply(s1,str1.length(), s2,str2.length());

    }


//    public static void main(String[] args) {
//        Date dNow = new Date();   //当前时间
//        Date dBefore = new Date();
//        Calendar calendar = Calendar.getInstance(); //得到日历
//        calendar.setTime(dNow);//把当前时间赋给日历
//        calendar.add(Calendar.MONTH, -3);  //设置为前3月
//        dBefore = calendar.getTime();   //得到前3月的时间
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //设置时间格式
//        String defaultStartDate = sdf.format(dBefore);    //格式化前3月的时间
//        String defaultEndDate = sdf.format(dNow); //格式化当前时间
//        System.out.println("三个月之前时间======="+defaultStartDate);
//        System.out.println("当前时间==========="+defaultEndDate);
//    }

}
