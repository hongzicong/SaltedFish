package hongzicong.saltedfish.viewholder;

import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;

import java.util.Calendar;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hongzicong.saltedfish.R;
import hongzicong.saltedfish.activity.DetailActivity;
import hongzicong.saltedfish.model.OneDayTask;
import hongzicong.saltedfish.model.Task;
import hongzicong.saltedfish.utils.UIUtils;

/**
 * Created by Dv00 on 2018/1/5.
 */

public class TodoOnedayViewHolder extends RecyclerView.ViewHolder {

    private StringBuffer time;

    private Task mTask;

    @BindView(R.id.task_end_time)
    TextView endTime;

    @BindDrawable(R.drawable.round_rectangle)
    Drawable textBackground;

    @BindView(R.id.list_item_layout)
    SwipeLayout mSwipeLayout;

    @BindView(R.id.item_detail)
    TextView detailButton;

    @BindView(R.id.item_complete)
    TextView completeButton;

    @BindView(R.id.item_delete)
    TextView deleteButton;

    @BindView(R.id.task_name)
    TextView taskName;

    @BindView(R.id.check_box)
    CheckBox checkBox;

    protected OnDeleteListener onDeleteListener;
    protected OnCompleteListener onCompleteListener;

    public interface OnDeleteListener{
        void deleteTask();
    }

    public interface OnCompleteListener{
        void completeTask();
    }

    public void setOnDeleteListener(OnDeleteListener onDeleteListener){
        this.onDeleteListener=onDeleteListener;
    }

    public void setOnCompleteListener(OnCompleteListener onCompleteListener) {
        this.onCompleteListener = onCompleteListener;
    }

    public TodoOnedayViewHolder(View itemView){
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    @OnClick(R.id.item_complete)
    public void Complete(){
        checkBox.setChecked(!checkBox.isChecked());
        setMiddleLine(checkBox.isChecked());
        onCompleteListener.completeTask();
    }

    @OnClick(R.id.item_delete)
    public void Delete(){
        onDeleteListener.deleteTask();
    }

    @OnClick(R.id.item_detail)
    public void Detail(){
        Intent intent=new Intent(UIUtils.getContext(), DetailActivity.class);
        UIUtils.getContext().startActivity(intent);
    }

    @OnClick(R.id.check_box)
    public void Check(){
        setMiddleLine(checkBox.isChecked());
        onCompleteListener.completeTask();
    }

    protected void setMiddleLine(boolean isComplete){
        if(isComplete){
            taskName.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);
            taskName.getPaint().setAntiAlias(true);
        } else {
            taskName.getPaint().setFlags(0);
        }
        taskName.setText(mTask.getName());
    }

    public void bind(OneDayTask oneDayTask){
        mTask = oneDayTask;
        initTaskData();
    }

    private void initTaskData(){
        time = new StringBuffer();
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
        if(mTask.getIsDetailTime()){
            if(mTask.getEndTime().get(Calendar.HOUR_OF_DAY)<10){
                time.append("0");
            }
            time.append(mTask.getEndTime().get(Calendar.HOUR_OF_DAY)+":");
            if(mTask.getEndTime().get(Calendar.MINUTE)<10){
                time.append("0");
            }
            time.append(mTask.getEndTime().get(Calendar.MINUTE));
        }

        if(mTask.getIsComplete()){
            checkBox.setChecked(true);
            setMiddleLine(true);
        }

        endTime.setText(time);
        taskName.setText(mTask.getName());
    }


    public void close(){
        mSwipeLayout.close();
    }

}
