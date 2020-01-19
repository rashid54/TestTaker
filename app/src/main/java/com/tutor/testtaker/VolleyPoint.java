package com.tutor.testtaker;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class VolleyPoint {
    private static final String TAG = "VolleyPoint";

    private String str;

    public VolleyPoint() {
        //gson= new Gson();
    }

    public static String getAuthToken(String username, String password, Context context){
        Log.d(TAG, "getAuthToken: started");
        Gson gson= new Gson();
        String url= "https://presslu1.pythonanywhere.com/api/profile/1/";
        StringRequest stringrequest= new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse: " + response);
                //str= response;

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: starated"+error.getMessage());

            }
        }
        );

        RequestQueue requestQueue= Volley.newRequestQueue(context);
        requestQueue.add(stringrequest);
        requestQueue.start();

        return "nai";

    }

}
