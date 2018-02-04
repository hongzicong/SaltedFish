package hongzicong.saltedfish.viewholder;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hongzicong.saltedfish.R;
import hongzicong.saltedfish.activity.DetailActivity;
import hongzicong.saltedfish.model.Task;
import hongzicong.saltedfish.utils.UIUtils;

/**
 * Created by DELL-PC on 2017/12/31.
 */

public class TodoViewHolder extends RecyclerView.ViewHolder{

    private Task mTask;

    @BindView(R.id.task_end_time)
    TextView endTime;

    @BindView(R.id.task_name)
    TextView taskName;

    @BindView(R.id.item_detail)
    TextView detailButton;

    @BindView(R.id.item_complete)
    TextView completeButton;

    @BindView(R.id.item_delete)
    TextView deleteButton;

    @OnClick(R.id.item_detail)
    public void Click(){
        Intent intent=new Intent(UIUtils.getContext(), DetailActivity.class);
        UIUtils.getContext().startActivity(intent);
    }

    public TodoViewHolder(View itemView,Task task){
        super(itemView);
        ButterKnife.bind(this,itemView);
        mTask=task;
        initTaskData();
    }

    private void initTaskData(){
        StringBuffer time=new StringBuffer();
        if(mTask.isEveryDay()){
            time.append("每日任务");
            endTime.setText(time);
            taskName.setText(mTask.getName());
            return;
        }
        boolean isEqual=true;
        if(mTask.getEndTime().get(Calendar.YEAR)!=Calendar.getInstance().get(Calendar.YEAR)){
            time.append(mTask.getEndTime().get(Calendar.YEAR));
            time.append("年");
            isEqual=false;
        }
        if(isEqual&&mTask.getEndTime().get(Calendar.MONTH)!=Calendar.getInstance().get(Calendar.MONTH)){
            if(mTask.getEndTime().get(Calendar.MONTH)<9){
                time.append("0"+(mTask.getEndTime().get(Calendar.MONTH)+1));
            }
            else{
                time.append(mTask.getEndTime().get(Calendar.MONTH)+1);
            }
            time.append("月");
            isEqual=false;
        }
        if(isEqual&&mTask.getEndTime().get(Calendar.DAY_OF_MONTH)!=Calendar.getInstance().get(Calendar.DAY_OF_MONTH)){
            if(mTask.getEndTime().get(Calendar.DAY_OF_MONTH)<10){
                time.append("0"+mTask.getEndTime().get(Calendar.DAY_OF_MONTH));
            }
            else{
                time.append(mTask.getEndTime().get(Calendar.DAY_OF_MONTH));
            }
            time.append("日");
            isEqual=false;
        }
        if(mTask.isDetailTime()){
            if(mTask.getEndTime().get(Calendar.HOUR_OF_DAY)<10){
                time.append("0");
            }
            time.append(mTask.getEndTime().get(Calendar.HOUR_OF_DAY)+":");
            if(mTask.getEndTime().get(Calendar.MINUTE)<10){
                time.append("0");
            }
            time.append(mTask.getEndTime().get(Calendar.MINUTE));
        }
        endTime.setText(time);
        taskName.setText(mTask.getName());
    }

}
