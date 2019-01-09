package hongzicong.saltedfish.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import hongzicong.saltedfish.R;
import hongzicong.saltedfish.utils.UIUtils;
import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;
import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

public class AboutActivity extends AppCompatActivity {

    @BindView(R.id.relative_layout)
    RelativeLayout relativeLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.konfettiView)
    KonfettiView mKonfettiView;

    private int press_count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ButterKnife.bind(this);

        initToolbar();

        View aboutPage = new AboutPage(this)
                .isRTL(false)
                .setImage(R.drawable.salted_fish_2)
                .setDescription("快乐咸鱼每一天")
                .addItem(new Element().setTitle("Version 1.0").setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        press_count += 1;
                        if(press_count == 5){
                            Toast.makeText(AboutActivity.this, "热烈庆祝咸鱼彩蛋的发现！！！", Toast.LENGTH_LONG).show();
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
                        press_count = (press_count) % 5;
                    }
                }))
                .addGroup("与我联系")
                .addEmail("hongzc@mail2.sysu.edu.cn")
                .addWebsite("http://hongzicong.github.io")
                .addGitHub("hongzicong")
                .create();

        relativeLayout.addView(aboutPage);
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
