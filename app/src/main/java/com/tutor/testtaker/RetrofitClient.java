package com.tutor.testtaker;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitClient {

    @GET("profile/")
    Call<List<UserProfile>> getProfileList();

    @POST("login")
    Call<Loginresponse> getAuthToken(@Body logindata logdata);

}
