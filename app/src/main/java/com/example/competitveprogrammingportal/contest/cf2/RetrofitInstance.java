package com.example.competitveprogrammingportal.contest.cf2;

import com.example.competitveprogrammingportal.contest.cf2.cfapi;

import retrofit2.Retrofit;

public class RetrofitInstance {
    private static Retrofit retrofit = null;
    private static String BASE_URL = "https://codeforces.com/api/";
    public static cfapi getService(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(cfapi.class);
    }
}