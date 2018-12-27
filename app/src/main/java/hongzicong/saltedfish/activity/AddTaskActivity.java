package hongzicong.saltedfish.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import hongzicong.saltedfish.R;
import hongzicong.saltedfish.adapter.AddHabitAdapter;
import hongzicong.saltedfish.adapter.AddTaskAdapter;
import hongzicong.saltedfish.fragment.TodoFragment;

public class AddTaskActivity extends BaseAddActivity {

    private AddTaskAdapter addTaskAdapter=new AddTaskAdapter(this);

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        initToolBar("Add Todo");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(addTaskAdapter);

    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent();
        intent.putExtra("returnCode",false);
        this.setResult(TodoFragment.habitRequestCode,intent);
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case android.R.id.home:
            case R.id.action_no:
                intent=new Intent();
                intent.putExtra("returnCode",false);
                this.setResult(TodoFragment.todoReqeustCode,intent);
                finish();
                return true;
            case R.id.action_ok:
                if(addTaskAdapter.isLegal()){
                    intent=new Intent();
                    intent.putExtra("returnCode",true);
                    intent.putExtra("returnId", addTaskAdapter.addInToDB());
                    this.setResult(TodoFragment.todoReqeustCode,intent);
                    finish();
                    return true;
                } else {
                    Toast.makeText(this,"请输入任务名称",Toast.LENGTH_SHORT).show();
                    return false;
                }
        }
        return false;
    }

}
