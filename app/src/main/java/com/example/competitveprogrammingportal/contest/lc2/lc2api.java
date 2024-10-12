package com.example.competitveprogrammingportal.contest.lc2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface lc2api {

    //{username} replave with PurvaNS (valid leetcode handle username) to get past contests
    @GET("PurvaNS/contest/history")
    Call<lc2modelclass> getContests();
}
