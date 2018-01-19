package hongzicong.saltedfish.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.List;

import hongzicong.saltedfish.R;
import hongzicong.saltedfish.model.EveryDayTask;
import hongzicong.saltedfish.model.OneDayTask;
import hongzicong.saltedfish.utils.EveryDayDaoUtil;
import hongzicong.saltedfish.utils.OneDayDaoUtil;
import hongzicong.saltedfish.utils.UIUtils;
import hongzicong.saltedfish.viewholder.TodoOnedayViewHolder;
import hongzicong.saltedfish.viewholder.TodoTextViewHolder;
import hongzicong.saltedfish.viewholder.TodoEverydayViewHolder;

/**
 * Created by DELL-PC on 2017/12/31.
 */

public class TodoListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private EveryDayDaoUtil everyDayDaoUtil=new EveryDayDaoUtil(UIUtils.getContext());
    private OneDayDaoUtil oneDayDaoUtil=new OneDayDaoUtil(UIUtils.getContext());

    protected Context mContext;
    private List<EveryDayTask> mEverydayTaskList;
    private List<OneDayTask> mOnedayTaskList;

    public TodoListAdapter(Fragment fragment,List<EveryDayTask> everydayTaskList,List<OneDayTask> onedayTaskList){
        mContext=fragment.getContext();
        this.mOnedayTaskList=onedayTaskList;
        this.mEverydayTaskList=everydayTaskList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,final int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        if(viewType==0){
            View itemView=layoutInflater.inflate(R.layout.item_text,parent,false);
            return new TodoTextViewHolder(itemView,"每日任务");
        }
        else if(viewType==mOnedayTaskList.size()+1){
            View itemView=layoutInflater.inflate(R.layout.item_text,parent,false);
            return new TodoTextViewHolder(itemView,"养成习惯");
        }
        else if(viewType>0&&viewType<=mOnedayTaskList.size()){
            View itemView=layoutInflater.inflate(R.layout.item_todo_list,parent,false);
            TodoOnedayViewHolder todoOnedayViewHolder= new TodoOnedayViewHolder(itemView,mOnedayTaskList.get(viewType-1));

            todoOnedayViewHolder.setOnDeleteListener(new TodoOnedayViewHolder.OnDeleteListener() {
                @Override
                public void deleteTask() {
                    oneDayDaoUtil.deleteOneDayTask(mOnedayTaskList.get(viewType-1));
                    mOnedayTaskList.remove(viewType-1);

                    //更新RecyclerView的列表，并显示动画
                    notifyItemRemoved(viewType-1);
                }
            });
            return todoOnedayViewHolder;
        }
        else{
            View itemView=layoutInflater.inflate(R.layout.item_todo_list,parent,false);
            TodoEverydayViewHolder todoEverydayViewHolder= new TodoEverydayViewHolder(itemView);

            //将它与模型层绑定在一起
            todoEverydayViewHolder.bind(mEverydayTaskList.get(viewType-mOnedayTaskList.size()-2));

            todoEverydayViewHolder.setOnDeleteListener(new TodoEverydayViewHolder.OnDeleteListener() {
                @Override
                public void deleteTask() {
                    everyDayDaoUtil.deleteEveryDayTask(mEverydayTaskList.get(viewType-mOnedayTaskList.size()-2));
                    mEverydayTaskList.remove(viewType-mOnedayTaskList.size()-2);

                    //更新RecyclerView的列表，并显示动画
                    notifyItemRemoved(viewType-mOnedayTaskList.size()-2);
                }
            });
            return todoEverydayViewHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mEverydayTaskList.size()+2+mOnedayTaskList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
