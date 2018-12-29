package hongzicong.saltedfish.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

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

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected())
            {
                // 当前网络是连接的
                if (info.getDetailedState() == NetworkInfo.DetailedState.CONNECTED)
                {
                    // 当前所连接的网络可用
                    return true;
                }
            }
        }
        return false;
    }

}
