package hongzicong.saltedfish.viewholder;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.carbs.android.avatarimageview.library.SquareAvatarImageView;
import hongzicong.saltedfish.R;
import hongzicong.saltedfish.model.PersonalInfo;

/**
 * Created by Dv00 on 2018/12/24.
 */

public class SettingAvatarViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.text_view_name)
    TextView nameTextView;

    @BindView(R.id.avatar)
    SquareAvatarImageView avatarImageView;

    public SettingAvatarViewHolder(View itemView, PersonalInfo personalInfo){
        super(itemView);
        ButterKnife.bind(this,itemView);

        if(!personalInfo.isLogin()){
            nameTextView.setText("未登录");
            avatarImageView.setImageResource(R.drawable.default_avatar);
        } else{
            nameTextView.setText(personalInfo.getName());
            if(personalInfo.getAvatar().equals("")) {
                if(personalInfo.getGender().equals("女")) {
                    avatarImageView.setImageResource(R.drawable.girl_avatar);
                } else{
                    avatarImageView.setImageResource(R.drawable.boy_avatar);
                }
            } else{
                avatarImageView.setImageURI(Uri.parse(personalInfo.getAvatar()));
            }
        }
    }

}
