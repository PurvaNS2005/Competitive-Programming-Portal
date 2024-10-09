package com.example.competitveprogrammingportal.contest.lc2;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class lcRepository {
    private MutableLiveData<List<ContestHistory>> contestsLiveData = new MutableLiveData<>();
    private Application application;

    public lcRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<ContestHistory>> getMutableLiveData(String handle) {
        lc2api api = RetrofitInstance.getService();
        Call<lc2modelclass> call = api.getContests(handle);
        call.enqueue(new Callback<lc2modelclass>() {
            @Override
            public void onResponse(Call<lc2modelclass> call, Response<lc2modelclass> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<ContestHistory> allContests = response.body().getContestHistory();
                    contestsLiveData.setValue(allContests);
                } else {
                    showErrorMessage("User not found or invalid handle.");
                }
            }

            @Override
            public void onFailure(Call<lc2modelclass> call, Throwable throwable) {
                showErrorMessage("API call failed: " + throwable.getMessage());
            }
        });
        return contestsLiveData;
    }

    private void showErrorMessage(String msg) {
        Toast.makeText(application, msg, Toast.LENGTH_SHORT).show();
    }
}
