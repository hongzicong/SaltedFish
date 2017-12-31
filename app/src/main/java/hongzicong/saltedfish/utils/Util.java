package hongzicong.saltedfish.utils;

/**
 * Created by DELL-PC on 2018/1/1.
 */

public class Util {

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

}
