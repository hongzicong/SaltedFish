package hongzicong.saltedfish.model;

import org.greenrobot.greendao.annotation.Entity;

import java.util.Calendar;
import java.util.Date;

import javax.security.auth.callback.Callback;

/**
 * Created by DELL-PC on 2017/12/31.
 */

public abstract class Task {

    public abstract String getName();

    public abstract boolean getIsComplete();

    public abstract Calendar getEndTime();

    public abstract boolean getIsDetailTime();

}
