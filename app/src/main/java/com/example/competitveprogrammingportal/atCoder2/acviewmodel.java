package com.example.competitveprogrammingportal.atCoder2;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.competitveprogrammingportal.atCoder2.acmodel_rank;
import com.example.competitveprogrammingportal.atCoder2.acrepo;

public class acviewmodel extends AndroidViewModel {
    private acrepo acRepository;
    private MutableLiveData<acmodel_rank> userData;
    public acviewmodel(Application application) {
        super(application);
        acRepository = new acrepo(application);
        userData = new MutableLiveData<>();
    }
    public void fetchUserData(String handle) {
        acRepository.getMutableLiveData(handle).observeForever(data -> {
            if (data != null && data.getRank()!=null) {
                userData.setValue(data);
            }else{
                Toast.makeText(this.getApplication(), "Username not found", Toast.LENGTH_SHORT).show();
                return;
            }
        });
    }
    public LiveData<acmodel_rank> getUserLiveData() {
        return userData;
    }
}
