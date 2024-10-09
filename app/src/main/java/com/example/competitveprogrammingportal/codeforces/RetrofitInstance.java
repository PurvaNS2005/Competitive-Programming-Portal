package com.example.competitveprogrammingportal.codeforces;

import retrofit2.Retrofit;

public class RetrofitInstance {

    private static Retrofit retrofit = null;
    private static String BASE_URL = "https://codeforces.com/api/";
    public static cfapiservice getService(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(cfapiservice.class);
    }
}
