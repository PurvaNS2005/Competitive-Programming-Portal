package com.example.competitveprogrammingportal.leetcode;

import com.example.competitveprogrammingportal.leetcode.lcapiservice;

import retrofit2.Retrofit;

public class RetrofitInstance {
    private static Retrofit retrofit = null;
    private static String BASE_URL = "https://alfa-leetcode-api.onrender.com/";
    public static lcapiservice getService(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(lcapiservice.class);
    }
}
