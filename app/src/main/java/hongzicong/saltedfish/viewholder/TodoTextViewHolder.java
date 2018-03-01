package hongzicong.saltedfish.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import hongzicong.saltedfish.R;

/**
 * Created by Dv00 on 2018/1/4.
 */

public class TodoTextViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.text_view)
    TextView textView;

    public TodoTextViewHolder(View view,String text){
        super(view);
        ButterKnife.bind(this,itemView);
        textView.setText(text);
    }

}
