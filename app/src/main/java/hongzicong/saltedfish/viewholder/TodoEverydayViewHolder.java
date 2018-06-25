package hongzicong.saltedfish.viewholder;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;

import java.util.Calendar;

import butterknife.BindColor;
import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hongzicong.saltedfish.R;
import hongzicong.saltedfish.activity.DetailActivity;
import hongzicong.saltedfish.model.EveryDayTask;
import hongzicong.saltedfish.model.Task;
import hongzicong.saltedfish.utils.UIUtils;

/**
 * Created by DELL-PC on 2017/12/31.
 */

public class TodoEverydayViewHolder extends RecyclerView.ViewHolder {

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

    public TodoEverydayViewHolder(View itemView){
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    @OnClick(R.id.item_complete)
    public void Complete(){
        checkBox.setChecked(!checkBox.isChecked());
        setMiddleLine(checkBox.isChecked());
        onCompleteListener.completeTask();
    }

    @OnClick(R.id.check_box)
    public void Check(){
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

    protected void setMiddleLine(boolean isComplete){
        if(isComplete){
            taskName.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);
            taskName.getPaint().setAntiAlias(true);
        } else {
            taskName.getPaint().setFlags(0);
        }
        taskName.setText(mTask.getName());
    }

    public void bind(EveryDayTask task){
        mTask=task;

        StringBuffer time=new StringBuffer();

        endTime.setBackground(textBackground);
        time.append("× "+((EveryDayTask)mTask).getCombos());
        endTime.setText(time);
        endTime.setTextSize(20);

        endTime.setTextColor(Color.WHITE);
        if(mTask.getIsComplete()){
            checkBox.setChecked(true);
            setMiddleLine(true);
        }
        taskName.setText(mTask.getName());
    }

    public void close(){
        mSwipeLayout.close();
    }

}
