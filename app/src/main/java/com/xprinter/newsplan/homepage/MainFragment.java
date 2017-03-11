package com.xprinter.newsplan.homepage;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xprinter.newsplan.adpter.MainPagerAdapter;

/**
 * Created by kylin on 2017/3/11.
 */

public class MainFragment extends Fragment {

    private Context context;
    private MainPagerAdapter mainPagerAdapter;
    private TabLayout tabLayout;

    private ZhihuDailyFragment zhihuDailyFragment;
    private GuokrFragment guokrFragment;
    private DoubanMomentFragment doubanMomentFragment;

    private ZhihuDailyPresenter zhihuDailyPresenter;
    private GuokrPresenter guokrPresenter;
    private DoubanMomentPresenter doubanMomentPresenter;

    public MainFragment() {}

    public static MainFragment newInstance(){
        return new MainFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context=getActivity();
        //恢复Fragment状态
        if (savedInstanceState!=null){
            FragmentManager manager=getChildFragmentManager();
            zhihuDailyFragment= (ZhihuDailyFragment) manager.getFragment(savedInstanceState,"zhihu");
            guokrFragment= (GuokrFragment) manager.getFragment(savedInstanceState,"guokr");
            doubanMomentFragment= (DoubanMomentFragment) manager.getFragment(savedInstanceState,"douban");

        }else {
            //创建实例
            zhihuDailyFragment=zhihuDailyFragment.newInstence();
            guokrFragment=GuokrFragment.newInstance();
            doubanMomentFragment=DoubanMomentFragment.newInstance();
        }
        //创建presenter实例
        zhihuDailyPresenter=new ZhihuDailyPresenter(context,zhihuDailyFragment);
        guokrPresenter=new GuokrPresenter(context,guokrFragment);
        doubanMomentPresenter=new DoubanMomentPresenter(context,doubanMomentFragment);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
