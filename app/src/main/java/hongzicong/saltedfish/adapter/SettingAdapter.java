package hongzicong.saltedfish.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hongzicong.saltedfish.R;
import hongzicong.saltedfish.viewholder.SettingAvatarViewHolder;
import hongzicong.saltedfish.viewholder.SettingViewHolder;

/**
 * Created by Dv00 on 2018/1/19.
 */

public class SettingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        if(viewType==0){
            View itemView=layoutInflater.inflate(R.layout.item_avatar_setting,parent,false);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //todo
                }
            });
            return new SettingAvatarViewHolder(itemView,"啦啦啦啦啦啦",R.drawable.test_avatar);
        }
        else if(viewType==1){
            View itemView=layoutInflater.inflate(R.layout.item_ordinary_setting,parent,false);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //todo
                }
            });
            return new SettingViewHolder(itemView,"设置",R.drawable.setting_icon);
        }
        else if(viewType==2){
            View itemView=layoutInflater.inflate(R.layout.item_ordinary_setting,parent,false);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //todo
                }
            });
            return new SettingViewHolder(itemView,"关于我们",R.drawable.about_us_icon);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        holder.itemView.setTag(position);
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
