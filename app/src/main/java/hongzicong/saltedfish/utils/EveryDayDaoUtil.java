package hongzicong.saltedfish.utils;

import android.content.Context;
import android.util.Log;

import com.ping.greendao.gen.EveryDayTaskDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import hongzicong.saltedfish.db.DBManager;
import hongzicong.saltedfish.model.EveryDayTask;

/**
 * Created by Dv00 on 2018/1/5.
 */

public class EveryDayDaoUtil {

    private static final String TAG = EveryDayDaoUtil.class.getSimpleName();
    private DBManager mManager;

    public EveryDayDaoUtil(Context context){
        mManager = DBManager.getInstance();
        mManager.init(context);
    }

    /**
     * 完成插入，如果表未创建，先创建表
     * @param everyDayTask
     * @return
     */
    public boolean insertEveryDayTask(EveryDayTask everyDayTask){
        boolean flag = false;
        flag = mManager.getDaoSession().getEveryDayTaskDao().insert(everyDayTask) == -1 ? false : true;
        Log.i(TAG, "insert Meizi :" + flag + "-->" + everyDayTask.toString());
        return flag;
    }

    /**
     * 插入多条数据，在子线程操作
     * @param everyDayTaskList
     * @return
     */
    public boolean insertMultEveryDayTask(final List<EveryDayTask> everyDayTaskList) {
        boolean flag = false;
        try {
            mManager.getDaoSession().runInTx(new Runnable() {
                @Override
                public void run() {
                    for (EveryDayTask everyDayTask : everyDayTaskList) {
                        mManager.getDaoSession().insertOrReplace(everyDayTask);
                    }
                }
            });
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 修改一条数据
     * @param everyDayTask
     * @return
     */
    public boolean updateEveryDayTask(EveryDayTask everyDayTask){
        boolean flag = false;
        try {
            mManager.getDaoSession().update(everyDayTask);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除单条记录
     * @param everyDayTask
     * @return
     */
    public boolean deleteEveryDayTask(EveryDayTask everyDayTask){
        boolean flag = false;
        try {
            //按照id删除
            mManager.getDaoSession().delete(everyDayTask);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除所有记录
     * @return
     */
    public boolean deleteAll(){
        boolean flag = false;
        try {
            //按照id删除
            mManager.getDaoSession().deleteAll(EveryDayTask.class);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 查询所有记录
     * @return
     */
    public List<EveryDayTask> queryAllEveryDayTask(){
        return mManager.getDaoSession().loadAll(EveryDayTask.class);
    }

    /**
     * 根据主键id查询记录
     * @param key
     * @return
     */
    public EveryDayTask queryEveryDayTaskById(long key){
        return mManager.getDaoSession().load(EveryDayTask.class, key);
    }

    /**
     * 使用native sql进行查询操作
     */
    public List<EveryDayTask> queryEveryDayByNativeSql(String sql, String[] conditions){
        return mManager.getDaoSession().queryRaw(EveryDayTask.class, sql, conditions);
    }

    /**
     * 使用queryBuilder进行查询
     * @return
     */
    public List<EveryDayTask> queryEveryDayByQueryBuilder(long id){
        QueryBuilder<EveryDayTask> queryBuilder = mManager.getDaoSession().queryBuilder(EveryDayTask.class);
        return queryBuilder.where(EveryDayTaskDao.Properties.Id.eq(id)).list();
    }

}
