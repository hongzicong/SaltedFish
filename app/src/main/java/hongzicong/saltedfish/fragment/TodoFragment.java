package hongzicong.saltedfish.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import hongzicong.saltedfish.R;
import hongzicong.saltedfish.activity.AddHabitActivity;
import hongzicong.saltedfish.activity.AddTaskActivity;
import hongzicong.saltedfish.adapter.ItemTouchHelperClass;
import hongzicong.saltedfish.adapter.TodoListAdapter;
import hongzicong.saltedfish.model.EveryDayTask;
import hongzicong.saltedfish.model.OneDayTask;
import hongzicong.saltedfish.model.Task;
import hongzicong.saltedfish.utils.EveryDayDaoUtil;
import hongzicong.saltedfish.utils.OneDayDaoUtil;
import hongzicong.saltedfish.utils.UIUtils;

import static hongzicong.saltedfish.utils.Util.getToday;

public class TodoFragment extends Fragment {

    private EveryDayDaoUtil everyDayDaoUtil=new EveryDayDaoUtil(UIUtils.getContext());
    private OneDayDaoUtil oneDayDaoUtil=new OneDayDaoUtil(UIUtils.getContext());

    private Unbinder mUnbinder;
    private TodoListAdapter mTodoListAdapter;
    public ItemTouchHelper itemTouchHelper;

    public static final int todoReqeustCode = 1;
    public static final int habitRequestCode = 2;

    @BindView(R.id.list_todo)
    RecyclerView mRecyclerView;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.multiple_fab)
    FloatingActionMenu mFloatingActionsMenu;

    @BindView(R.id.fab_add_habit)
    FloatingActionButton mAddHabitButton;

    @BindView(R.id.fab_add_todo)
    FloatingActionButton mAddTodoButton;

    @BindView(R.id.text_view_date)
    TextView dateTextView;

    @BindView(R.id.salted_fish_image_view)
    ImageView saltedFishImageView;

    private List<EveryDayTask> everydayTaskList;
    private List<OneDayTask> onedayTaskList;

    public static TodoFragment newInstance(){
        TodoFragment todoFragment=new TodoFragment();
        return todoFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_todo, container, false);
        mUnbinder= ButterKnife.bind(this,v);

        initToolbar();

        updateData();

        initLayerout();

        setOnAllListener();
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    private void initLayerout(){
        if(everydayTaskList.isEmpty() && onedayTaskList.isEmpty()){
            saltedFishImageView.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.INVISIBLE);
        } else{
            saltedFishImageView.setVisibility(View.INVISIBLE);
            mRecyclerView.setVisibility(View.VISIBLE);
        }
        mTodoListAdapter=new TodoListAdapter(this,everydayTaskList,onedayTaskList);
        mRecyclerView.setAdapter(mTodoListAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ItemTouchHelper.Callback callback = new ItemTouchHelperClass(mTodoListAdapter);
        itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }


    private void initToolbar(){
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        dateTextView.setText(getToday());
    }


    private void updateData(){
        everydayTaskList=everyDayDaoUtil.queryAllEveryDayTask();
        onedayTaskList=oneDayDaoUtil.queryAllOneDayTask();
    }

    private void setOnAllListener(){
        mAddTodoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFloatingActionsMenu.close(true);
                Intent intent=new Intent(getContext(), AddTaskActivity.class);
                startActivityForResult(intent,todoReqeustCode);
            }
        });
        mAddHabitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFloatingActionsMenu.close(true);
                Intent intent=new Intent(getContext(),AddHabitActivity.class);
                startActivityForResult(intent,habitRequestCode);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        boolean returnCode = data.getBooleanExtra("returnCode",false);
        long id=data.getLongExtra("returnId",-1);
        if(returnCode){
            switch (requestCode){
                case todoReqeustCode:
                    onedayTaskList.add(oneDayDaoUtil.queryOneDayTaskById(id));
                    mTodoListAdapter.notifyDataSetChanged();
                    break;
                case habitRequestCode:
                    everydayTaskList.add(everyDayDaoUtil.queryEveryDayTaskById(id));
                    mTodoListAdapter.notifyDataSetChanged();
                    break;
            }
            mFloatingActionsMenu.close(false);
        }
    }

}
