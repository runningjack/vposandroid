package com.vpos.amedora.vpos.helper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Amedora on 9/1/2015.
 */
public class AppStatus {
    private static  AppStatus instance = new AppStatus();
    static Context context;
    ConnectivityManager connectivityManager;
    NetworkInfo wifiInfo, mobileInfo;
    boolean connected = false;

    public static AppStatus getInstance(Context ctx){
        context = ctx.getApplicationContext();
        return instance;
    }

    public boolean isOnline(){
        try{
            connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            connected = networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
            return connected;
        }catch (Exception e){
            System.out.println("CheckConnectivity Exception: " + e.getMessage());
        }

        return connected;
    }

}
