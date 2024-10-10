package com.example.competitveprogrammingportal.leetcode;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import com.example.competitveprogrammingportal.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Leetcode extends AppCompatActivity {
    private EditText editTextHandle;
    private Button btnSearch;
    private TextView userName, ranking, totalSolved, easySolved, mediumSolved, hardSolved, contribution, reputation, lc_handle;
    private ProgressBar progressBar;
    private lcviewmodel lcviewmodel;
    private FirebaseFirestore db;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leetcode);
        editTextHandle = findViewById(R.id.edit_text_handle);
        btnSearch = findViewById(R.id.btn_search);
        userName = findViewById(R.id.user_name);
        ranking = findViewById(R.id.ranking);
        totalSolved = findViewById(R.id.total_solved);
        easySolved = findViewById(R.id.easy_solved);
        mediumSolved = findViewById(R.id.medium_solved);
        hardSolved = findViewById(R.id.hard_solved);
        contribution = findViewById(R.id.user_contribution);
        reputation = findViewById(R.id.reputation);
        progressBar = findViewById(R.id.progress_bar);
        auth = FirebaseAuth.getInstance();
        lcviewmodel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(lcviewmodel.class);
        db = FirebaseFirestore.getInstance();
        searchDefault();
        btnSearch.setOnClickListener(v -> {
            String handle = editTextHandle.getText().toString().trim();
            progressBar.setVisibility(View.VISIBLE);
            if (!handle.isEmpty()) {
                lcviewmodel.fetchUserData(handle);
                saveToDb(auth.getCurrentUser().getEmail().toString(), handle);
                progressBar.setVisibility(View.GONE);
            } else {
                Toast.makeText(Leetcode.this, "Please enter a valid handle", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        });
        lcviewmodel.getUserLiveData().observe(this, userData -> {
            if (userData != null) {
                displayUserData(userData);
            }
            progressBar.setVisibility(View.GONE);
        });
        Toolbar toolbar = findViewById(R.id.toolbar_lc);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void displayUserData(lcmodelclass userData) {
        userName.setText(editTextHandle.getText().toString());
        ranking.setText(String.valueOf(userData.getRanking()));
        totalSolved.setText(String.valueOf(userData.getTotalSolved()));
        easySolved.setText(String.valueOf(userData.getEasySolved()));
        mediumSolved.setText(String.valueOf(userData.getMediumSolved()));
        hardSolved.setText(String.valueOf(userData.getHardSolved()));
        contribution.setText(String.valueOf(userData.getContributionPoint()));
        reputation.setText(String.valueOf(userData.getReputation()));
    }
    private void saveToDb(String email, String handle){
        Map<String, Object> leetcodeData = new HashMap<>();
        leetcodeData.put("handle", handle);
        db.collection("users").document(email).collection("Leetcode").document("handleDocument")
                .set(leetcodeData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("Firestore", "Leetcode handle added successfully!");
                    }
                })
                .addOnFailureListener(e -> {
                    Log.e("Firestore", "Error adding document", e);
                });
    }
    private void searchDefault() {
        db.collection("users").document(auth.getCurrentUser().getEmail()).collection("Leetcode").document("handleDocument").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String handle = documentSnapshot.getString("handle");
                if (handle != null && !handle.isEmpty()) {
                    editTextHandle.setText(handle);
                    lcviewmodel.fetchUserData(handle);
                }
            }
        });
    }
}
