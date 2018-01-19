package hongzicong.saltedfish.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hongzicong.saltedfish.R;

/**
 * Created by Dv00 on 2018/1/4.
 */

public class AddNumViewHolder extends RecyclerView.ViewHolder{

    private int numOfDay=1;

    @BindView(R.id.text_view)
    TextView textView;

    @BindView(R.id.text_view_date)
    TextView dateView;

    @BindView(R.id.image_button_add)
    TextView plusImage;

    @BindView(R.id.image_button_minus)
    TextView minusImage;

    public AddNumViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup){
        super(layoutInflater.inflate(R.layout.item_num,viewGroup,false));
        ButterKnife.bind(this,itemView);
        initUI();
    }

    private void initUI(){
        textView.setText("多久打卡一次");
    }

    @OnClick(R.id.image_button_add)
    public void addNum(){
        numOfDay++;
        dateView.setText(""+numOfDay);
    }

    @OnClick(R.id.image_button_minus)
    public void minusNum(){
        if(numOfDay>1){
            numOfDay--;
        }
        dateView.setText(""+numOfDay);
    }

    public int getNumOfDay(){
        return numOfDay;
    }

}
