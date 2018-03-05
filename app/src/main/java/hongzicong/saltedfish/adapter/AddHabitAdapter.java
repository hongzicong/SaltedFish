package hongzicong.saltedfish.adapter;

import android.accessibilityservice.AccessibilityService;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnClickListener;
import com.orhanobut.dialogplus.OnItemClickListener;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.Calendar;
import java.util.Date;

import hongzicong.saltedfish.R;
import hongzicong.saltedfish.fragment.DatePickerFragment;
import hongzicong.saltedfish.model.EveryDayTask;
import hongzicong.saltedfish.utils.EveryDayDaoUtil;
import hongzicong.saltedfish.utils.UIUtils;
import hongzicong.saltedfish.viewholder.AddDateViewHolder;
import hongzicong.saltedfish.viewholder.AddNumViewHolder;
import hongzicong.saltedfish.viewholder.AddTextViewHolder;

/**
 * Created by Dv00 on 2018/1/4.
 */

public class AddHabitAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private EveryDayDaoUtil everyDayDaoUtil=new EveryDayDaoUtil(UIUtils.getContext());

    private FragmentManager fragmentManager;

    private AddTextViewHolder mAddNameViewHolder;
    private AddTextViewHolder mAddDetailViewHolder;
    private AddDateViewHolder mAddDateViewHolder;
    private AddNumViewHolder mAddNumViewHolder;

    private Context mContext;
    private final int addRowCount=4;

    private static final int REQUEST_DATE=0;

    public AddHabitAdapter(Activity activity){
        mContext=activity;
        fragmentManager=activity.getFragmentManager();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        //养成的习惯名称
        if(viewType==0){
            if(mAddNameViewHolder==null){
                mAddNameViewHolder=new AddTextViewHolder(layoutInflater,parent,"你想养成的习惯","习惯名称");
            }
            return mAddNameViewHolder;
        }
        //养成习惯的原因
        else if(viewType==1){
            if(mAddDetailViewHolder==null){
                mAddDetailViewHolder=new AddTextViewHolder(layoutInflater,parent,"想养成这个习惯的原因","为了什么而开始");
            }
            return mAddDetailViewHolder;
        }
        //日期栏
        else if(viewType==2){
            if(mAddDateViewHolder==null){
                mAddDateViewHolder=new AddDateViewHolder(layoutInflater,parent,"在什么时间结束");
                mAddDateViewHolder.setOnChangeDateListener(new AddDateViewHolder.OnChangeDateListener() {
                    @Override
                    public void showDateDialog() {
                        DatePickerFragment dialog=DatePickerFragment.newInstance(new Date(mAddDateViewHolder.getEndTime()));
                        dialog.setFragmentInteraction(new DatePickerFragment.FragmentInteraction() {
                            @Override
                            public void changeDate(long time) {
                                mAddDateViewHolder.setEndTime(time);
                            }
                        });
                        dialog.show(fragmentManager,"DialogDate");
                    }
                });
            }
            return mAddDateViewHolder;
        }
        else if(viewType==3){
            if(mAddNumViewHolder==null){
                mAddNumViewHolder=new AddNumViewHolder(layoutInflater,parent);
            }
            return mAddNumViewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    public long addInToDB(){
        EveryDayTask everyDayTask=getTaskFromViewHolder();
        everyDayDaoUtil.insertEveryDayTask(everyDayTask);
        return everyDayTask.getId();
    }

    public EveryDayTask getTaskFromViewHolder(){
        EveryDayTask everyDayTask=new EveryDayTask(null,mAddDetailViewHolder.getText(),mAddDateViewHolder.getEndTime(),false,mAddNameViewHolder.getText(),false,mAddNumViewHolder.getNumOfDay());
        return everyDayTask;
    }


}
