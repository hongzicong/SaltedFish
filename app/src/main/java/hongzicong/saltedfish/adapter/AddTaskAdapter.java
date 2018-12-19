package hongzicong.saltedfish.adapter;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.Calendar;
import java.util.Date;

import hongzicong.saltedfish.fragment.DatePickerFragment;
import hongzicong.saltedfish.model.OneDayTask;
import hongzicong.saltedfish.utils.OneDayDaoUtil;
import hongzicong.saltedfish.utils.UIUtils;
import hongzicong.saltedfish.viewholder.AddDateViewHolder;
import hongzicong.saltedfish.viewholder.AddNumViewHolder;
import hongzicong.saltedfish.viewholder.AddTextViewHolder;

/**
 * Created by Dv00 on 2018/1/5.
 */

public class AddTaskAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    private OneDayDaoUtil oneDayDaoUtil=new OneDayDaoUtil(UIUtils.getContext());

    private FragmentManager fragmentManager;

    private AddTextViewHolder mAddNameViewHolder;
    private AddTextViewHolder mAddDetailViewHolder;
    private AddDateViewHolder mAddDateViewHolder;

    private Context mContext;

    public AddTaskAdapter(Activity activity){
        mContext=activity;
        fragmentManager=activity.getFragmentManager();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        if(viewType==0){
            if(mAddNameViewHolder==null){
                mAddNameViewHolder=new AddTextViewHolder(layoutInflater,parent,"要完成什么任务","任务名称");
            }
            return mAddNameViewHolder;
        } else if(viewType==1){
            if(mAddDetailViewHolder==null){
                mAddDetailViewHolder=new AddTextViewHolder(layoutInflater,parent,"备忘录","记下防止自己忘记");
            }
            return mAddDetailViewHolder;
        } else if(viewType==2){
            if(mAddDateViewHolder==null){
                mAddDateViewHolder=new AddDateViewHolder(layoutInflater,parent,"什么时间前完成任务");
                mAddDateViewHolder.setOnChangeDateListener(new AddDateViewHolder.OnChangeDateListener() {
                    @Override
                    public void showDateDialog() {
                        DatePickerFragment dialog=DatePickerFragment.newInstance(new Date(mAddDateViewHolder.getEndTime()));
                        dialog.setFragmentInteraction(new DatePickerFragment.FragmentInteraction() {
                            @Override
                            public void changeDate(long time) {
                                mAddDateViewHolder.setEndTime(time);
                            }
                        });
                        dialog.show(fragmentManager,"DialogDate");
                    }
                });
            }
            return mAddDateViewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    public long addInToDB(){
        OneDayTask oneDayTask=getTaskFromViewHolder();
        oneDayDaoUtil.insertOneDayTask(oneDayTask);
        return oneDayTask.getId();
    }

    public OneDayTask getTaskFromViewHolder(){
        Calendar calendar=Calendar.getInstance();
        OneDayTask oneDayTask=new OneDayTask(null,mAddDetailViewHolder.getText(),calendar.getTimeInMillis(),
                mAddDateViewHolder.getEndTime(), false,mAddNameViewHolder.getText(),false);
        return oneDayTask;
    }

    public boolean isLegal(){
        if(mAddNameViewHolder.getText().isEmpty()){
            return false;
        }
        return true;
    }

}
