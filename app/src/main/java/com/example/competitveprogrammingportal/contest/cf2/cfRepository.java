package com.example.competitveprogrammingportal.contest.cf2;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class cfRepository {
    private MutableLiveData<List<List<Result>>> contestsLiveData = new MutableLiveData<>();
    private Application application;

    public cfRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<List<Result>>> getMutableLiveData() {
        cfapi api = RetrofitInstance.getService();
        Call<cfmodelclass2> call = api.getContestList();
        call.enqueue(new Callback<cfmodelclass2>() {
            @Override
            public void onResponse(Call<cfmodelclass2> call, Response<cfmodelclass2> response) {
                if (response.isSuccessful() && response.body() != null) {
                    cfmodelclass2 allContests = response.body();
                    segregateContests(allContests);
                } else {
                    showErrorMessage("User not found or invalid handle.");
                }
            }
            @Override
            public void onFailure(Call<cfmodelclass2> call, Throwable throwable) {
                showErrorMessage("API call failed: " + throwable.getMessage());
            }
        });
        return contestsLiveData;
    }

    public void segregateContests(cfmodelclass2 allContests) {
        List<Result> pastContests = new ArrayList<>();
        List<Result> ongoingContests = new ArrayList<>();
        List<Result> futureContests = new ArrayList<>();
        long currentTime = System.currentTimeMillis() / 1000;

        for (Result contest : allContests.getResult()) {
            long contestEndTime = contest.getStartTimeSeconds() + contest.getDurationSeconds();
            if (contestEndTime < currentTime) {
                pastContests.add(contest);
            } else if (contest.getStartTimeSeconds() > currentTime) {
                futureContests.add(contest);
            } else {
                ongoingContests.add(contest);
            }
        }

        List<List<Result>> segregatedContests = new ArrayList<>();
        segregatedContests.add(pastContests);
        segregatedContests.add(ongoingContests);
        segregatedContests.add(futureContests);
        contestsLiveData.setValue(segregatedContests);
    }

    private void showErrorMessage(String msg) {
        Toast.makeText(application, "hi"+msg, Toast.LENGTH_SHORT).show();
    }
}
