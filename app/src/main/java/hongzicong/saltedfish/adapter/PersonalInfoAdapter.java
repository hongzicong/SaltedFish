package hongzicong.saltedfish.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hongzicong.saltedfish.R;
import hongzicong.saltedfish.model.PersonalInfo;
import hongzicong.saltedfish.viewholder.EditAvatarViewHolder;
import hongzicong.saltedfish.viewholder.EditViewHolder;


public class PersonalInfoAdapter extends RecyclerView.Adapter {

    private PersonalInfo mPersonalInfo = PersonalInfo.getPersonalInfo();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        if(viewType==0){
            View itemView=layoutInflater.inflate(R.layout.item_avatar_edit,parent,false);
            return new EditAvatarViewHolder(itemView,mPersonalInfo.getAvatar());
        }
        else if(viewType==1){
            View itemView=layoutInflater.inflate(R.layout.item_setting_edit,parent,false);
            return new EditViewHolder(itemView, "名字", mPersonalInfo.getName());
        }
        else if(viewType==2){
            View itemView=layoutInflater.inflate(R.layout.item_setting_edit,parent,false);
            return new EditViewHolder(itemView, "性别", mPersonalInfo.getGender());
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        holder.itemView.setTag(position);
        if(position == 0){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        } else if(position == 1){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        } else if(position == 2){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
