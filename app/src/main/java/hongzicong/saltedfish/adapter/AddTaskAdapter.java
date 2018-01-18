package hongzicong.saltedfish.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by Dv00 on 2018/1/5.
 */

public class AddTaskAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    private Context mContext;

    public AddTaskAdapter(Activity activity){
        mContext=activity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
