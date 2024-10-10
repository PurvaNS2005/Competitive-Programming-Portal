package com.example.competitveprogrammingportal.contest.lc2;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;


import java.util.List;

public class lc2vm extends AndroidViewModel {
    private lcRepository repository;
    private MutableLiveData<List<ContestHistory>> contestsLiveData;

    public lc2vm(@NonNull Application application) {
        super(application);
        repository = new lcRepository(application);
        contestsLiveData = new MutableLiveData<>();
    }
    public void fetchUserContests(){
        repository.getMutableLiveData().observeForever(data -> {
            if (data != null) {
                contestsLiveData.setValue(data);
            } else {
                Toast.makeText(getApplication(), "Username not found", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public MutableLiveData<List<ContestHistory>> getContestsLiveData() {
        return contestsLiveData;
    }
}
