package com.xprinter.newsplan.homepage;

import com.xprinter.newsplan.BasePresenter;
import com.xprinter.newsplan.BaseView;

/**
 * Created by Charlie on 2017/3/10.
 */

public interface DoubanMomentContract {

    interface View extends BaseView<Presenter> {

        void startLoading();

        void stopLoading();

        void showLoadingError();

//        void showResults(ArrayList<DoubanMomentNews.posts> list);

    }

    interface Presenter extends BasePresenter {

        void startReading(int position);

        void loadPosts(long date, boolean clearing);

        void refresh();

        void loadMore(long date);

        void feelLucky();

    }
}
