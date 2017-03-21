package com.xprinter.newsplan.homepage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xprinter.newsplan.R;
import com.xprinter.newsplan.adpter.ZhihuDailyNewsAdapter;
import com.xprinter.newsplan.bean.ZhihuDailyNews;
import com.xprinter.newsplan.interfaze.OnRecyclerViewOnClickListener;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Charlie on 2017/3/10.
 */

public class ZhihuDailyFragment extends Fragment implements ZhihuDailyContract.View {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout refresh;
    private FloatingActionButton fab;
    private TabLayout tabLayout;

    private ZhihuDailyNewsAdapter adapter;

    private int mYear= Calendar.getInstance().get(Calendar.YEAR);
    private int mMonth= Calendar.getInstance().get(Calendar.MONTH);
    private int mDay=Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

    private ZhihuDailyContract.Presenter presenter;


    public ZhihuDailyFragment() {
    }

    public static ZhihuDailyFragment newInstence(){
        return new ZhihuDailyFragment();
    }

    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_list,container,false);
        initViews(view);
        return null;
    }

    @Override
    public void setPresenter(ZhihuDailyContract.Presenter presenter) {

    }

    @Override
    public void initViews(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        refresh = (SwipeRefreshLayout) view.findViewById(R.id.refreshLayout);
        //设置下拉刷新的按钮的颜色
        refresh.setColorSchemeResources(R.color.colorPrimary);

        fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.setRippleColor(getResources().getColor(R.color.colorPrimaryDark));

        tabLayout = (TabLayout) getActivity().findViewById(R.id.tab_layout);
    }

    @Override
    public void showError() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showResults(ArrayList<ZhihuDailyNews.Question> list) {
        if (adapter==null){
            adapter=new ZhihuDailyNewsAdapter(getContext(),list);
            adapter.setItemClickListener(new OnRecyclerViewOnClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    presenter.startReading(position);
                }
            });
            recyclerView.setAdapter(adapter);
        }
        else {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showPickDialog() {

    }

    @Override
    public void stopLoading() {

    }


}
