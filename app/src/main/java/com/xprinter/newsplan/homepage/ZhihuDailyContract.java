package com.xprinter.newsplan.homepage;

import com.xprinter.newsplan.BasePresenter;
import com.xprinter.newsplan.BaseView;
import com.xprinter.newsplan.bean.ZhihuDailyNews;

import java.util.ArrayList;

/**
 * Created by Charlie on 2017/3/10.
 */

public interface ZhihuDailyContract {

    interface View extends BaseView<Presenter> {
        //显示加载或其他错误
        void showError();
        //显示正在加载
        void showLoading();
        //成功获取数据后在界面显示
        void showResults(ArrayList<ZhihuDailyNews.Question> list);
        // 显示用于加载指定日期的date picker dialog
        void showPickDialog();
        //停止加载
        void stopLoading();


    }

    interface Presenter extends BasePresenter{

        //请求数据
        void loadPost(long date, boolean cleanning);
        //刷新数据
        void refresh();
        //加载更多文章
        void loadMore(long data);
        //显示详情
        void startReading(int data);
        //随便看看
        void feelLuck();
    }

}
