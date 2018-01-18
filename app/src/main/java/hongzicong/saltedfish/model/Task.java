package hongzicong.saltedfish.model;

import org.greenrobot.greendao.annotation.Entity;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by DELL-PC on 2017/12/31.
 */

public abstract class Task {

    protected int id;
    protected String detail;
    protected Calendar mEndTime;
    protected boolean isComplete;
    protected String name;
    protected boolean isDetailTime=false;

    public Task(int id, String detail,Calendar endTime, boolean isComplete, String name) {
        this.id = id;
        this.detail = detail;
        mEndTime = endTime;
        this.isComplete = isComplete;
        this.name=name;
    }

}
