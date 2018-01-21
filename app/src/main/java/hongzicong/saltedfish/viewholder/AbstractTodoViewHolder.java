package hongzicong.saltedfish.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import hongzicong.saltedfish.R;

/**
 * Created by Dv00 on 2018/1/21.
 */

public class AbstractTodoViewHolder extends RecyclerView.ViewHolder {

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

}
