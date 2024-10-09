package com.example.competitveprogrammingportal.contest.lc2;


import retrofit2.Retrofit;

public class RetrofitInstance {
    private static Retrofit retrofit = null;
    private static String BASE_URL = "https://alfa-leetcode-api.onrender.com/";
    public static lc2api getService(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(lc2api.class);
    }
}
