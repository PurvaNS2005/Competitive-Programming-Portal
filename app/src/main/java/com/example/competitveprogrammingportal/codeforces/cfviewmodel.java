package com.example.competitveprogrammingportal.codeforces;

import android.app.Application;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
public class cfviewmodel extends AndroidViewModel {
    private MutableLiveData<cfmodelclass> userLiveData; // Holds the entire response
    private cfrepo repository;

    public cfviewmodel(@NonNull Application application) {
        super(application);
        repository = new cfrepo(application);
        userLiveData = new MutableLiveData<>();
    }

    public void fetchUserData(String handle) {
        repository.getMutableLiveData(handle).observeForever(data -> {
            if (data != null && data.getResult()!=null && !data.getResult().isEmpty()) {
                userLiveData.setValue(data);
            }else{
                Toast.makeText(this.getApplication(), "Username not found", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public MutableLiveData<cfmodelclass> getUserLiveData() {
        return userLiveData;
    }
}
