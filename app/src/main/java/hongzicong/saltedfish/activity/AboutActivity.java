package hongzicong.saltedfish.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import hongzicong.saltedfish.R;
import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class AboutActivity extends AppCompatActivity {

    @BindView(R.id.relative_layout)
    RelativeLayout relativeLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

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
                .addItem(new Element().setTitle("Version 1.0"))
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
