package com.xprinter.newsplan.homepage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.xprinter.newsplan.bean.StringModelImpl;

/**
 * Created by kylin on 2017/3/10.
 */

public class GuokrPresenter implements GuokrContract.Presenter {

    private Context context;
    private GuokrContract.View view;

    private StringModelImpl model;
    private SQLiteDatabase db;


    public GuokrPresenter(Context context, GuokrContract.View view) {
        this.context = context;
        this.view = view;
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
