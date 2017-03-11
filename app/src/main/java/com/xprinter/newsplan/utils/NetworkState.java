package com.xprinter.newsplan.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Charlie on 2017/3/11.
 * * judge the network state, whether the network is connected
 * 判断当前的网络状态，是否有网络连接
 * WiFi或者是移动数据
 * or is wifi or mobile data
 */

public class NetworkState {

    // 检查是否连接到网络
    // whether connect to internet
    public static boolean networkConnect(Context context){
        if (context!=null){
            ConnectivityManager manager=(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info=manager.getActiveNetworkInfo();
            if (info!=null)
                return info.isAvailable();
        }
        return false;
    }

    // 检查WiFi是否连接
    public static boolean wifiConnect(Context context){
        if (context!=null){
            ConnectivityManager manager=(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info =manager.getActiveNetworkInfo();
            if (info!=null){
                if (info.getType()==ConnectivityManager.TYPE_WIFI);
                return info.isAvailable();
            }
        }
        return false;
    }
    // 检查移动网络是否连接
    public static boolean mobileDataConnect(Context context){
        if (context!=null){
            ConnectivityManager manager=(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info=manager.getActiveNetworkInfo();
            if (info!=null){
                if (info.getType()==ConnectivityManager.TYPE_MOBILE);
                return info.isAvailable();
            }
        }
        return false;
    }
}
