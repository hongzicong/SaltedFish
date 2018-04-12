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

public class TodoFragment extends Fragment {

    //建立与数据库之间的工具联系
    private EveryDayDaoUtil everyDayDaoUtil=new EveryDayDaoUtil(UIUtils.getContext());
    private OneDayDaoUtil oneDayDaoUtil=new OneDayDaoUtil(UIUtils.getContext());

    private Unbinder mUnbinder;
    private TodoListAdapter mTodoListAdapter;
    public ItemTouchHelper itemTouchHelper;

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

    //公共方法，为了让托管它们的activity调用
    //Bundle用来保存状态
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //View视图在onCreateView方法中生成
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //生成fragment的视图
        View v=inflater.inflate(R.layout.fragment_todo, container, false);
        mUnbinder= ButterKnife.bind(this,v);

        initToolbar();

        updateDataFromDB();

        mTodoListAdapter=new TodoListAdapter(this,everydayTaskList,onedayTaskList);
        mRecyclerView.setAdapter(mTodoListAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ItemTouchHelper.Callback callback = new ItemTouchHelperClass(mTodoListAdapter);
        itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);

        setOnAllListener();
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    //初始化toolbar
    private void initToolbar(){
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    //从数据库中更新数据
    private void updateDataFromDB(){
        everydayTaskList=everyDayDaoUtil.queryAllEveryDayTask();
        onedayTaskList=oneDayDaoUtil.queryAllOneDayTask();
    }

    //设置floatingactionbutton的监听器
    private void setOnAllListener(){
        //每日任务的浮动按钮，且需要知道有没有成功创建
        mAddTodoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), AddTaskActivity.class);
                startActivityForResult(intent,todoReqeustCode);
            }
        });
        //习惯的浮动按钮，且需要知道有没有成功创建
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
        //当成功创建时，从数据库重新读取信息，但是不改变原来的list，否则会出错
        if(returnCode==true){
            switch (requestCode){
                case todoReqeustCode:
                    onedayTaskList.add(oneDayDaoUtil.queryOneDayTaskById(id));
                    mTodoListAdapter.notifyItemInserted(onedayTaskList.size()+1+everydayTaskList.size());
                    break;
                case habitRequestCode:
                    everydayTaskList.add(everyDayDaoUtil.queryEveryDayTaskById(id));
                    mTodoListAdapter.notifyItemInserted(onedayTaskList.size()+1+everydayTaskList.size());
                    break;
            }
            mFloatingActionsMenu.close(false);
        }
    }

}
