package hongzicong.saltedfish.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.Calendar;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Dv00 on 2018/1/4.
 */

//用来记录习惯的object
@Entity
public class EveryDayTask extends Task{

    @Id(autoincrement = true)
    private Long id;
    private String detail;
    private long mEndTime;
    private boolean isComplete;
    private String name;

    //是否需要每天定时提醒
    private boolean isDetailTime=false;

    private int combos=0;

    @Generated(hash = 1296861129)
    public EveryDayTask(Long id, String detail, long mEndTime, boolean isComplete,
            String name, boolean isDetailTime, int combos) {
        this.id = id;
        this.detail = detail;
        this.mEndTime = mEndTime;
        this.isComplete = isComplete;
        this.name = name;
        this.isDetailTime = isDetailTime;
        this.combos = combos;
    }

    @Generated(hash = 1597007745)
    public EveryDayTask() {
    }

    public boolean getIsSomeDay(){
        return false;
    }

    public Long getId() {
        return this.id;
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

    public int getCombos() {
        return this.combos;
    }

    public void setCombos(int combos) {
        this.combos = combos;
    }

    public long getMEndTime() {
        return this.mEndTime;
    }

    public void setMEndTime(long mEndTime) {
        this.mEndTime = mEndTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getEndTime(){
        Calendar calendar=Calendar.getInstance();
        calendar.setTimeInMillis(mEndTime);
        return calendar;
    }

}