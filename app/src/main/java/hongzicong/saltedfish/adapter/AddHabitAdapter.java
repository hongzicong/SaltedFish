package hongzicong.saltedfish.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hongzicong.saltedfish.R;
import hongzicong.saltedfish.viewholder.AddTextViewHolder;

/**
 * Created by Dv00 on 2018/1/4.
 */

public class AddHabitAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private AddTextViewHolder mAddNameViewHolder;
    private AddTextViewHolder mAddDetailViewHolder;

    private Context mContext;
    private final int addRowCount=4;

    public AddHabitAdapter(Activity activity){
        mContext=activity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        if(viewType==0){
            View itemView=layoutInflater.inflate(R.layout.item_edit_text,parent,false);
            if(mAddNameViewHolder==null){
                mAddNameViewHolder=new AddTextViewHolder(layoutInflater,parent,"你想养成的习惯","习惯名称");
            }
            return mAddNameViewHolder;
        }
        else if(viewType==1){
            View itemView=layoutInflater.inflate(R.layout.item_edit_text,parent,false);
            if(mAddDetailViewHolder==null){
                mAddDetailViewHolder=new AddTextViewHolder(layoutInflater,parent,"想养成这个习惯的原因","习惯原因");
            }
            return mAddDetailViewHolder;
        }
        else if(viewType==2){
            return null;
        }
        else if(viewType==3){
            return null;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

}
