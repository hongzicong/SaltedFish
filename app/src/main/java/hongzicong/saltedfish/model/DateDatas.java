package hongzicong.saltedfish.model;

import java.util.List;

/**
 * Created by DELL-PC on 2018/1/1.
 */

//用来存储日历表上每一天对应做的事情数量
public class DateDatas {

    private List<Integer> mLevels;

    public int getLevel(int day){
        return (int) (Math.random() * 100) % 5;
        //return mLevels.get(day-1);
    }

}
