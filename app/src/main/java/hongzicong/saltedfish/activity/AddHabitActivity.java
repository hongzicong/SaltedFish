package hongzicong.saltedfish.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import hongzicong.saltedfish.R;
import hongzicong.saltedfish.adapter.AddHabitAdapter;
import hongzicong.saltedfish.fragment.TodoFragment;
import hongzicong.saltedfish.model.EveryDayTask;
import hongzicong.saltedfish.utils.EveryDayDaoUtil;
import hongzicong.saltedfish.utils.UIUtils;

public class AddHabitActivity extends BaseAddActivity {

    private AddHabitAdapter addHabitAdapter=new AddHabitAdapter(this);

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        initToolBar("Add Habit");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(addHabitAdapter);

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
                intent=new Intent();
                intent.putExtra("returnCode",true);
                intent.putExtra("returnId", addHabitAdapter.addInToDB());
                this.setResult(TodoFragment.habitRequestCode,intent);
                finish();
                return true;
        }
        return false;
    }

}
