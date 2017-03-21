package com.xprinter.newsplan.homepage;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;


import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.xprinter.newsplan.R;
import com.xprinter.newsplan.bean.StringModelImpl;
import com.xprinter.newsplan.bean.ZhihuDailyNews;
import com.xprinter.newsplan.db.DatabaseHelper;
import com.xprinter.newsplan.interfaze.OnStringListener;
import com.xprinter.newsplan.service.CacheService;
import com.xprinter.newsplan.utils.Api;
import com.xprinter.newsplan.utils.DateFormatter;
import com.xprinter.newsplan.utils.NetworkState;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Charlie on 2017/3/10.
 */

public class ZhihuDailyPresenter implements ZhihuDailyContract.Presenter {

    private Context context;
    private ZhihuDailyContract.View view;
    private StringModelImpl model;

    private DateFormatter formatter=new DateFormatter();
    private Gson gson=new Gson();

    private ArrayList<ZhihuDailyNews.Question> list=new ArrayList<ZhihuDailyNews.Question>();
    private DatabaseHelper dbhelper;
    private SQLiteDatabase db;

    public ZhihuDailyPresenter(Context context,ZhihuDailyContract.View view) {
        this.context=context;
        this.view=view;
        this.view.setPresenter(this);
        model=new StringModelImpl(context);
        dbhelper=new DatabaseHelper(context,"History.db",null,5);
        db=dbhelper.getWritableDatabase();

    }

    @Override
    public void loadPost(long date, final boolean cleanning) {
        if (cleanning){
            view.showLoading();
        }
        if (NetworkState.networkConnect(context)){
            model.load(Api.ZHIHU_HISTORY + formatter.ZhihuDailyDateFormat(date), new OnStringListener() {
                @Override
                public void onSuccess(String result) {
                    try{
                        ZhihuDailyNews post=gson.fromJson(result,ZhihuDailyNews.class);
                        ContentValues values=new ContentValues();
                        if (cleanning){
                            list.clear();
                        }
                        for (ZhihuDailyNews.Question item:post.getStories()){
                            list.add(item);
                            //存储数据到sql
                            if(!queryifIDExists(item.getId())){
                                db.beginTransaction();
                                try{
                                    DateFormat format=new SimpleDateFormat("yyyyMMdd");
                                    Date date=format.parse(post.getDate());
                                    values.put("zhihu_id",item.getId());
                                    values.put("zhihu_news",gson.toJson(item));
                                    values.put("zhihu_content","");
                                    values.put("zhihu_time",date.getTime()/1000);
                                    db.insert("zhihu",null,values);
                                    values.clear();
                                    db.setTransactionSuccessful();
                                }catch (Exception e){
                                    e.printStackTrace();
                                    Log.e("ZHIHU_SQL", "ZHIHU_SQL: "+R.string.save_tosql_erre);
                                }finally {
                                    db.endTransaction();
                                }
                            }
                            //发广播来去让cacheseice去保存content的内容
                            //因为内容很多，在上面操作会阻塞线程需要另起服务来保存
                            Intent intent =new Intent(Api.LOCAL_BROADCAST);
                            intent.putExtra("type", CacheService.TYPE_ZHIHU);
                            intent.putExtra("id",item.getId());
                            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                        }
                        view.showResults(list);
                    }catch (JsonIOException e){
                        view.showError();
                    }
                }

                @Override
                public void onError(VolleyError error) {
                    view.showError();
                    view.stopLoading();
                }
            });
        }else {

            if (cleanning){
                list.clear();

                Cursor cursor=db.query("zhihu",null,null,null,null,null,null);
                if (cursor.moveToFirst()){
                    do {
                        ZhihuDailyNews.Question question=gson.fromJson(
                                cursor.getString(cursor.getColumnIndex("zhihu_news"))
                                , ZhihuDailyNews.Question.class);
                        list.add(question);
                    }while (cursor.moveToNext());
                }
                cursor.close();
                view.stopLoading();
                view.showResults(list);
            }else {
                view.showError();
            }
        }


    }
    //查找数据库是否有了此id
    private boolean queryifIDExists(int id){
        Cursor cursor=db.query("zhihu",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                if (id==cursor.getInt(cursor.getColumnIndex("zhihu_id")));
                return true;
            }while (cursor.moveToNext());
        }
        cursor.close();
        return false;
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
