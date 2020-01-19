package com.tutor.testtaker;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class VolleyPoint {
    private static final String TAG = "VolleyPoint";

    private String str;

    public VolleyPoint() {
        //gson= new Gson();
    }

    public String getAuthToken(String username, String password, Context context){
        Log.d(TAG, "getAuthToken: started");
        Gson gson= new Gson();
        String url= "https://presslu1.pythonanywhere.com/api/login/";
        StringRequest stringrequest= new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse: " + response);
                str= response;


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: starated"+error.getMessage());

            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> logindata= new HashMap<>();

                logindata.put("username","rashid");
                logindata.put("password","ekduitin");

                return logindata;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(context);
        requestQueue.add(stringrequest);
        requestQueue.start();

        return str;

    }
    public String getAuth(String username, String password, Context context){
        Log.d(TAG, "getAuth: started");
        String url = "https://presslu1.pythonanywhere.com/api/login/";

        Map<String,String> logindata= new HashMap<>();

        logindata.put("username",username);
        logindata.put("password",password);

        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.POST, url,new JSONObject(logindata), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.d(TAG, "onResponse: getauth " + response.getString("token"));
                    str= response.getString("token");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: getauth");
                Log.e(TAG, "onErrorResponse: ",error );
                str= null;
            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(context);
        requestQueue.add(jsonObjectRequest);
        requestQueue.start();
        Log.d(TAG, "getAuth: str : "+ str);
        return str;
    }

}
