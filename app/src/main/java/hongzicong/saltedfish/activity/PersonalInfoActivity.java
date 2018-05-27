package hongzicong.saltedfish.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import hongzicong.saltedfish.R;
import hongzicong.saltedfish.adapter.PersonalInfoAdapter;
import hongzicong.saltedfish.model.PersonalInfo;
import hongzicong.saltedfish.utils.UIUtils;

public class PersonalInfoActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private PersonalInfo mPersonalInfo;

    private PersonalInfoAdapter mPersonalInfoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
        ButterKnife.bind(this);

        initPersonalData();

        initToolbar();

        initLayout();

    }

    private void initPersonalData(){
        mPersonalInfo = PersonalInfo.getPersonalInfo();
    }

    private void initLayout(){
        mPersonalInfoAdapter = new PersonalInfoAdapter();
        mRecyclerView.setAdapter(mPersonalInfoAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(UIUtils.getContext()));
    }

    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
