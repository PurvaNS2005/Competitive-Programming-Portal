package com.example.competitveprogrammingportal.codeforces;

import android.app.Application;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
public class cfviewmodel extends AndroidViewModel {
    private MutableLiveData<cfmodelclass> userLiveData;
    private cfrepo repository;

    public cfviewmodel(@NonNull Application application) {
        super(application);
        repository = new cfrepo(application);
        userLiveData = new MutableLiveData<>();
    }
    //uses repos to get the required user details
    public void fetchUserData(String handle) {
        repository.getMutableLiveData(handle).observeForever(data -> {
            if (data != null) {
                userLiveData.setValue(data);
            }else{
                Toast.makeText(this.getApplication(), "Username not found", Toast.LENGTH_SHORT).show();
            }
        });
    }
    //returns the live data
    public MutableLiveData<cfmodelclass> getUserLiveData() {
        return userLiveData;
    }
}
