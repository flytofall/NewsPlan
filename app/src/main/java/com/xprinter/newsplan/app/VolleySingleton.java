package com.xprinter.newsplan.app;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * volley单例模式
 * Created by Charlie on 2017/3/10.
 */

public class VolleySingleton {
    private static VolleySingleton volleySingleton;
    private RequestQueue requestQueue;

    private VolleySingleton(Context context){
        requestQueue= Volley.newRequestQueue(context.getApplicationContext());
    }

    public static synchronized VolleySingleton getVolleySingleton(Context context){
        if (volleySingleton==null)volleySingleton=new VolleySingleton(context);
        return volleySingleton;
    }

    public RequestQueue getRequestQueue() {
        return this.requestQueue;
    }

    public <T> void addToRequsetQueue(Request<T> req){
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);

    }
}
