package com.example.competitveprogrammingportal.atCoder2;

import com.example.competitveprogrammingportal.atCoder2.acapiservice;

import retrofit2.Retrofit;

public class RetrofitInstance {
    private static Retrofit retrofit = null;
    private static String BASE_URL = "https://kenkoooo.com/atcoder/atcoder-api/v3/user/";
    public static acapiservice getService(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(acapiservice.class);
    }
}