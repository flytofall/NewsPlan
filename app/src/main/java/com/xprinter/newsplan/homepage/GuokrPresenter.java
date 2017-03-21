package com.xprinter.newsplan.homepage;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.xprinter.newsplan.R;
import com.xprinter.newsplan.bean.GuokrHandpickNews;
import com.xprinter.newsplan.bean.StringModelImpl;
import com.xprinter.newsplan.db.DatabaseHelper;
import com.xprinter.newsplan.interfaze.OnStringListener;
import com.xprinter.newsplan.service.CacheService;
import com.xprinter.newsplan.utils.Api;
import com.xprinter.newsplan.utils.NetworkState;

import java.util.ArrayList;

/**
 * Created by charlie on 2017/3/10.
 */

public class GuokrPresenter implements GuokrContract.Presenter {

    private Context context;
    private GuokrContract.View view;

    private StringModelImpl model;
    private SQLiteDatabase db;

    private DatabaseHelper dbHelper;

    private ArrayList<GuokrHandpickNews.result> list=new ArrayList<GuokrHandpickNews.result>();
    private Gson gson=new Gson();

    private static final String TAG="GUOKRPRESENTER.CLASS";


    public GuokrPresenter(Context context, GuokrContract.View view) {
        this.context = context;
        this.view = view;
        view.setPresenter(this);
        model=new StringModelImpl(context);
        dbHelper=new DatabaseHelper(context,"History.db", null, 5);
        db=dbHelper.getWritableDatabase();
    }

    @Override
    public void loadPost() {

        view.showLoading();

        if (NetworkState.networkConnect(context)){
            model.load(Api.GUOKR_ARTICLES, new OnStringListener() {
                @Override
                public void onSuccess(String result) {
                    // 由于果壳并没有按照日期加载的api
                    // 所以不存在加载指定日期内容的操作，当要请求数据时一定是在进行刷新
                    list.clear();
                    try{
                        GuokrHandpickNews question=gson.fromJson(result,GuokrHandpickNews.class);
                        for (GuokrHandpickNews.result re:question.getResult()){
                            list.add(re);
                            if (!queryIfIDExists(re.getId())){
                                try{
                                    db.beginTransaction();;
                                    ContentValues values=new ContentValues();
                                    values.put("guokr_id", re.getId());
                                    values.put("guokr_news", gson.toJson(re));
                                    values.put("guokr_content", "");
                                    values.put("guokr_time", (long)re.getDate_picked());
                                    db.insert("Guokr",null,values);
                                    values.clear();
                                    db.setTransactionSuccessful();
                                }catch (Exception e){
                                    e.printStackTrace();
                                    Log.e(TAG,R.string.save_tosql_erre+"");
                                }finally {
                                    db.endTransaction();
                                }

                            }
                            Intent intent = new Intent(Api.LOCAL_BROADCAST);
                            intent.putExtra("type", CacheService.TYPE_GUOKR);
                            intent.putExtra("id", re.getId());
                            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);


                        }

                        view.showResults(list);

                    }catch (Exception e){
                        e.printStackTrace();
                        Log.e(TAG,"");
                        view.showError();
                    }
                    view.stopLoading();
                }

                @Override
                public void onError(VolleyError error) {
                    view.stopLoading();
                    view.showError();

                }
            });

        }else {
            Cursor cursor=db.query("Guokr", null, null, null, null, null, null);
            if (cursor.moveToFirst()){
                do {
                    GuokrHandpickNews.result result=gson.fromJson(
                            cursor.getString(cursor.getColumnIndex("guokr_news")), GuokrHandpickNews.result.class);
                    list.add(result);
                }while (cursor.moveToNext());
            }
            cursor.close();;
            view.stopLoading();
            view.showResults(list);

            //当第一次安装应用，并且没网的时候
            if (list.isEmpty()){
                view.showError();
//                Snackbar.make(view,R.string.please_check_network, Snackbar.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public void refresh() {
        loadPost();
    }

    @Override
    public void loadMore(long data) {

    }

    @Override
    public void startReading(int data) {
        GuokrHandpickNews.result item=list.get(data);


    }

    @Override
    public void feelLuck() {

    }

    @Override
    public void start() {
        loadPost();

    }
    private boolean queryIfIDExists(int id){
        Cursor cursor=db.query("Guokr",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                if (id==cursor.getInt(cursor.getColumnIndex("guokr_id")));return true;

            }while (cursor.moveToNext());
        }
        return false;
    }
}
