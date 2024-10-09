package com.example.competitveprogrammingportal.leetcode;

import com.example.competitveprogrammingportal.leetcode.lcmodelclass;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface lcapiservice {
    @GET("userProfile/{userName}")
    Call<lcmodelclass> getLCUserInfo(@Path("userName") String handle);
}
