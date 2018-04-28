package hongzicong.saltedfish.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by DELL-PC on 2018/1/1.
 */

//用来存储日历表的历史纪录
public class DateDatas {

    //对应每一天做了多少东西
    private List<Integer> mLevels=new ArrayList<>();

    //记录总共做了多少东西
    private int totalNum=0;

    public DateDatas(int day,List<EveryDayTask> everyDayTaskList,List<OneDayTask> oneDayTaskList){
        for(int i=0;i<day;++i){
            mLevels.add(0);
        }
        for(EveryDayTask everyDayTask:everyDayTaskList){
            Calendar calendar=Calendar.getInstance();
            calendar.setTimeInMillis(everyDayTask.getMBeginTime());
            for(int i=0;i<everyDayTask.getKeepClockList().size();++i){
                if(everyDayTask.getKeepClockList().get(i)){
                    mLevels.set(calendar.get(Calendar.DATE)-1,mLevels.get(calendar.get(Calendar.DATE)-1)+1);
                }
                calendar.add(Calendar.DATE,1);
            }
        }
        for(OneDayTask oneDayTask:oneDayTaskList){
            if(oneDayTask.getIsComplete()){
                Calendar calendar=Calendar.getInstance();
                calendar.setTimeInMillis(oneDayTask.getMEndTime());
                mLevels.set(calendar.get(Calendar.DATE)-1,mLevels.get(calendar.get(Calendar.DATE)-1)+1);
                totalNum++;
            }
        }
    }

    public int getLevel(int day){
        //return (int) (Math.random() * 100) % 5;
        return mLevels.get(day-1);
    }

    public int getTotalNum(){
        return totalNum;
    }

}
