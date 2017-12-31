package hongzicong.saltedfish.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import hongzicong.saltedfish.R;

public class TodoFragment extends Fragment {

    private Unbinder mUnbinder;

    public TodoFragment() {
        // Required empty public constructor
    }

    public static TodoFragment newInstance(){
        TodoFragment todoFragment=new TodoFragment();
        return todoFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_todo, container, false);
        mUnbinder= ButterKnife.bind(this,v);
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }


}
