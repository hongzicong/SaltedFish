package hongzicong.saltedfish.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import hongzicong.saltedfish.model.Flag;

public class FlagViewHolder extends RecyclerView.ViewHolder {

    private Flag mFlag;

    public FlagViewHolder(View itemView, Flag flag) {
        super(itemView);
        mFlag = flag;
    }

}
