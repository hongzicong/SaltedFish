package hongzicong.saltedfish.adapter;

import android.accessibilityservice.AccessibilityService;
import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import hongzicong.saltedfish.R;
import hongzicong.saltedfish.utils.UIUtils;
import hongzicong.saltedfish.viewholder.AddDateViewHolder;
import hongzicong.saltedfish.viewholder.AddTextViewHolder;

/**
 * Created by Dv00 on 2018/1/4.
 */

public class AddHabitAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private AddTextViewHolder mAddNameViewHolder;
    private AddTextViewHolder mAddDetailViewHolder;
    private AddDateViewHolder mAddDateViewHolder;

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
            View itemView=layoutInflater.inflate(R.layout.item_date,parent,false);
            if(mAddDateViewHolder==null){
                mAddDateViewHolder=new AddDateViewHolder(layoutInflater,parent,"在什么时间结束");
                mAddDateViewHolder.setOnChangeDateListener(new AddDateViewHolder.OnChangeDateListener() {
                    @Override
                    public void showDateDialog() {
                        DialogPlus dialogPlus=DialogPlus.newDialog(mContext)
                                .setContentHolder(new ViewHolder(R.layout.dialog_date))
                                .setCancelable(true)
                                .setGravity(Gravity.CENTER)
                                .create();
                        dialogPlus.show();
                        InputMethodManager inputMethodManager = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                        inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                });
            }
            return mAddDateViewHolder;
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
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

}
