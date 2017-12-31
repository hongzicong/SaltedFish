package hongzicong.saltedfish.model;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by DELL-PC on 2017/12/31.
 */

public class Task {

    private int id;
    private String detail;
    private Calendar mEndTime;
    private boolean isComplete;
    private String name;
    private boolean isDetailTime=false;

    public Task(int id, String detail,Calendar endTime, boolean isComplete, String name) {
        this.id = id;
        this.detail = detail;
        mEndTime = endTime;
        this.isComplete = isComplete;
        this.name=name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Calendar getEndTime() {
        return mEndTime;
    }

    public void setEndTime(Calendar endTime) {
        mEndTime = endTime;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDetailTime() {
        return isDetailTime;
    }

    public void setDetailTime(boolean detailTime) {
        isDetailTime = detailTime;
    }
}
