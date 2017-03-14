package com.xprinter.newsplan.bookmarks;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.xprinter.newsplan.R;
import com.xprinter.newsplan.adpter.BookmarksAdapter;
import com.xprinter.newsplan.bean.DoubanMomentNews;
import com.xprinter.newsplan.bean.GuokrHandpickNews;
import com.xprinter.newsplan.bean.ZhihuDailyNews;

import java.util.ArrayList;

/**
 * Created by kylin on 2017/3/13.
 */

public class BookmarkFragment extends Fragment implements BookmarkContract.View {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    private BookmarksAdapter adapter;
    private BookmarkContract.Presenter presenter;




    public BookmarkFragment() {
    }

    public static BookmarkFragment newInstance(){
        return new BookmarkFragment();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_list,container,false);
        initViews(view);

        setHasOptionsMenu(true);

        presenter.loadResult(false);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadResult(true);
            }
        });
        return super.onCreateView(inflater, container, savedInstanceState);
    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_bookmarks,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
//            startActivity(new Intent(getActivity(), SearchActivity.class));// TODO: 2017/3/14 收藏的搜索界面 
        } else if (id == R.id.action_feel_lucky) {
            presenter.feelLucky();
        }
        return true;
    }

    @Override
    public void showResult(ArrayList<ZhihuDailyNews.Question> zhihulist,
                           ArrayList<GuokrHandpickNews.result> guokrlist,
                           ArrayList<DoubanMomentNews.posts> doubanlist,
                           ArrayList<Integer> types) {
//        if(adapter==null){
//            adapter=new BookmarksAdapter(getActivity(),zhihulist,guokrlist,doubanlist,types);
//        }
        // TODO: 2017/3/14 收藏界面显示

    }

    @Override
    public void notifyDataChanged() {
        presenter.loadResult(true);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showLoading() {
        refreshLayout.setRefreshing(true);

    }

    @Override
    public void stopLoading() {
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void setPresenter(BookmarkContract.Presenter presenter) {
        if (presenter==null){
            this.presenter=presenter;
        }
    }

    @Override
    public void initViews(View view) {
        recyclerView= (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        refreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.refreshLayout);
        refreshLayout.setColorSchemeResources(R.color.colorPrimary);
    }
}
