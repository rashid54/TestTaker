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
    private static String BASE_URL = "presslu1.pythonanywhere.com/api/";
    Retrofit retrofit;
    RetrofitClient retrofitclient;

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
        users = null;
        Call<List<UserProfile>> getProfileList = retrofitclient.getProfileList();
        getProfileList.enqueue(new Callback<List<UserProfile>>() {
            @Override
            public void onResponse(Call<List<UserProfile>> call, Response<List<UserProfile>> response) {
                Log.d(TAG, "onResponse: "+response.code());
                users = response.body();
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

        Call<String> getAuthToken= retrofitclient.getAuthToken(username,password);
        getAuthToken.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d(TAG, "onResponse: getauthtoken"+response.code());
                if(response.code()== 200){
                    string= response.body();
                }
                else string = null;
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d(TAG, "onFailure: getauthtoken");
            }
        });
        return string;
    }

}
