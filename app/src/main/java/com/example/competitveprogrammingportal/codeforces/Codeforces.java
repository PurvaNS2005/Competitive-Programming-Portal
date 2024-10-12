package com.example.competitveprogrammingportal.codeforces;

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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.competitveprogrammingportal.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Codeforces extends AppCompatActivity {
    Button btn;
    EditText handle_cf;
    cfviewmodel cfviewmodel;
    ProgressBar bar;
    private FirebaseFirestore db;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_codeforces);
        // Initialize views
        btn = findViewById(R.id.btn_search);
        handle_cf = findViewById(R.id.edit_text_handle);
        bar = findViewById(R.id.progress_bar);
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        searchDefault(auth.getCurrentUser().getEmail(), handle_cf.getText().toString());
        cfviewmodel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(com.example.competitveprogrammingportal.codeforces.cfviewmodel.class);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userHandle = handle_cf.getText().toString();
                if (userHandle.isEmpty()) {
                    Toast.makeText(Codeforces.this, "Please enter a handle", Toast.LENGTH_SHORT).show();
                    return;
                }
                bar.setVisibility(View.VISIBLE);
                cfviewmodel.fetchUserData(userHandle);
                saveToDb(auth.getCurrentUser().getEmail(), handle_cf.getText().toString());
                searchDefault(auth.getCurrentUser().getEmail(), handle_cf.getText().toString());
            }
        });

        //fill the necessary fields with the response
        cfviewmodel.getUserLiveData().observe(this, new Observer<cfmodelclass>() {
            @Override
            public void onChanged(cfmodelclass userData) {
                bar.setVisibility(View.GONE);
                ((TextView) findViewById(R.id.user_handle)).setText(userData.getResult().get(0).getHandle());
                ((TextView) findViewById(R.id.user_rating)).setText(String.valueOf(userData.getResult().get(0).getRating()));
                ((TextView) findViewById(R.id.user_contribution)).setText(String.valueOf(userData.getResult().get(0).getContribution()));
                ((TextView) findViewById(R.id.rank)).setText(userData.getResult().get(0).getRank());
                ((TextView) findViewById(R.id.max_rating)).setText(String.valueOf(userData.getResult().get(0).getMaxRating()));
                ((TextView) findViewById(R.id.max_rank)).setText(userData.getResult().get(0).getMaxRank());
                ((TextView) findViewById(R.id.name)).setText(userData.getResult().get(0).getFirstName());
                ((TextView) findViewById(R.id.Last_name)).setText(userData.getResult().get(0).getLastName());
                ((TextView) findViewById(R.id.country)).setText(userData.getResult().get(0).getCountry());
            }
        });
        Toolbar toolbar = findViewById(R.id.toolbar_cf);
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
    private void searchDefault(String email, String handle) {
        db.collection("users").document(email).collection("Codeforces").document("handleDocument").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                bar.setVisibility(View.GONE);
                String handle = documentSnapshot.getString("handle");
                if (handle != null && !handle.isEmpty()) {
                    handle_cf.setText(handle);
                    cfviewmodel.fetchUserData(handle);
                }
            }
        });
    }
    private void saveToDb(String email, String handle){
        Map<String, Object> codeforcesdata = new HashMap<>();
        codeforcesdata.put("handle", handle);
        db.collection("users").document(email).collection("Codeforces").document("handleDocument")
                .set(codeforcesdata)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("Firestore", "Codeforces handle added successfully!");
                    }
                })
                .addOnFailureListener(e -> {
                    Log.e("Firestore", "Error adding document", e);
                });
    }
}