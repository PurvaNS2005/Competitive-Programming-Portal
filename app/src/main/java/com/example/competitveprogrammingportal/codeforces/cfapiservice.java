package com.example.competitveprogrammingportal.codeforces;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface cfapiservice {

    @GET("user.info")
    Call<cfmodelclass> getUserInfo(@Query("handles") String handle);
}
