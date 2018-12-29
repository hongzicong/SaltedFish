package hongzicong.saltedfish.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import hongzicong.saltedfish.R;
import hongzicong.saltedfish.model.PersonalInfo;
import hongzicong.saltedfish.utils.UIUtils;
import hongzicong.saltedfish.viewholder.SettingViewHolder;

/**
 * Created by Dv00 on 2018/12/27.
 */

public class SettingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        if(viewType==0){
            View itemView=layoutInflater.inflate(R.layout.item_setting,parent,false);
            return new SettingViewHolder(itemView,"更改风格");
        }
        else if(viewType==1){
            View itemView=layoutInflater.inflate(R.layout.item_setting,parent,false);
            return new SettingViewHolder(itemView,"备份");
        }
        else if(viewType==2){
            View itemView=layoutInflater.inflate(R.layout.item_setting,parent,false);
            return new SettingViewHolder(itemView,"更新");
        }
        else if(viewType==3){
            View itemView=layoutInflater.inflate(R.layout.item_setting,parent,false);
            return new SettingViewHolder(itemView,"退出登录");
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
                    // TODO
                }
            });
        } else if(position == 1){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!PersonalInfo.getPersonalInfo().isLogin()){
                        Toast.makeText(UIUtils.getContext(), "请先登录", Toast.LENGTH_SHORT).show();
                    } else{
                        // TODO
                    }
                }
            });
        } else if(position == 2){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!PersonalInfo.getPersonalInfo().isLogin()){
                        Toast.makeText(UIUtils.getContext(), "请先登录", Toast.LENGTH_SHORT).show();
                    } else{
                        // TODO
                    }
                }
            });
        } else if(position == 3){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!PersonalInfo.getPersonalInfo().isLogin()){
                        Toast.makeText(UIUtils.getContext(), "请先登录", Toast.LENGTH_SHORT).show();
                    } else{
                        // TODO
                    }
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
        return 4;
    }
}
