package hongzicong.saltedfish.utils;

import android.content.Context;
import android.util.Log;

import com.ping.greendao.gen.EveryDayTaskDao;
import com.ping.greendao.gen.OneDayTaskDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import hongzicong.saltedfish.db.DBManager;
import hongzicong.saltedfish.model.EveryDayTask;
import hongzicong.saltedfish.model.OneDayTask;

public class OneDayDaoUtil {

    private static final String TAG = OneDayDaoUtil.class.getSimpleName();
    private DBManager mManager;

    public OneDayDaoUtil(Context context){
        mManager = DBManager.getInstance();
        mManager.init(context);
    }

    /**
     * 完成插入，如果表未创建，先创建表
     * @param oneDayTask
     * @return
     */
    public boolean insertOneDayTask(OneDayTask oneDayTask){
        boolean flag = false;
        flag = mManager.getDaoSession().getOneDayTaskDao().insert(oneDayTask) == -1 ? false : true;
        Log.i(TAG, "insert OneDayTask :" + flag + "-->" + oneDayTask.toString());
        return flag;
    }

    /**
     * 插入多条数据，在子线程操作
     * @param oneDayTaskList
     * @return
     */
    public boolean insertMultOneDayTask(final List<OneDayTask> oneDayTaskList) {
        boolean flag = false;
        try {
            mManager.getDaoSession().runInTx(new Runnable() {
                @Override
                public void run() {
                    for (OneDayTask oneDayTask : oneDayTaskList) {
                        mManager.getDaoSession().insertOrReplace(oneDayTask);
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
     * @param oneDayTask
     * @return
     */
    public boolean updateOneDayTask(OneDayTask oneDayTask){
        boolean flag = false;
        try {
            mManager.getDaoSession().update(oneDayTask);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除单条记录
     * @param oneDayTask
     * @return
     */
    public boolean deleteOneDayTask(OneDayTask oneDayTask){
        boolean flag = false;
        try {
            //按照id删除
            mManager.getDaoSession().delete(oneDayTask);
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
            mManager.getDaoSession().deleteAll(OneDayTask.class);
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
    public List<OneDayTask> queryAllOneDayTask(){
        return mManager.getDaoSession().loadAll(OneDayTask.class);
    }

    /**
     * 根据主键id查询记录
     * @param key
     * @return
     */
    public OneDayTask queryOneDayTaskById(long key){
        return mManager.getDaoSession().load(OneDayTask.class, key);
    }

    /**
     * 使用native sql进行查询操作
     */
    public List<OneDayTask> queryOneDayByNativeSql(String sql, String[] conditions){
        return mManager.getDaoSession().queryRaw(OneDayTask.class, sql, conditions);
    }

    /**
     * 使用queryBuilder进行查询
     * @return
     */
    public List<OneDayTask> queryOneDayTaskByQueryBuilder(long id){
        QueryBuilder<OneDayTask> queryBuilder = mManager.getDaoSession().queryBuilder(OneDayTask.class);
        return queryBuilder.where(OneDayTaskDao.Properties.Id.eq(id)).list();
    }

}
