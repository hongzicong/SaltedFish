package hongzicong.saltedfish.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import hongzicong.saltedfish.R;

/**
 * Created by Dv00 on 2018/1/19.
 */

public class SettingIconViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.text_view)
    TextView textView;

    @BindView(R.id.icon_view)
    ImageView imageView;

    public SettingIconViewHolder(View itemView, String text, int icon_id){
        super(itemView);
        ButterKnife.bind(this,itemView);
        textView.setText(text);
        imageView.setImageResource(icon_id);
    }

}
