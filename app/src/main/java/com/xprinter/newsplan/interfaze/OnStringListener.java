package com.xprinter.newsplan.interfaze;

import com.android.volley.VolleyError;

/**
 * Created by Charlie on 2017/3/10.
 */

public interface OnStringListener  {

    /**
     * 请求成功时的回调
     * @param result
     */
    void onSuccess(String result);


    /**
     * 请求失败回调
     * @param error
     */
    void onError(VolleyError error);


}
