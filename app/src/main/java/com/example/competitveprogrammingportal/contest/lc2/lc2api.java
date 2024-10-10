package com.example.competitveprogrammingportal.contest.lc2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface lc2api {
    @GET("PurvaNS/contest/history")
    Call<lc2modelclass> getContests();
}
