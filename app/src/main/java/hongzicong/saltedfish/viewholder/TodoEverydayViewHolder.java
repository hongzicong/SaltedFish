package hongzicong.saltedfish.viewholder;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

public class TodoEverydayViewHolder extends RecyclerView.ViewHolder{

    private EveryDayTask mTask;

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

    @BindDrawable(R.drawable.round_rectangle)
    Drawable textBackground;

    private OnDeleteListener onDeleteListener;

    public interface OnDeleteListener{
        void deleteTask();
    }

    public void setOnDeleteListener(OnDeleteListener onDeleteListener){
        this.onDeleteListener=onDeleteListener;
    }

    public TodoEverydayViewHolder(View itemView){
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    @OnClick(R.id.item_detail)
    public void Click(){
        Intent intent=new Intent(UIUtils.getContext(), DetailActivity.class);
        UIUtils.getContext().startActivity(intent);
    }

    @OnClick(R.id.item_delete)
    public void Delete(){
        onDeleteListener.deleteTask();
    }

    public void bind(EveryDayTask task){
        mTask=task;
        StringBuffer time=new StringBuffer();
        endTime.setBackground(textBackground);
        time.append("× "+mTask.getCount());
        endTime.setText(time);
        endTime.setTextSize(20);

        endTime.setTextColor(Color.WHITE);
        taskName.setText(mTask.getName());
    }

}
