package com.xprinter.newsplan.homepage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by kylin on 2017/3/10.
 */

public class DoubanMomentFragment extends Fragment implements DoubanMomentContract.View {

    public DoubanMomentFragment() {
    }

    public static DoubanMomentFragment newInstance(){
        return new DoubanMomentFragment();
    }

    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return null;
    }

    @Override
    public void startLoading() {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showLoadingError() {

    }

    @Override
    public void setPresenter(DoubanMomentContract.Presenter presenter) {

    }

    @Override
    public void initViews(View view) {

    }
}
