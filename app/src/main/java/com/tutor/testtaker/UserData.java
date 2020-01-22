package com.tutor.testtaker;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class UserData {
    private static final String TAG = "UserData";
    public static final String DATABASE_NAME= "UserData";

    private Context context;
    private static boolean loginStatus=false;
    private static boolean is_teacher =false;

    public UserData(Context context) {
        this.context = context;
        loginStatus= false;
    }

    public boolean isLoginStatus() {
        SharedPreferences sharedPreferences=context.getSharedPreferences(DATABASE_NAME,Context.MODE_PRIVATE);
        UserData.loginStatus=sharedPreferences.getBoolean("loginStatus",false);
        return UserData.loginStatus;
    }

    public void setLoginStatus(boolean loginStatus) {
        UserData.loginStatus = loginStatus;
        SharedPreferences sharedPreferences= context.getSharedPreferences(DATABASE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putBoolean("loginStatus",loginStatus);
        editor.commit();
    }

    public boolean setAuthToken(String token){
        SharedPreferences sharedPreferences= context.getSharedPreferences(DATABASE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString("AuthToken",token);
        editor.commit();
        return true;
    }

    public String getAuthToken(){
        SharedPreferences sharedPreferences= context.getSharedPreferences(DATABASE_NAME,Context.MODE_PRIVATE);
        String string= sharedPreferences.getString("AuthToken",null);
        return string;
    }
    public void setUser(UserProfile userProfile){
        Gson gson= new Gson();
        SharedPreferences sharedPreferences= context.getSharedPreferences(DATABASE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString("user",gson.toJson(userProfile));
        editor.commit();
    }
    public UserProfile getUser(){
        Gson gson= new Gson();
        SharedPreferences sharedPreferences= context.getSharedPreferences(DATABASE_NAME,Context.MODE_PRIVATE);
        return gson.fromJson(sharedPreferences.getString("user",null),UserProfile.class);
    }

    public static boolean isIs_teacher() {
        return is_teacher;
    }

    public void setIsTeacher(boolean isTeacher) {
        UserData.is_teacher = isTeacher;
        SharedPreferences sharedPreferences= context.getSharedPreferences(DATABASE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putBoolean("is_teacher",isTeacher);
        editor.commit();
    }
}
