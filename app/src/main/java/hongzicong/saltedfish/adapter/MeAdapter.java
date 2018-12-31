package hongzicong.saltedfish.adapter;

import android.app.Fragment;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hongzicong.saltedfish.R;
import hongzicong.saltedfish.activity.AboutActivity;
import hongzicong.saltedfish.activity.LoginRegisterActivity;
import hongzicong.saltedfish.activity.SaltedFishFlagActivity;
import hongzicong.saltedfish.activity.SaltedFishSquareActivity;
import hongzicong.saltedfish.activity.SettingActivity;
import hongzicong.saltedfish.activity.PersonalInfoActivity;
import hongzicong.saltedfish.fragment.MeFragment;
import hongzicong.saltedfish.model.PersonalInfo;
import hongzicong.saltedfish.utils.UIUtils;
import hongzicong.saltedfish.viewholder.SettingAvatarViewHolder;
import hongzicong.saltedfish.viewholder.SettingIconViewHolder;

/**
 * Created by Dv00 on 2018/1/19.
 */

public class MeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    private MeFragment mMeFragment;

    public MeAdapter(MeFragment fragment){
        this.mMeFragment = fragment;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        if(viewType == 0){
            View itemView=layoutInflater.inflate(R.layout.item_avatar_setting,parent,false);
            return new SettingAvatarViewHolder(itemView);
        }
        else if(viewType == 1){
            View itemView=layoutInflater.inflate(R.layout.item_icon_setting,parent,false);
            return new SettingIconViewHolder(itemView,"立Flag",R.drawable.flag_icon);
        }
        else if(viewType == 2){
            View itemView=layoutInflater.inflate(R.layout.item_icon_setting,parent,false);
            return new SettingIconViewHolder(itemView,"咸鱼广场",R.drawable.fish_icon);
        }
        else if(viewType == 3){
            View itemView=layoutInflater.inflate(R.layout.item_icon_setting,parent,false);
            return new SettingIconViewHolder(itemView,"关于我们",R.drawable.about_us_icon);
        }
        else if(viewType == 4){
            View itemView=layoutInflater.inflate(R.layout.item_icon_setting,parent,false);
            return new SettingIconViewHolder(itemView,"设置",R.drawable.setting_icon);
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
                    if(PersonalInfo.isLogin()){
                        Intent intent=new Intent(UIUtils.getContext(), PersonalInfoActivity.class);
                        UIUtils.getContext().startActivity(intent);
                    } else{
                        Intent intent=new Intent(UIUtils.getContext(), LoginRegisterActivity.class);
                        mMeFragment.startActivityForResult(intent, 2);
                    }
                }
            });
            ((SettingAvatarViewHolder)holder).updateData();
        } else if(position == 1){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(UIUtils.getContext(), SaltedFishFlagActivity.class);
                    UIUtils.getContext().startActivity(intent);
                }
            });
        }  else if(position == 2){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(UIUtils.getContext(), SaltedFishSquareActivity.class);
                    UIUtils.getContext().startActivity(intent);
                }
            });
        }  else if(position == 3){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(UIUtils.getContext(), AboutActivity.class);
                    UIUtils.getContext().startActivity(intent);
                }
            });
        } else if(position == 4){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(UIUtils.getContext(), SettingActivity.class);
                    mMeFragment.startActivityForResult(intent, 2);
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
        return 5;
    }

}
