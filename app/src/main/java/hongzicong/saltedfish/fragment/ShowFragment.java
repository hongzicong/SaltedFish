package hongzicong.saltedfish.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import hongzicong.saltedfish.R;
import hongzicong.saltedfish.adapter.TableViewAdapter;
import hongzicong.saltedfish.model.DateDatas;
import hongzicong.saltedfish.utils.EveryDayDaoUtil;
import hongzicong.saltedfish.utils.OneDayDaoUtil;
import hongzicong.saltedfish.utils.UIUtils;
import hongzicong.saltedfish.view.TableView;

import static hongzicong.saltedfish.utils.Util.getTotalDayNum;

public class ShowFragment extends Fragment {

    private EveryDayDaoUtil everyDayDaoUtil=new EveryDayDaoUtil(UIUtils.getContext());
    private OneDayDaoUtil oneDayDaoUtil=new OneDayDaoUtil(UIUtils.getContext());

    private int totalDayNum;
    private DateDatas dateDatas;

    private Unbinder mUnbinder;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.table_view)
    TableView mTableView;

    @BindView(R.id.text_view_total_day)
    TextView totalDay;

    @BindView(R.id.text_view_total_num)
    TextView totalNum;


    public static ShowFragment newInstance() {
        ShowFragment fragment = new ShowFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_show, container, false);
        mUnbinder= ButterKnife.bind(this,v);

        totalDayNum=getTotalDayNum();
        dateDatas=new DateDatas(totalDayNum,everyDayDaoUtil.queryAllEveryDayTask(),oneDayDaoUtil.queryAllOneDayTask());

        initToolbar();
        initTableView();

        totalDay.setText(totalDayNum+" days");
        totalNum.setText(dateDatas.getTotalNum()+" total");
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

    private void initTableView(){
        TableViewAdapter tableViewAdapter=new TableViewAdapter(dateDatas);
        tableViewAdapter.setCurrentDay(totalDayNum);
        mTableView.setAdapter(tableViewAdapter);
    }

}
