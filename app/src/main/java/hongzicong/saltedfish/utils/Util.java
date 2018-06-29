package hongzicong.saltedfish.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by DELL-PC on 2018/1/1.
 */

public class Util {

    public static String getToday(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }

    public static int getTotalDayNum() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("D");
        Date date = new Date(System.currentTimeMillis());
        return Integer.parseInt(simpleDateFormat.format(date));
    }

    public static int getDay(int week,int day){
        int result=0;
        for(int i=0;i<week;++i){
            for(int j=0;j<7;++j){
                ++result;
            }
        }
        for(int i=0;i<=day;++i){
            ++result;
        }
        return result;
    }

    public static boolean isValidName(String nameStr){
        if(!TextUtils.isEmpty(nameStr)&&isStudentEmail(nameStr)){
            return true;
        }
        return false;
    }

    public static boolean isStudentEmail(String email){
        //todo 学正则表达式
        return true;
    }

    public static boolean isValidPassword(String password){
        //todo
        return true;
    }

}
