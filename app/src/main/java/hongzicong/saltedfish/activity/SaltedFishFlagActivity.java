package hongzicong.saltedfish.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import hongzicong.saltedfish.R;
import hongzicong.saltedfish.utils.SharedPreferencesUtils;

public class SaltedFishFlagActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.salted_fish_image_view)
    ImageView saltedFishImageView;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salted_fish_flag);
        ButterKnife.bind(this);

        initToolbar();

        if(SharedPreferencesUtils.getInstance().getBoolean("has_flag", false)){
            saltedFishImageView.setVisibility(View.INVISIBLE);
            mRecyclerView.setVisibility(View.VISIBLE);
        } else{
            saltedFishImageView.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.INVISIBLE);
        }
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
