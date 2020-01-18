package com.tutor.testtaker;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;

public class VolleyPoint {
    Gson gson;

    public VolleyPoint() {
        gson= new Gson();
    }

    public String getAuthToken(String username, String password){
        String url= "presslu1.pythonanywhere.com/api/login/";
        StringRequest stringrequest= new StringRequest(Request.Method.POST,url,)

    }

}
