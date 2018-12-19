package hongzicong.saltedfish.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.Collections;
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

public class TodoListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ItemTouchHelperClass.ItemTouchHelperAdapter{

    private EveryDayDaoUtil everyDayDaoUtil=new EveryDayDaoUtil(UIUtils.getContext());
    private OneDayDaoUtil oneDayDaoUtil=new OneDayDaoUtil(UIUtils.getContext());

    protected Context mContext;
    private List<EveryDayTask> mEverydayTaskList;
    private List<OneDayTask> mOnedayTaskList;

    private final int VIEW_TYPE_MENU_HABIT=0;
    private final int VIEW_TYPE_MENU_TASK=1;
    private final int VIEW_TYPE_HABIT=2;
    private final int VIEW_TYPE_TASK=3;

    public TodoListAdapter(Fragment fragment,List<EveryDayTask> everydayTaskList,List<OneDayTask> onedayTaskList){
        mContext=fragment.getContext();
        this.mOnedayTaskList=onedayTaskList;
        this.mEverydayTaskList=everydayTaskList;
    }

    @Override
    public void onItemMoved(int fromPosition, int toPosition) {
        //todo
    }

    @Override
    public void onItemRemoved(int position) {
        if(position>0&&position<=mOnedayTaskList.size()){
            oneDayDaoUtil.deleteOneDayTask(mOnedayTaskList.get(position-1));
            mOnedayTaskList.remove(position-1);
        } else if(position>mOnedayTaskList.size()+1){
            everyDayDaoUtil.deleteEveryDayTask(mEverydayTaskList.get(position-mOnedayTaskList.size()-2));
            mEverydayTaskList.remove(position-mOnedayTaskList.size()-2);
        }
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,getItemCount()-position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,final int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        if(viewType==VIEW_TYPE_MENU_TASK){
            View itemView=layoutInflater.inflate(R.layout.item_text,parent,false);
            return new TodoTextViewHolder(itemView,"每日任务");
        }
        else if(viewType==VIEW_TYPE_MENU_HABIT){
            View itemView=layoutInflater.inflate(R.layout.item_text,parent,false);
            return new TodoTextViewHolder(itemView,"养成习惯");
        }
        else if(viewType==VIEW_TYPE_TASK){
            View itemView=layoutInflater.inflate(R.layout.item_todo_list,parent,false);
           return new TodoOnedayViewHolder(itemView);
        } else if(viewType==VIEW_TYPE_HABIT){
            View itemView=layoutInflater.inflate(R.layout.item_todo_list,parent,false);
            return new TodoEverydayViewHolder(itemView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        holder.itemView.setTag(position);
        //将它与模型层绑定在一起
        if(holder instanceof TodoEverydayViewHolder){
            ((TodoEverydayViewHolder) holder).bind(mEverydayTaskList.get(position-mOnedayTaskList.size()-2));
            ((TodoEverydayViewHolder) holder).setOnDeleteListener(new TodoEverydayViewHolder.OnDeleteListener() {
                @Override
                public void deleteTask() {
                    onItemRemoved(position);
                }
            });
            ((TodoEverydayViewHolder) holder).setOnCompleteListener(new TodoEverydayViewHolder.OnCompleteListener() {
                @Override
                public void completeTask() {
                    boolean isComplete=mEverydayTaskList.get(position-mOnedayTaskList.size()-2).getIsComplete();
                    mEverydayTaskList.get(position-mOnedayTaskList.size()-2).setIsComplete(!isComplete);
                    everyDayDaoUtil.updateEveryDayTask(mEverydayTaskList.get(position-mOnedayTaskList.size()-2));
                }
            });
        } else if(holder instanceof TodoOnedayViewHolder){
            ((TodoOnedayViewHolder) holder).bind(mOnedayTaskList.get(position-1));
            ((TodoOnedayViewHolder) holder).setOnDeleteListener(new TodoOnedayViewHolder.OnDeleteListener() {
                @Override
                public void deleteTask() {
                    onItemRemoved(position);
                }
            });
            ((TodoOnedayViewHolder) holder).setOnCompleteListener(new TodoOnedayViewHolder.OnCompleteListener() {
                @Override
                public void completeTask() {
                    boolean isComplete=mOnedayTaskList.get(position-1).getIsComplete();
                    mOnedayTaskList.get(position-1).setIsComplete(!isComplete);
                    oneDayDaoUtil.updateOneDayTask(mOnedayTaskList.get(position-1));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mEverydayTaskList.size()+2+mOnedayTaskList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return VIEW_TYPE_MENU_TASK;
        } else if(position==1+mOnedayTaskList.size()){
            return VIEW_TYPE_MENU_HABIT;
        } else if(position<=mOnedayTaskList.size()){
            return VIEW_TYPE_TASK;
        } else{
            return VIEW_TYPE_HABIT;
        }
    }

}
