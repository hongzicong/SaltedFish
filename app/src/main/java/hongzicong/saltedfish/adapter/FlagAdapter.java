package hongzicong.saltedfish.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import hongzicong.saltedfish.R;
import hongzicong.saltedfish.model.Flag;
import hongzicong.saltedfish.viewholder.FlagTitleViewHolder;
import hongzicong.saltedfish.viewholder.FlagViewHolder;

public class FlagAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Flag> probFlagList;

    private List<Flag> likelyFlgList;

    public FlagAdapter(List<Flag> probFlagList, List<Flag> likelyFlgList){
        this.probFlagList = probFlagList;
        this.likelyFlgList = likelyFlgList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        if(viewType==0){
            View itemView=layoutInflater.inflate(R.layout.item_flag_title,parent,false);
            return new FlagTitleViewHolder(itemView);
        } else if(viewType == 1){
            View itemView=layoutInflater.inflate(R.layout.item_flag_title_likely,parent,false);
            return new FlagTitleViewHolder(itemView);
        } else if(viewType == 2 + probFlagList.size()){
            View itemView=layoutInflater.inflate(R.layout.item_flag_title_prob,parent,false);
            return new FlagTitleViewHolder(itemView);
        } else if(viewType < 2 + probFlagList.size()){
            View itemView=layoutInflater.inflate(R.layout.item_flag,parent,false);
            return new FlagViewHolder(itemView, probFlagList.get(viewType - 1));
        } else {
            View itemView=layoutInflater.inflate(R.layout.item_flag,parent,false);
            return new FlagViewHolder(itemView, likelyFlgList.get(viewType - probFlagList.size() - 3));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 3 + probFlagList.size() + likelyFlgList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
