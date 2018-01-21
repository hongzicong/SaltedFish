package hongzicong.saltedfish.viewholder;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hongzicong.saltedfish.R;
import hongzicong.saltedfish.activity.DetailActivity;
import hongzicong.saltedfish.model.EveryDayTask;
import hongzicong.saltedfish.model.OneDayTask;
import hongzicong.saltedfish.model.Task;
import hongzicong.saltedfish.utils.UIUtils;

/**
 * Created by Dv00 on 2018/1/5.
 */

public class TodoOnedayViewHolder extends AbstractTodoViewHolder{

    private StringBuffer time;

    @BindView(R.id.task_end_time)
    TextView endTime;

    @BindDrawable(R.drawable.round_rectangle)
    Drawable textBackground;

    public TodoOnedayViewHolder(View itemView){
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void bind(OneDayTask oneDayTask){
        mTask=oneDayTask;
        initTaskData();
    }

    @OnClick(R.id.item_detail)
    public void Detail(){
        Intent intent=new Intent(UIUtils.getContext(), DetailActivity.class);
        UIUtils.getContext().startActivity(intent);
    }

    private void initTaskData(){
        time=new StringBuffer();
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

}
