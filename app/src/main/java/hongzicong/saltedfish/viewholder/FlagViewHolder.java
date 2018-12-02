package hongzicong.saltedfish.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import hongzicong.saltedfish.R;
import hongzicong.saltedfish.utils.SharedPreferencesUtils;

public class FlagViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.text_view)
    TextView mTextView;

    private boolean isProb;

    public FlagViewHolder(View itemView, boolean isProb) {
        super(itemView);
        ButterKnife.bind(this,itemView);
        this.isProb = isProb;

        String flagText;

        if(isProb){
            flagText = SharedPreferencesUtils.getInstance().getString("prob_flag");
        } else {
            flagText = SharedPreferencesUtils.getInstance().getString("likely_flag");
        }
        mTextView.setText(flagText);
    }

    public void setTextView(String flagText){
        mTextView.setText(flagText);
    }

}
