package hongzicong.saltedfish.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hongzicong.saltedfish.R;
import hongzicong.saltedfish.utils.SharedPreferencesUtils;
import hongzicong.saltedfish.viewholder.FlagTitleViewHolder;
import hongzicong.saltedfish.viewholder.FlagViewHolder;

public class FlagAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        if(viewType==0){
            View itemView=layoutInflater.inflate(R.layout.item_flag_title,parent,false);
            return new FlagTitleViewHolder(itemView);
        } else if(viewType == 1){
            View itemView=layoutInflater.inflate(R.layout.item_flag_title_likely,parent,false);
            return new FlagTitleViewHolder(itemView);
        } else if(viewType == 3){
            View itemView=layoutInflater.inflate(R.layout.item_flag_title_prob,parent,false);
            return new FlagTitleViewHolder(itemView);
        } else if(viewType == 2){
            View itemView=layoutInflater.inflate(R.layout.item_flag,parent,false);
            return new FlagViewHolder(itemView, true);
        } else {
            View itemView=layoutInflater.inflate(R.layout.item_flag,parent,false);
            return new FlagViewHolder(itemView, false);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(position == 2){
            ((FlagViewHolder)holder).setTextView(SharedPreferencesUtils.getInstance().getString("prob_flag"));
        } else if (position == 4) {
            ((FlagViewHolder)holder).setTextView(SharedPreferencesUtils.getInstance().getString("likely_flag"));
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
