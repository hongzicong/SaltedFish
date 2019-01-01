package hongzicong.saltedfish.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import hongzicong.saltedfish.R;
import hongzicong.saltedfish.adapter.FlagAdapter;
import hongzicong.saltedfish.model.Flag;
import hongzicong.saltedfish.utils.SharedPreferencesUtils;

import hongzicong.saltedfish.utils.UIUtils;
import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

public class SaltedFishFlagActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.salted_fish_image_view)
    ImageView saltedFishImageView;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.new_fab)
    FloatingActionButton newFAB;

    @BindView(R.id.delete_fab)
    FloatingActionButton deleteFAB;

    @BindView(R.id.konfettiView)
    KonfettiView mKonfettiView;

    private FlagAdapter mFlagAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salted_fish_flag);
        ButterKnife.bind(this);

        initToolbar();

        initLayout();

        initClickListener();

        if(SharedPreferencesUtils.getInstance().getBoolean("has_flag", false)){
            saltedFishImageView.setVisibility(View.INVISIBLE);
            mRecyclerView.setVisibility(View.VISIBLE);
            deleteFAB.setVisibility(View.VISIBLE);
        } else{
            saltedFishImageView.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.INVISIBLE);
            deleteFAB.setVisibility(View.INVISIBLE);
        }
    }

    private void initClickListener(){

        deleteFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        newFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mKonfettiView.build()
                        .addColors(UIUtils.getColor(R.color.konfetti_1),
                                UIUtils.getColor(R.color.konfetti_2),
                                UIUtils.getColor(R.color.konfetti_3),
                                UIUtils.getColor(R.color.konfetti_4))
                        .setDirection(0.0, 359.0)
                        .setSpeed(1f, 5f)
                        .setFadeOutEnabled(true)
                        .setTimeToLive(1500L)
                        .addShapes(Shape.RECT, Shape.CIRCLE)
                        .addSizes(new Size(12, 5))
                        .setPosition(-50f, mKonfettiView.getWidth() + 50f, -50f, -50f)
                        .streamFor(300, 5000L);
            }
        });
    }

    private void initLayout(){
        mFlagAdapter = new FlagAdapter(new ArrayList<Flag>(), new ArrayList<Flag>());
        mRecyclerView.setAdapter(mFlagAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
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
