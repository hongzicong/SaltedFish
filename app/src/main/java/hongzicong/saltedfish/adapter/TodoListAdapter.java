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
import hongzicong.saltedfish.model.Task;
import hongzicong.saltedfish.viewholder.TodoViewHolder;

/**
 * Created by DELL-PC on 2017/12/31.
 */

public class TodoListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected Context mContext;
    private List<Task> mTaskList;
    private Animation mAnimation;

    public TodoListAdapter(Fragment fragment,List<Task> taskList){
        mContext=fragment.getContext();
        mAnimation= AnimationUtils.loadAnimation(mContext, R.anim.list_anim);
        mTaskList=taskList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View itemView=layoutInflater.inflate(R.layout.item_todo_list,parent,false);
        return new TodoViewHolder(itemView,mTaskList.get(viewType));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mTaskList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
