package com.tutor.testtaker;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class VolleyPoint {
    private static final String TAG = "VolleyPoint";

    private static VolleyPoint mInstance;
    private RequestQueue mRequestQueue;

    private VolleyPoint(Context context){
        mRequestQueue= Volley.newRequestQueue(context.getApplicationContext());
    }

    public static synchronized VolleyPoint getInstance(Context context){
        if(mInstance == null){
            mInstance = new VolleyPoint(context);
        }

        return mInstance;
    }

    private RequestQueue getRequestQueue(){
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        Log.d(TAG, "addToRequestQueue: started");
        req.setRetryPolicy(
                new DefaultRetryPolicy(5 * 1000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
        );
        getRequestQueue().add(req);
    }
}
