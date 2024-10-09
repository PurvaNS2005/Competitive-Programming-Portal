package com.example.competitveprogrammingportal.contest.ac2;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class Repository {
    private MutableLiveData<List<List<acmodelclass>>> contests = new MutableLiveData<>();
    private Application application;

    public Repository(Application application) {
        this.application = application;
    }
    public MutableLiveData<List<List<acmodelclass>>> getContests() {
        acapi acapi = RetrofitInstance.getService();
        Call<List<acmodelclass>> call = acapi.getContests();
        call.enqueue(new retrofit2.Callback<List<acmodelclass>>() {
            @Override
            public void onResponse(Call<List<acmodelclass>> call, retrofit2.Response<List<acmodelclass>> response) {
                if (response != null && response.body() != null) {
                    List<acmodelclass> allContests = response.body();
                    List<acmodelclass> pastContests = new ArrayList<>();
                    List<acmodelclass> ongoingContests = new ArrayList<>();
                    List<acmodelclass> futureContests = new ArrayList<>();
                    long currentTime = System.currentTimeMillis() / 1000;
                    for (acmodelclass contest : allContests) {
                        if (contest.getStartEpochSecond() + contest.getDurationSecond() < currentTime) {
                            pastContests.add(contest);
                        } else if (contest.getStartEpochSecond() <= currentTime && contest.getStartEpochSecond()+contest.getDurationSecond()>currentTime) {
                            ongoingContests.add(contest);
                        } else {
                            futureContests.add(contest);
                        }
                    }
                    List<List<acmodelclass>> categorizedContests = new ArrayList<>();
                    categorizedContests.add(pastContests);
                    categorizedContests.add(ongoingContests);
                    categorizedContests.add(futureContests);

                    contests.setValue(categorizedContests);
                }else{
                    contests.setValue(null);
                }
            }
            @Override
            public void onFailure(Call<List<acmodelclass>> call, Throwable throwable) {
                Toast.makeText(application, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                contests.setValue(null);
            }
        });
        return contests;
    }
}
