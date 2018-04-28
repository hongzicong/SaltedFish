package hongzicong.saltedfish.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.NotNull;

/**
 * Created by Dv00 on 2018/1/4.
 */

//用来记录每日任务详情的object
@Entity
public class OneDayTask extends Task{

    @Id(autoincrement = true)
    private Long id;
    private String detail;
    private long mBeginTime;
    private long mEndTime;
    private boolean isComplete;

    @NotNull
    private String name;

    private boolean isDetailTime=false;

    @Generated(hash = 875728040)
    public OneDayTask(Long id, String detail, long mBeginTime, long mEndTime,
            boolean isComplete, @NotNull String name, boolean isDetailTime) {
        this.id = id;
        this.detail = detail;
        this.mBeginTime = mBeginTime;
        this.mEndTime = mEndTime;
        this.isComplete = isComplete;
        this.name = name;
        this.isDetailTime = isDetailTime;
    }

    @Generated(hash = 1382090838)
    public OneDayTask() {
    }

    public Calendar getEndTime(){
        Calendar calendar=Calendar.getInstance();
        calendar.setTimeInMillis(mEndTime);
        return calendar;
    }

    public Long getId() {
        return this.id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getDetail() {
        return this.detail;
    }


    public void setDetail(String detail) {
        this.detail = detail;
    }


    public boolean getIsComplete() {
        return this.isComplete;
    }


    public void setIsComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }


    public String getName() {
        return this.name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public boolean getIsDetailTime() {
        return this.isDetailTime;
    }


    public void setIsDetailTime(boolean isDetailTime) {
        this.isDetailTime = isDetailTime;
    }

    public long getMEndTime() {
        return this.mEndTime;
    }

    public void setMEndTime(long mEndTime) {
        this.mEndTime = mEndTime;
    }

    public long getMBeginTime() {
        return this.mBeginTime;
    }

    public void setMBeginTime(long mBeginTime) {
        this.mBeginTime = mBeginTime;
    }

}