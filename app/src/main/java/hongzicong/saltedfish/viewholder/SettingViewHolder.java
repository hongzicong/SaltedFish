package hongzicong.saltedfish.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import hongzicong.saltedfish.R;

/**
 * Created by Dv00 on 2018/12/27.
 */

public class SettingViewHolder extends RecyclerView.ViewHolder  {

    @BindView(R.id.text_view)
    TextView textView;

    public SettingViewHolder(View itemView, String text){
        super(itemView);
        ButterKnife.bind(this,itemView);
        textView.setText(text);
    }

}
