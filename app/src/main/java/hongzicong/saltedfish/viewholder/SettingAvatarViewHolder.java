package hongzicong.saltedfish.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.carbs.android.avatarimageview.library.SquareAvatarImageView;
import hongzicong.saltedfish.R;

/**
 * Created by Dv00 on 2018/12/24.
 */

public class SettingAvatarViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.text_view_name)
    TextView nameTextView;

    @BindView(R.id.avatar)
    SquareAvatarImageView avatarImageView;

    public SettingAvatarViewHolder(View itemView, String name, int icon_id){
        super(itemView);
        ButterKnife.bind(this,itemView);
        nameTextView.setText(name);
        avatarImageView.setImageResource(icon_id);
    }

}
