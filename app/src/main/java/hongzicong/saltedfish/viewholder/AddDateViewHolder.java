package hongzicong.saltedfish.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

    @BindView(R.id.image_button_add)
    ImageButton plusImage;

    @BindView(R.id.image_button_minus)
    ImageButton minusImage;

    @BindView(R.id.text_view_date)
    TextView dateTextView;

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
    }

    public long getEndTime(){
        String dateString=dateTextView.getText().toString();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy年MM月dd日");
        Date date=new Date();
        try{
            date=simpleDateFormat.parse(dateString);
        }catch (Exception e){
            System.out.print(e);
        }
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getTimeInMillis();
    }

    @OnClick(R.id.text_view_date)
    public void changeDate(){
        mOnChangeDateListener.showDateDialog();
    }

    @OnClick(R.id.image_button_minus)
    public void minusDate(){

    }

    @OnClick(R.id.image_button_add)
    public void addDate(){

    }

}
