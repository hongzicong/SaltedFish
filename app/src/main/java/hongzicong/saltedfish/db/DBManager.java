package hongzicong.saltedfish.db;

import android.content.Context;

import com.ping.greendao.gen.DaoMaster;
import com.ping.greendao.gen.DaoSession;

import org.greenrobot.greendao.query.QueryBuilder;

/**
 * Created by Dv00 on 2018/1/5.
 */

public class DBManager {

    private static final String TAG = DBManager.class.getSimpleName();
    private static final String DB_NAME = "saltedFishDB";

    private Context context;

    //多线程中要被共享的使用volatile关键字修饰
    private volatile static DBManager manager = new DBManager();
    private static DaoMaster sDaoMaster;
    private static DaoMaster.DevOpenHelper sHelper;
    private static DaoSession sDaoSession;

    /**
     * 单例模式获得操作数据库对象
     * @return
     */
    public static DBManager getInstance(){
        return manager;
    }

    public void init(Context context){
        this.context = context;
    }

    /**
     * 判断是否有存在数据库，如果没有则创建
     * 应用了单例模式
     * @return
     */
    public DaoMaster getDaoMaster(){
        if(sDaoMaster == null) {
            //创建新的数据库
            DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
            sDaoMaster = new DaoMaster(helper.getWritableDatabase());
        }
        return sDaoMaster;
    }

    /**
     * 完成对数据库的添加、删除、修改、查询操作，仅仅是一个接口
     * @return
     */
    public DaoSession getDaoSession(){
        if(sDaoSession == null){
            if(sDaoMaster == null){
                sDaoMaster = getDaoMaster();
            }
            sDaoSession = sDaoMaster.newSession();
        }
        return sDaoSession;
    }

    /**
     * 关闭所有的操作，数据库开启后，使用完毕要关闭
     */
    public void closeConnection(){
        closeHelper();
        closeDaoSession();
    }

    public void closeHelper(){
        if(sHelper != null){
            sHelper.close();
            sHelper = null;
        }
    }

    public void closeDaoSession(){
        if(sDaoSession != null){
            sDaoSession.clear();
            sDaoSession = null;
        }
    }

}
