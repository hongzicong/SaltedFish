package hongzicong.saltedfish.viewholder;

import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hongzicong.saltedfish.R;
import hongzicong.saltedfish.model.Task;

/**
 * Created by Dv00 on 2018/1/21.
 */

public class AbstractTodoViewHolder extends RecyclerView.ViewHolder {

    protected Task mTask;

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

    public AbstractTodoViewHolder(View itemView){
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    @OnClick(R.id.item_complete)
    public void Complete_(){
        checkBox.setChecked(!checkBox.isChecked());
        Complete();
    }

    @OnClick(R.id.check_box)
    public void Complete(){
        setMiddleLine(checkBox.isChecked());
        onCompleteListener.completeTask();
    }

    @OnClick(R.id.item_delete)
    public void Delete(){
        onDeleteListener.deleteTask();
    }

    protected void setMiddleLine(boolean isComplete){
        if(isComplete){
            taskName.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);
            taskName.getPaint().setAntiAlias(true);
        }else {
            taskName.getPaint().setFlags(0);
        }
        taskName.setText(mTask.getName());
    }

}
