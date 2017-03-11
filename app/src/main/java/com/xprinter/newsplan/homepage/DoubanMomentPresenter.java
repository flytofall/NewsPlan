package com.xprinter.newsplan.homepage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.xprinter.newsplan.bean.StringModelImpl;

/**
 * Created by kylin on 2017/3/10.
 */

public class DoubanMomentPresenter implements DoubanMomentContract.Presenter {

    private Context context;
    private DoubanMomentContract.View view;
    private StringModelImpl model;

    private SQLiteDatabase db;

    public DoubanMomentPresenter(Context context, DoubanMomentContract.View view) {
        this.context = context;
        this.view = view;
        model=new StringModelImpl(context);

    }

    @Override
    public void startReading(int position) {

    }

    @Override
    public void loadPosts(long date, boolean clearing) {

    }

    @Override
    public void refresh() {

    }

    @Override
    public void loadMore(long date) {

    }

    @Override
    public void feelLucky() {

    }

    @Override
    public void start() {

    }
}
