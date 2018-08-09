package hongzicong.saltedfish.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import hongzicong.saltedfish.R;
import hongzicong.saltedfish.fragment.MeFragment;
import hongzicong.saltedfish.fragment.ShowFragment;
import hongzicong.saltedfish.fragment.TodoFragment;
import hongzicong.saltedfish.model.PersonalInfo;
import hongzicong.saltedfish.utils.NetUtil;

import static hongzicong.saltedfish.utils.NetUtil.LogIn;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar mBottomNavigationBar;

    private FragmentManager mFragmentManager;

    private MeFragment mMeFragment;
    private ShowFragment mShowFragment;
    private TodoFragment mTodoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initBottomNavigationBar();
        initFragmentManager();

        if(PersonalInfo.getPersonalInfo().hasRecord() && NetUtil.isNetworkAvailable(this)){
            LogIn(PersonalInfo.getPersonalInfo().getName(), PersonalInfo.getPersonalInfo().getPassword());
        }

        // open the introduction
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                //  Initialize SharedPreferences
                SharedPreferences getPrefs = PreferenceManager
                        .getDefaultSharedPreferences(getBaseContext());

                //  Create a new boolean and preference and set it to true
                boolean isFirstStart = getPrefs.getBoolean("firstStart", true);

                //  If the activity has never started before...
                if (isFirstStart) {

                    //  Launch app intro
                    final Intent i = new Intent(MainActivity.this, IntroActivity.class);

                    runOnUiThread(new Runnable() {
                        @Override public void run() {
                            startActivity(i);
                        }
                    });

                    //  Make a new preferences editor
                    SharedPreferences.Editor e = getPrefs.edit();

                    //  Edit preference to make it false because we don't want this to run again
                    e.putBoolean("firstStart", false);

                    //  Apply changes
                    e.apply();
                }
            }
        });

        // Start the thread
        t.start();

    }

    private void initFragmentManager(){
        mFragmentManager=this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=mFragmentManager.beginTransaction();
        mTodoFragment=TodoFragment.newInstance();
        fragmentTransaction.replace(R.id.member_layout,mTodoFragment);
        fragmentTransaction.commit();
    }

    protected void initBottomNavigationBar(){
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        mBottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.show_icon,"Show").setActiveColorResource(R.color.show_color))
                .addItem(new BottomNavigationItem(R.drawable.todo_icon,"TODO").setActiveColorResource(R.color.todo_color))
                .addItem(new BottomNavigationItem(R.drawable.set_icon,"Me").setActiveColorResource(R.color.set_color))
                .setFirstSelectedPosition(1)
                .initialise();
        mBottomNavigationBar.setAutoHideEnabled(false);
        mBottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener(){
            @Override
            public void onTabSelected(int position) {
                FragmentTransaction fragmentTransaction=mFragmentManager.beginTransaction();
                switch (position){
                    case 0:
                        if(mShowFragment==null){
                            mShowFragment=ShowFragment.newInstance();
                        }
                        fragmentTransaction.replace(R.id.member_layout,mShowFragment);
                        break;
                    case 1:
                        if(mTodoFragment==null){
                            mTodoFragment=TodoFragment.newInstance();
                        }
                        fragmentTransaction.replace(R.id.member_layout,mTodoFragment);
                        break;
                    case 2:
                        if(mMeFragment ==null){
                            mMeFragment = MeFragment.newInstance();
                        }
                        fragmentTransaction.replace(R.id.member_layout, mMeFragment);
                        break;
                    default:
                        break;
                }
                fragmentTransaction.commit();
            }

            @Override
            public void onTabReselected(int position) {

            }

            @Override
            public void onTabUnselected(int position) {

            }

        });
    }
}
