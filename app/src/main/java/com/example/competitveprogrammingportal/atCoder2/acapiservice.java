package com.example.competitveprogrammingportal.atCoder2;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface acapiservice {
    @GET("ac_rank")
    Call<acmodel_rank> getUserRank(@Query("user") String handle);
}
