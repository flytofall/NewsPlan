package com.xprinter.newsplan.homepage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xprinter.newsplan.R;
import com.xprinter.newsplan.adpter.GuokrNewsAdapter;
import com.xprinter.newsplan.bean.GuokrHandpickNews;
import com.xprinter.newsplan.interfaze.OnRecyclerViewOnClickListener;

import java.util.ArrayList;

/**
 * Created by kylin on 2017/3/10.
 */

public class GuokrFragment extends Fragment implements GuokrContract.View {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;

    private GuokrNewsAdapter adapter;
    private GuokrContract.Presenter presenter;

    public GuokrFragment() {
    }

    public static GuokrFragment newInstance(){
        return new GuokrFragment();
    }

    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_list,container,false);
        initViews(view);

        presenter.start();

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.refresh();
            }
        });


        return null;
    }

    @Override
    public void showError() {
        Snackbar.make(refreshLayout,R.string.loaded_failed,Snackbar.LENGTH_SHORT)
                .setAction(R.string.retry, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.refresh();
            }
        }).show();
    }

    @Override
    public void showLoading() {
        refreshLayout.setRefreshing(true);
    }

    @Override
    public void showResults(ArrayList<GuokrHandpickNews.result> list) {
        if (adapter!=null){
            adapter=new GuokrNewsAdapter(getContext(),list);
            adapter.setItemClickListener(new OnRecyclerViewOnClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    presenter.startReading(position);
                }
            });
            recyclerView.setAdapter(adapter);
        }else {
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    public void showPickDialog() {

    }

    @Override
    public void stopLoading() {
        refreshLayout.setRefreshing(false);

    }

    @Override
    public void setPresenter(GuokrContract.Presenter presenter) {
        this.presenter=presenter;

    }

    @Override
    public void initViews(View view) {

        recyclerView= (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        refreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.refreshLayout);
        //设置下拉刷新时的颜色
        refreshLayout.setColorSchemeResources(R.color.colorPrimary);

    }
}
