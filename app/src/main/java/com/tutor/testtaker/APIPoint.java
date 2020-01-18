package com.tutor.testtaker;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIPoint {
    private static final String TAG = "APIPoint";
    private static String BASE_URL = "http://presslu1.pythonanywhere.com/api/";
    public Retrofit retrofit;

    public RetrofitClient retrofitclient;

    List<UserProfile> users;
    String string;

    public APIPoint() {
        retrofit= new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitclient= retrofit.create(RetrofitClient.class);

    }

    public ArrayList<UserProfile> getallprofile(){
        Log.d(TAG, "getallprofile: started");
        users = null;
        Call<List<UserProfile>> getProfileList = retrofitclient.getProfileList();
        getProfileList.enqueue(new Callback<List<UserProfile>>() {
            @Override
            public void onResponse(Call<List<UserProfile>> call, Response<List<UserProfile>> response) {
                Log.d(TAG, "onResponse: "+response.code());
                users = response.body();
                Log.d(TAG, "onResponse: "+users.get(0).getUsername());
            }

            @Override
            public void onFailure(Call<List<UserProfile>> call, Throwable t) {
                Log.d(TAG, "onFailure: failed");

            }
        });
        //Todo: needs to be tested (casting of List<> to ArrayList<>
        return new ArrayList<UserProfile>(users);
    }

    public String getAuthToken(String username,String password){
        Log.d(TAG, "getAuthToken: started");
        string= null;
        logindata data= new logindata(username,password);
        Call<Loginresponse> getAuthToken= retrofitclient.getAuthToken(data);
        getAuthToken.enqueue(new Callback<Loginresponse>() {
            @Override
            public void onResponse(Call<Loginresponse> call, Response<Loginresponse> response) {
                Log.d(TAG, "onResponse: getauthtoken started");
                string= response.body().getToken();
            }

            @Override
            public void onFailure(Call<Loginresponse> call, Throwable t) {
                Log.d(TAG, "onFailure: authtoken");
                string=null;

            }
        });
        return string;
    }

}
