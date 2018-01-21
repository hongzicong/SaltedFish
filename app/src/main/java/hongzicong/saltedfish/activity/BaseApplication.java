package hongzicong.saltedfish.activity;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.ping.greendao.gen.DaoMaster;
import com.ping.greendao.gen.DaoSession;

import hongzicong.saltedfish.db.DBManager;

/**
 * Created by Dv00 on 2018/1/4.
 */

public class BaseApplication extends Application {

    private static Context mContext;
    private static Thread  mMainThread;
    private static Handler mMainHandler;

    public DBManager dbManager;

    public static Thread getMainThread() {
        return mMainThread;
    }

    public static Handler getMainHandler() {
        return mMainHandler;
    }

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        mMainThread = Thread.currentThread();
        mMainHandler = new Handler();
        dbManager=DBManager.getInstance();
    }

}
