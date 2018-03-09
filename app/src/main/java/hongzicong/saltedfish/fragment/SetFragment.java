package hongzicong.saltedfish.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import hongzicong.saltedfish.R;
import hongzicong.saltedfish.adapter.SettingAdapter;
import hongzicong.saltedfish.utils.UIUtils;

public class SetFragment extends Fragment {

    private Unbinder mUnbinder;
    private SettingAdapter settingAdapter=new SettingAdapter();

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    public static SetFragment newInstance(){
        SetFragment setFragment=new SetFragment();
        return setFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_set, container, false);
        mUnbinder= ButterKnife.bind(this,v);

        initToolbar();

        recyclerView.setAdapter(settingAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(UIUtils.getContext()));

        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    private void initToolbar(){
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

}