package com.example.competitveprogrammingportal.contest.ac2;


import retrofit2.Retrofit;

public class RetrofitInstance {
    private static Retrofit retrofit = null;
    private static String BASE_URL = "https://kenkoooo.com/atcoder/resources/";
    public static acapi getService(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(acapi.class);
    }
}
