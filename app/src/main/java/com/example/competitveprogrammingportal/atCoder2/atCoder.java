package com.example.competitveprogrammingportal.atCoder2;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.competitveprogrammingportal.R;
import com.example.competitveprogrammingportal.atCoder2.atCoder;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class atCoder extends AppCompatActivity {
    private TextView acRank, acCount;
    private EditText editTextHandle;
    private Button btnSearch;
    private ProgressBar progressBar;
    private acviewmodel acVM;
    private FirebaseFirestore db;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_at_coder2);
        acRank = findViewById(R.id.ac_rank);
        acCount = findViewById(R.id.ac_count);
        editTextHandle = findViewById(R.id.edit_text_ac_handle);
        auth = FirebaseAuth.getInstance();
        btnSearch = findViewById(R.id.btn_ac_search);
        progressBar = findViewById(R.id.progress_bar_ac);
        db = FirebaseFirestore.getInstance();
        acVM = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication()))
                .get(acviewmodel.class);
        btnSearch.setOnClickListener(v -> {
            String handle = editTextHandle.getText().toString();
            if (!handle.isEmpty()) {
                progressBar.setVisibility(View.VISIBLE);
                acVM.fetchUserData(handle);
                saveToDb(auth.getCurrentUser().getEmail(), handle);
                searchDefault();
            } else {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(atCoder.this, "Please enter a valid handle", Toast.LENGTH_SHORT).show();
            }
        });
        acVM.getUserLiveData().observe(this, userData -> {
            progressBar.setVisibility(View.GONE);
            if (userData != null) {
                updateUI(userData);
            } else {
                Toast.makeText(atCoder.this, "No data found for this user", Toast.LENGTH_SHORT).show();
            }
        });
        Toolbar toolbar = findViewById(R.id.toolbar_ac);
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

    private void updateUI(acmodel_rank userData) {
        acRank.setText(String.valueOf(userData.getRank()));
        acCount.setText(String.valueOf(userData.getCount()));
    }
    private void searchDefault() {
        db.collection("users").document(auth.getCurrentUser().getEmail()).collection("AtCoder").document("handleDocument").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String handle = documentSnapshot.getString("handle");
                if (handle != null && !handle.isEmpty()) {
                    editTextHandle.setText(handle);
                    acVM.fetchUserData(handle);
                }
            }
        });
    }
    private void saveToDb(String email, String handle){
        Map<String, Object> atCoderData = new HashMap<>();
        atCoderData.put("handle", handle);
        db.collection("users").document(email).collection("AtCoder").document("handleDocument")
                .set(atCoderData)
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
}