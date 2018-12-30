package hongzicong.saltedfish.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import hongzicong.saltedfish.utils.EveryDayDaoUtil;
import hongzicong.saltedfish.utils.OneDayDaoUtil;
import hongzicong.saltedfish.utils.UIUtils;
import hongzicong.saltedfish.utils.Util;

/**
 * Created by DELL-PC on 2018/1/1.
 */

//用来存储日历表的历史纪录
public class DateDatas {

    //对应每一天做了多少东西
    private List<Integer> mLevels = new ArrayList<>();

    private List<EveryDayTask> everyDayTaskList;

    private List<OneDayTask> oneDayTaskList;

    private EveryDayDaoUtil everyDayDaoUtil = new EveryDayDaoUtil(UIUtils.getContext());

    private OneDayDaoUtil oneDayDaoUtil = new OneDayDaoUtil(UIUtils.getContext());

    //记录总共做了多少东西
    private int totalNum=0;

    public DateDatas(){
        updateData();
    }

    public void updateData(){
        this.everyDayTaskList = everyDayDaoUtil.queryAllEveryDayTask();
        this.oneDayTaskList = oneDayDaoUtil.queryAllOneDayTask();

        Calendar calendar=Calendar.getInstance();
        for(int i = 0; i < Util.getTotalDayNum(); ++i){
            mLevels.add(0);
        }
        for(EveryDayTask everyDayTask:everyDayTaskList){
            calendar.setTimeInMillis(everyDayTask.getMBeginTime());
            for(int i=0;i<everyDayTask.getKeepClockList().size();++i){
                if(everyDayTask.getKeepClockList().get(i)){
                    mLevels.set(calendar.get(Calendar.DAY_OF_YEAR)-1,mLevels.get(calendar.get(Calendar.DAY_OF_YEAR)-1)+1);
                    totalNum++;
                }
                calendar.add(Calendar.DAY_OF_YEAR,1);
            }
        }
        for(OneDayTask oneDayTask:oneDayTaskList){
            if(oneDayTask.getIsComplete()){
                calendar.setTimeInMillis(oneDayTask.getMEndTime());
                mLevels.set(calendar.get(Calendar.DAY_OF_YEAR) - 1, mLevels.get(calendar.get(Calendar.DAY_OF_YEAR)-1) + 1);
                totalNum++;
            }
        }
    }

    public int getLevel(int day){
        //return (int) (Math.random() * 100) % 5;
        return mLevels.get(day - 1);
    }

    public int getTotalNum(){
        return totalNum;
    }

}
