package com.example.competitveprogrammingportal.contest.ac2;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class acvm extends AndroidViewModel {
    private Repository repository;
    private MutableLiveData<List<List<acmodelclass>>> contestsLiveData;

    public acvm(Application application) {
        super(application);
        repository = new Repository(application);
        contestsLiveData = new MutableLiveData<>();
    }
    // Fetch contests from the repository
    public void fetchContests() {
        repository.getContests().observeForever(data -> {
            if (data != null) {
                contestsLiveData.setValue(data);
            } else {
                Toast.makeText(getApplication(), "No contests found", Toast.LENGTH_SHORT).show();
            }
        });
    }
    // Get the LiveData for the list of contests
    public LiveData<List<List<acmodelclass>>> getContestsLiveData() {
        return contestsLiveData;
    }
}