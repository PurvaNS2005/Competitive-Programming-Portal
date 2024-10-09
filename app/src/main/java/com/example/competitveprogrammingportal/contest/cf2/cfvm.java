package com.example.competitveprogrammingportal.contest.cf2;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;


import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;


import java.util.List;
public class cfvm extends AndroidViewModel {
    private cfRepository repository;
    private MutableLiveData<List<List<Result>>> contestsLiveData;

    public cfvm(Application application) {
        super(application);
        repository = new cfRepository(application);
        contestsLiveData = repository.getMutableLiveData();
    }

    public MutableLiveData<List<List<Result>>> getContestsLiveData() {
        return contestsLiveData;
    }
}
