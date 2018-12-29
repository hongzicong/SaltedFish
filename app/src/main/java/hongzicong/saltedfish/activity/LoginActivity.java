package hongzicong.saltedfish.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import hongzicong.saltedfish.R;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import hongzicong.saltedfish.fragment.ForgetPasswordFragment;
import hongzicong.saltedfish.fragment.LoginFragment;
import hongzicong.saltedfish.fragment.RegisterFragment;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.first_view_pager)
    ViewPager mViewPager;

    private PagerAdapter mPagerAdapter;
    private List<Fragment> mFragments;
    private ForgetPasswordFragment forgetPasswordFragment;
    private LoginFragment loginFragment;
    private RegisterFragment registerFragment;

    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mUnbinder= ButterKnife.bind(this);

        if(mFragments==null){
            mFragments=new ArrayList<>();
            mFragments.add(ForgetPasswordFragment.newInstance());

            mFragments.add(LoginFragment.newInstance());

            mFragments.add(RegisterFragment.newInstance());
        }

        FragmentManager fragmentManager=getSupportFragmentManager();
        mPagerAdapter=new PagerAdapter(fragmentManager);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(getPageChangeListener());

        mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        mViewPager.setCurrentItem(1);

    }

    private ViewPager.OnPageChangeListener getPageChangeListener() {
        return new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };
    }


    public class PagerAdapter extends FragmentPagerAdapter {

        public PagerAdapter(FragmentManager fragmentManager){
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    if (forgetPasswordFragment == null) {
                        forgetPasswordFragment = ForgetPasswordFragment.newInstance();
                    }
                    return forgetPasswordFragment;
                case 1:
                    if (loginFragment == null) {
                        loginFragment = LoginFragment.newInstance();
                    }
                    return loginFragment;
                case 2:
                    if (registerFragment == null) {
                        registerFragment = RegisterFragment.newInstance();
                    }
                    return registerFragment;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

    }


}
