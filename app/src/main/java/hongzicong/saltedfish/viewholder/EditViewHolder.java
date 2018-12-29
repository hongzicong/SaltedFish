package hongzicong.saltedfish.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import hongzicong.saltedfish.R;

public class EditViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.name_text_view)
    TextView nameTextView;

    @BindView(R.id.src_text_view)
    TextView srcTextView;

    public EditViewHolder(View itemView, String name, String src){
        super(itemView);
        ButterKnife.bind(this,itemView);
        nameTextView.setText(name);
        srcTextView.setText(src);
    }
}
