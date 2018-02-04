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
import hongzicong.saltedfish.model.Task;

public class TodoFragment extends Fragment {

    private Unbinder mUnbinder;
    private TodoListAdapter mTodoListAdapter;

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

    private List<Task> taskList;

    public static TodoFragment newInstance(){
        TodoFragment todoFragment=new TodoFragment();
        return todoFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_todo, container, false);
        mUnbinder= ButterKnife.bind(this,v);

        initToolbar();
        initData();

        mTodoListAdapter=new TodoListAdapter(this,taskList);
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

    private void initData(){
        taskList=new ArrayList();
        Calendar date=Calendar.getInstance();
        Task task=new Task(0,"健身",date,false,"健身");
        task.setEveryDay(true);
        taskList.add(task);
        task=new Task(1,"数据结构复习",date,false,"数据结构复习");
        taskList.add(task);
        task=new Task(2,"Java课项目PPT",date,false,"Java课项目PPT");
        taskList.add(task);
        task=new Task(3,"每周软导博客",date,false,"每周软导博客");
        taskList.add(task);
        task=new Task(4,"微软小英单词任务",date,false,"微软小英单词");
        task.setEveryDay(true);
        taskList.add(task);
        task=new Task(5,"两天一篇论文",date,false,"两天一篇论文");
        task.setEveryDay(true);
        taskList.add(task);
    }

    private void setOnAllListener(){
        mAddTodoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), AddTaskActivity.class);
                getActivity().startActivity(intent);
            }
        });
        mAddHabitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),AddHabitActivity.class);
                getActivity().startActivity(intent);
            }
        });
    }


}
