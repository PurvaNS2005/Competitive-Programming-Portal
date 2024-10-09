package com.example.competitveprogrammingportal.leetcode;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class lcviewmodel extends AndroidViewModel {
    private MutableLiveData<lcmodelclass> userLiveData;
    private lcrepo repository;

    public lcviewmodel(@NonNull Application application) {
        super(application);
        repository = new lcrepo(application);
        userLiveData = new MutableLiveData<>();
    }

    public void fetchUserData(String handle) {
        repository.getMutableLiveData(handle).observeForever(data -> {
            if (data != null) {
                userLiveData.setValue(data);
            } else {
                Toast.makeText(getApplication(), "Username not found", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public LiveData<lcmodelclass> getUserLiveData() {
        return userLiveData;
    }
}
