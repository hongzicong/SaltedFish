package hongzicong.saltedfish.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.cazaea.sweetalert.SweetAlertDialog;
import com.github.clans.fab.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import hongzicong.saltedfish.R;
import hongzicong.saltedfish.adapter.FlagAdapter;
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

        updateLayout();

    }

    private void updateLayout(){
        if(SharedPreferencesUtils.getInstance().getBoolean("has_flag", false)){
            saltedFishImageView.setVisibility(View.INVISIBLE);
            mRecyclerView.setVisibility(View.VISIBLE);
            deleteFAB.setVisibility(View.VISIBLE);
        } else{
            saltedFishImageView.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.INVISIBLE);
            deleteFAB.setVisibility(View.INVISIBLE);
        }
        mFlagAdapter.notifyDataSetChanged();
    }

    private void initClickListener(){

        deleteFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new SweetAlertDialog(SaltedFishFlagActivity.this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("这些Flag要被你删除了")
                        .setContentText("这个操作是无法恢复的！")
                        .setConfirmText("一意孤行")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.setTitleText("删除")
                                        .setContentText("已被删除成功！")
                                        .setConfirmText("真香")
                                        .setConfirmClickListener(null)
                                        .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);

                                SharedPreferencesUtils.getInstance().putBoolean("has_flag", false);
                                SharedPreferencesUtils.getInstance().putString("prob_flag", "");
                                SharedPreferencesUtils.getInstance().putString("likely_flag", "");
                                updateLayout();
                            }
                        })
                        .show();

            }
        });

        newFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SaltedFishFlagActivity.this, NewFlagActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    private void initLayout(){
        mFlagAdapter = new FlagAdapter();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            if(resultCode == 2){
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
                updateLayout();
            }
        }
    }
}
