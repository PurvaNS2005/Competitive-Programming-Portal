package com.example.competitveprogrammingportal.contest.ac2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface acapi {
    @GET("contests.json")
    Call<List<acmodelclass>> getContests();
}
