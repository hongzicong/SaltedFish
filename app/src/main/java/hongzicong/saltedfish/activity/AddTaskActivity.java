package hongzicong.saltedfish.activity;

import android.os.Bundle;
import butterknife.ButterKnife;
import hongzicong.saltedfish.R;

public class AddTaskActivity extends BaseAddActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        initToolBar("Add Todo");
    }

}