package com.example.competitveprogrammingportal;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.competitveprogrammingportal.atCoder2.atCoder;
import com.example.competitveprogrammingportal.cal.Calendarfile;
import com.example.competitveprogrammingportal.codeforces.Codeforces;
import com.example.competitveprogrammingportal.contest.Contests;
import com.example.competitveprogrammingportal.leetcode.Leetcode;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.HashMap;
import java.util.Map;
public class HomePage extends AppCompatActivity {
    TextView tv, email, lc_handle, cf_handle, ac_handle;
    CardView cardView, cardView2, cardView3;
    Button contests;
    ImageButton calendar;
    FirebaseFirestore db;
    SwipeRefreshLayout swipeRefreshLayout;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tv = findViewById(R.id.name);
        cardView = findViewById(R.id.cardView);
        swipeRefreshLayout = findViewById(R.id.refreshLayout);
        cardView2 = findViewById(R.id.cardView2);
        cardView3 = findViewById(R.id.cardView3);
        auth = FirebaseAuth.getInstance();
        contests = findViewById(R.id.contests);
        calendar = findViewById(R.id.calendar);
        String name = getIntent().getStringExtra("username");;
        tv.setText(name);
        email = findViewById(R.id.emails);
        String mail = getIntent().getStringExtra("email");
        email.setText(mail);
        lc_handle = findViewById(R.id.lc_handle);
        cf_handle = findViewById(R.id.cf_handle);
        ac_handle = findViewById(R.id.ac_handle);
        db = FirebaseFirestore.getInstance();
        fetchLeetcodeHandleFromFirestore(email.getText().toString());
        fetchCodeforcesHandleFromFirestore(email.getText().toString());
        fetchAtCoderFromFirestore(email.getText().toString());
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomePage.this, Calendarfile.class);
                startActivity(i);
            }
        });
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomePage.this, Codeforces.class);
                startActivity(i);
            }
        });
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this, Leetcode.class));
            }
        });
        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this, atCoder.class));
            }
        });
        contests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this, Contests.class));
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchLeetcodeHandleFromFirestore(email.getText().toString());
                fetchCodeforcesHandleFromFirestore(email.getText().toString());
                fetchAtCoderFromFirestore(email.getText().toString());
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.logout){
            auth.signOut();
            finish();
            startActivity(new Intent(HomePage.this, LogIn.class));
            return true;
        }
        return onOptionsItemSelected(item);
    }

    private void fetchLeetcodeHandleFromFirestore(String email){
        db.collection("users").document(email).collection("Leetcode").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if(!queryDocumentSnapshots.isEmpty()){
                    lc_handle.setText(queryDocumentSnapshots.getDocuments().get(0).getString("handle"));
                }
            }
        });
    }
    private void fetchCodeforcesHandleFromFirestore(String email){
        db.collection("users").document(email).collection("Codeforces").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if(!queryDocumentSnapshots.isEmpty()){
                    cf_handle.setText(queryDocumentSnapshots.getDocuments().get(0).getString("handle"));
                }
            }
        });
    }
    private void fetchAtCoderFromFirestore(String email){
        db.collection("users").document(email).collection("AtCoder").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if(!queryDocumentSnapshots.isEmpty()){
                    ac_handle.setText(queryDocumentSnapshots.getDocuments().get(0).getString("handle"));
                }
            }
        });
    }
}