package hongzicong.saltedfish.viewholder;

import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hongzicong.saltedfish.R;
import hongzicong.saltedfish.model.EveryDayTask;
import hongzicong.saltedfish.utils.UIUtils;

/**
 * Created by Dv00 on 2018/1/4.
 */

public class AddDateViewHolder extends RecyclerView.ViewHolder  {

    private String name;
    private SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy年MM月dd日");

    @BindView(R.id.image_button_add)
    TextView plusImage;

    @BindView(R.id.image_button_minus)
    TextView minusImage;

    @BindView(R.id.text_view_date)
    TextView dateTextView;

    @BindView(R.id.text_view)
    TextView nameTextView;

    private Date endTime;

    private OnChangeDateListener mOnChangeDateListener;

    public interface OnChangeDateListener{
        void showDateDialog();
    }

    public void setOnChangeDateListener(OnChangeDateListener onChangeDateListener){
        mOnChangeDateListener=onChangeDateListener;
    }

    public AddDateViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup,String name){
        super(layoutInflater.inflate(R.layout.item_date,viewGroup,false));
        ButterKnife.bind(this,itemView);
        this.name=name;
        this.endTime=new Date();
        setEndTime(Calendar.getInstance().getTimeInMillis());
        nameTextView.setText(this.name);
    }

    public long getEndTime(){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(endTime);
        return calendar.getTimeInMillis();
    }

    public void setEndTime(long millisTime){
        endTime.setTime(millisTime);
        this.dateTextView.setText(simpleDateFormat.format(endTime));
    }

    @OnClick(R.id.text_view_date)
    public void changeDate(){
        mOnChangeDateListener.showDateDialog();
    }

    @OnClick(R.id.image_button_minus)
    public void minusDate(){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(endTime);
        calendar.add(Calendar.DATE,-1);
        setEndTime(calendar.getTimeInMillis());
    }

    @OnClick(R.id.image_button_add)
    public void addDate(){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(endTime);
        calendar.add(Calendar.DATE,1);
        setEndTime(calendar.getTimeInMillis());
    }

}
