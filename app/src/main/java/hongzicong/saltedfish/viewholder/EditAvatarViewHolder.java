package hongzicong.saltedfish.viewholder;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.carbs.android.avatarimageview.library.SquareAvatarImageView;
import hongzicong.saltedfish.R;

public class EditAvatarViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.avatar)
    SquareAvatarImageView imageView;

    public EditAvatarViewHolder(View itemView, String uri){
        super(itemView);
        ButterKnife.bind(this,itemView);
        imageView.setImageURI(Uri.parse(uri));
    }

}
