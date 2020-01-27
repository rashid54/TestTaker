package com.tutor.testtaker;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Build;

import androidx.annotation.RequiresApi;

public class Utils {
    private static final String TAG = "Utils";
    private static String DOMAIN= "https://truetest.herokuapp.com/api/";

    public static String getDOMAIN() {
        return DOMAIN;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static boolean isOnline(Context context) {
        ConnectivityManager connectivityManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        Network network= connectivityManager.getActiveNetwork();
        if(network!=null){
            NetworkCapabilities networkCapabilities= connectivityManager.getNetworkCapabilities(network);
            return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET);
        }
        else {
            return false;
        }
    }
}
