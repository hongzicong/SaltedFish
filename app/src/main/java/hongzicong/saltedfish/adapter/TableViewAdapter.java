package hongzicong.saltedfish.adapter;

import hongzicong.saltedfish.model.DateDatas;

/**
 * Created by DELL-PC on 2017/12/31.
 */

public class TableViewAdapter {

    private DateDatas mDateDatas;
    private int columnCount;
    private int rowCount;
    private int currentDay;

    //日期从1开始
    public int getCurrentDay() {
        return currentDay;
    }

    //每天更新一次
    public void setCurrentDay(int currentDay) {
        this.currentDay = currentDay;
    }

    public TableViewAdapter(DateDatas dateDatas){
        this.mDateDatas=dateDatas;
    }

    public int getColumnCount(){
        return 7;
    }

    public int getRowCount(){
        return currentDay/7+1;
    }

    public int getLevel(int day){
        return mDateDatas.getLevel(day);
    }

}
