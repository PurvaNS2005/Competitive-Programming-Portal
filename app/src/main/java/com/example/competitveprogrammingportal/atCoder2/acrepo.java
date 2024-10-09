package com.example.competitveprogrammingportal.atCoder2;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class acrepo {
    private MutableLiveData<acmodel_rank> mutableLiveData = new MutableLiveData<>();
    private Application application;

    public acrepo(Application application) {
        this.application = application;
    }
    public MutableLiveData<acmodel_rank> getMutableLiveData(String handle) {
        acapiservice api = RetrofitInstance.getService();

        Call<acmodel_rank> call = api.getUserRank(handle);

        call.enqueue(new Callback<acmodel_rank>() {
            @Override
            public void onResponse(Call<acmodel_rank> call, Response<acmodel_rank> response) {
                if (response.isSuccessful() && response.body() != null) {
                    acmodel_rank userInfo = response.body();
                    mutableLiveData.setValue(userInfo);
                    // Update UI with user info
                } else {
                    // Handle case where user is not found or any other error
                    showErrorMessage("User not found or invalid handle.");
                }
            }

            @Override
            public void onFailure(Call<acmodel_rank> call, Throwable throwable) {
                // Handle failure, log error, etc.
                showErrorMessage("API call failed: "+throwable.getMessage());
            }
        });

        return mutableLiveData;
    }
    private void showErrorMessage(String msg){
        Toast.makeText(application, msg, Toast.LENGTH_SHORT).show();
    }
}
