package com.xprinter.newsplan;


import android.view.View;

/**
 * Created by Charlie on 2017/3/10.
 */

public interface BaseView <T>{

    /**
     * set the presenter of mvp
     * @param presenter
     */
    // 为View设置Presenter
    void setPresenter(T presenter);

    /**
     * init the views of fragment
     * @param view
     */
    // 初始化界面控件
    void initViews(View view);
}
