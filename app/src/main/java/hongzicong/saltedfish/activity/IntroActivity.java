package hongzicong.saltedfish.activity;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.WindowManager;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.github.paolorotolo.appintro.model.SliderPage;

import hongzicong.saltedfish.R;

public class IntroActivity extends AppIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  WindowManager.LayoutParams.FLAG_FULLSCREEN);

        SliderPage sliderPage = new SliderPage();
        sliderPage.setTitle("养成习惯 / 添加计划");
        sliderPage.setDescription("添加每日计划和长期习惯！\n坚决不做咸鱼！");
        sliderPage.setImageDrawable(R.drawable.empty_todo_page);
        sliderPage.setBgColor(Color.parseColor("#A5DEE4"));
        addSlide(AppIntroFragment.newInstance(sliderPage));

        sliderPage.setTitle("日积月累");
        sliderPage.setDescription("通过颜色的深浅看出每日的咸鱼程度");
        sliderPage.setImageDrawable(R.drawable.show_page);
        sliderPage.setBgColor(Color.parseColor("#B1B479"));
        addSlide(AppIntroFragment.newInstance(sliderPage));

        sliderPage.setTitle("咸鱼功能集合");
        sliderPage.setDescription("包含立Flag、咸鱼广场、更换风格等多项没用的功能");
        sliderPage.setImageDrawable(R.drawable.me_page_unlogin);
        sliderPage.setBgColor(Color.parseColor("#DC9FB4"));
        addSlide(AppIntroFragment.newInstance(sliderPage));

    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        // Do something when users tap on Skip button.
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        finish();
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
        // Do something when the slide changes.
    }

}
