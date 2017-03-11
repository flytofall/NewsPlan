package com.xprinter.newsplan.homepage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


import com.google.gson.Gson;
import com.xprinter.newsplan.bean.StringModelImpl;
import com.xprinter.newsplan.utils.DateFormatter;

/**
 * Created by Charlie on 2017/3/10.
 */

public class ZhihuDailyPresenter implements ZhihuDailyContract.Presenter {

    private Context context;
    private ZhihuDailyContract.View view;
    private StringModelImpl model;

    private DateFormatter formatter=new DateFormatter();
    private Gson gson=new Gson();


    private SQLiteDatabase db;

    public ZhihuDailyPresenter(Context context,ZhihuDailyContract.View view) {
        this.context=context;
        this.view=view;
        this.view.setPresenter(this);
        model=new StringModelImpl(context);

    }

    @Override
    public void loadPost(long date, boolean cleanning) {

    }

    @Override
    public void refresh() {

    }

    @Override
    public void loadMore(long data) {

    }

    @Override
    public void startReading(int data) {

    }

    @Override
    public void feelLuck() {

    }

    @Override
    public void start() {

    }
}
