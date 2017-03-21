package com.xprinter.newsplan.homepage;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.gson.Gson;
import com.xprinter.newsplan.bean.DoubanMomentNews;
import com.xprinter.newsplan.bean.StringModelImpl;
import com.xprinter.newsplan.db.DatabaseHelper;

import java.util.ArrayList;

/**
 * Created by charlie on 2017/3/10.
 */

public class DoubanMomentPresenter implements DoubanMomentContract.Presenter {

    private Context context;
    private DoubanMomentContract.View view;
    private StringModelImpl model;

    private SQLiteDatabase db;
    private DatabaseHelper dbHeler;

    private Gson gson=new Gson();
    private ArrayList<DoubanMomentNews.posts> list=new ArrayList<DoubanMomentNews.posts>();

    public DoubanMomentPresenter(Context context, DoubanMomentContract.View view) {
        this.context = context;
        this.view = view;
        model=new StringModelImpl(context);
        dbHeler=new DatabaseHelper(context,"History.db",null,5);
        db=dbHeler.getWritableDatabase();

    }

    @Override
    public void startReading(int position) {
        DoubanMomentNews.posts item=list.get(position);
        Intent intent=new Intent();



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
