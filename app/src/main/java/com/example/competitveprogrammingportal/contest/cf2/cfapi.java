package com.example.competitveprogrammingportal.contest.cf2;

import retrofit2.Call;
import retrofit2.http.GET;

public interface cfapi {
    @GET("contest.list")
    Call<cfmodelclass2> getContestList();
}
