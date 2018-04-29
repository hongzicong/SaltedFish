package hongzicong.saltedfish.activity;

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
import hongzicong.saltedfish.fragment.SetFragment;
import hongzicong.saltedfish.fragment.ShowFragment;
import hongzicong.saltedfish.fragment.TodoFragment;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar mBottomNavigationBar;

    private FragmentManager mFragmentManager;

    private SetFragment mSetFragment;
    private ShowFragment mShowFragment;
    private TodoFragment mTodoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
        localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);

        ButterKnife.bind(this);

        initBottomNavigationBar();
        initFragmentManager();

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
                        if(mSetFragment==null){
                            mSetFragment=SetFragment.newInstance();
                        }
                        fragmentTransaction.replace(R.id.member_layout,mSetFragment);
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
