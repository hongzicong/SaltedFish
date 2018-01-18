package hongzicong.saltedfish.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import hongzicong.saltedfish.adapter.TodoListAdapter;
import hongzicong.saltedfish.model.EveryDayTask;
import hongzicong.saltedfish.model.OneDayTask;
import hongzicong.saltedfish.model.Task;
import hongzicong.saltedfish.utils.EveryDayDaoUtil;
import hongzicong.saltedfish.utils.OneDayDaoUtil;
import hongzicong.saltedfish.utils.UIUtils;

public class TodoFragment extends Fragment {

    private EveryDayDaoUtil everyDayDaoUtil=new EveryDayDaoUtil(UIUtils.getContext());
    private OneDayDaoUtil oneDayDaoUtil=new OneDayDaoUtil(UIUtils.getContext());

    private Unbinder mUnbinder;
    private TodoListAdapter mTodoListAdapter;

    public static final int todoReqeustCode=1;
    public static final int habitRequestCode=2;

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

    private List<EveryDayTask> everydayTaskList;
    private List<OneDayTask> onedayTaskList;

    public static TodoFragment newInstance(){
        TodoFragment todoFragment=new TodoFragment();
        return todoFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_todo, container, false);
        mUnbinder= ButterKnife.bind(this,v);

        initToolbar();

        initDataFromDB();

        mTodoListAdapter=new TodoListAdapter(this,everydayTaskList,onedayTaskList);
        mRecyclerView.setAdapter(mTodoListAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        setOnAllListener();
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    private void initToolbar(){
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void initDataFromDB(){
        everydayTaskList=everyDayDaoUtil.queryAllEveryDayTask();
        onedayTaskList=oneDayDaoUtil.queryAllOneDayTask();
        Calendar calendar=Calendar.getInstance();
        OneDayTask task=new OneDayTask(null,"数据结构复习",calendar.getTimeInMillis(),false,"数据结构复习",true);
        onedayTaskList.add(task);
        task=new OneDayTask(null,"Java课项目PPT",calendar.getTimeInMillis(),false,"Java课项目PPT",true);
        onedayTaskList.add(task);
    }

    private void setOnAllListener(){
        mAddTodoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), AddTaskActivity.class);
                startActivityForResult(intent,todoReqeustCode);
            }
        });
        mAddHabitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),AddHabitActivity.class);
                startActivityForResult(intent,habitRequestCode);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        boolean returnCode = data.getBooleanExtra("returnCode",false);
        long id=data.getLongExtra("returnId",-1);
        if(returnCode==true){
            switch (requestCode){
                case todoReqeustCode:
                    onedayTaskList.add(oneDayDaoUtil.queryOneDayTaskById(id));
                    break;
                case habitRequestCode:
                    everydayTaskList.add(everyDayDaoUtil.queryEveryDayTaskById(id));
                    break;
            }
            mTodoListAdapter.notifyDataSetChanged();
            mFloatingActionsMenu.close(false);
        }
    }
}
