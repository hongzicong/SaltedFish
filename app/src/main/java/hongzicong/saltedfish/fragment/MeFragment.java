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
import hongzicong.saltedfish.adapter.MeAdapter;
import hongzicong.saltedfish.utils.UIUtils;

public class MeFragment extends Fragment {

    private Unbinder mUnbinder;
    private MeAdapter meAdapter =new MeAdapter();

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    public static MeFragment newInstance(){
        MeFragment meFragment =new MeFragment();
        return meFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_set, container, false);
        mUnbinder= ButterKnife.bind(this,v);

        initToolbar();

        recyclerView.setAdapter(meAdapter);
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