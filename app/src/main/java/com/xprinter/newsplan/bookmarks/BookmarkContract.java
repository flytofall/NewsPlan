package com.xprinter.newsplan.bookmarks;

import com.xprinter.newsplan.BasePresenter;
import com.xprinter.newsplan.BaseView;
import com.xprinter.newsplan.bean.DoubanMomentNews;
import com.xprinter.newsplan.bean.GuokrHandpickNews;
import com.xprinter.newsplan.bean.ZhihuDailyNews;

import java.util.ArrayList;

/**
 * Created by kylin on 2017/3/13.
 */

public interface BookmarkContract  {

    interface View extends BaseView<Presenter>{
        void showResult(ArrayList<ZhihuDailyNews.Question> zhihulist,
                        ArrayList<GuokrHandpickNews.result> guokrlist,
                        ArrayList<DoubanMomentNews.posts> doubanlist,
                        ArrayList<Integer> type);
        void notifyDataChanged();
        void showLoading();
        void stopLoading();
    }

    interface Presenter extends BasePresenter{
        void loadResult(boolean refresh);
        void startReading();
        void checkForFreshData();
        void feelLucky();


    }

}
