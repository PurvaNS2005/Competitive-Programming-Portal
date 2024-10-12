package com.example.competitveprogrammingportal.codeforces;

import android.app.Application;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.competitveprogrammingportal.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class cfrepo {
    private MutableLiveData<cfmodelclass> mutableLiveData = new MutableLiveData<>();
    private Application application;

    public cfrepo(Application application) {
        this.application = application;
    }
    //calls the api to get the user handle details
    public MutableLiveData<cfmodelclass> getMutableLiveData(String handle) {
        cfapiservice api = RetrofitInstance.getService();

        Call<cfmodelclass> call = api.getUserInfo(handle);

        call.enqueue(new Callback<cfmodelclass>() {
            @Override
            public void onResponse(Call<cfmodelclass> call, Response<cfmodelclass> response) {
                if (response.isSuccessful() && response.body() != null) {
                    cfmodelclass userInfo = response.body(); // response is of type cfmodelclass.
                    mutableLiveData.setValue(userInfo);
                } else {
                    mutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<cfmodelclass> call, Throwable throwable) {
                mutableLiveData.setValue(null);
                showErrorMessage("API call failed: "+throwable.getMessage());
            }
        });

        return mutableLiveData;
    }
    private void showErrorMessage(String msg){
        Toast.makeText(application, msg, Toast.LENGTH_SHORT).show();
    }
}
