package com.example.competitveprogrammingportal.leetcode;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class lcrepo {
    private MutableLiveData<lcmodelclass> mutableLiveData = new MutableLiveData<>();
    private Application application;

    public lcrepo(Application application) {
        this.application = application;
    }
    public MutableLiveData<lcmodelclass> getMutableLiveData(String handle) {
        lcapiservice api = RetrofitInstance.getService();
        Call<lcmodelclass> call = api.getLCUserInfo(handle);
        call.enqueue(new Callback<lcmodelclass>() {
            @Override
            public void onResponse(Call<lcmodelclass> call, Response<lcmodelclass> response) {
                if (response.isSuccessful() && response.body() != null) {
                    lcmodelclass userInfo = response.body();
                    mutableLiveData.setValue(userInfo);
                } else {
                    mutableLiveData.setValue(null);
                    Toast.makeText(application, "User handle not found", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<lcmodelclass> call, Throwable throwable) {
                mutableLiveData.setValue(null);
                showErrorMessage("API call failed: "+throwable.getMessage());
            }
        });

        return mutableLiveData;
    }
    private void showErrorMessage(String msg){
        Toast.makeText(application, msg, Toast.LENGTH_SHORT).show();
    }
}
