package com.example.competitveprogrammingportal.cal;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.competitveprogrammingportal.HomePage;
import com.example.competitveprogrammingportal.R;
import com.example.competitveprogrammingportal.contest.Contests;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
public class Calendarfile extends AppCompatActivity {
    private FirebaseFirestore db;
    private FirebaseAuth auth;
    RecyclerView recyclerView;
    calendarAdapter calendarAdapter;
    TextView days;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        recyclerView = findViewById(R.id.curr_cal_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchContestsFromFirestore();
        Toolbar toolbar_cal = findViewById(R.id.toolbar_calendar);
        setSupportActionBar(toolbar_cal);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.contests_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            onBackPressed();
            return true;
        }else if(item.getItemId()==R.id.contests_menu){
            startActivity(new Intent(this, Contests.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void fetchContestsFromFirestore() {
        db.collection("users")
                .document(auth.getCurrentUser().getEmail())
                .collection("Contests")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    List<DocumentSnapshot> contestDocs = queryDocumentSnapshots.getDocuments();
                    if (!contestDocs.isEmpty()) {
                        calendarAdapter = new calendarAdapter(contestDocs, this);
                        recyclerView.setAdapter(calendarAdapter);
                    } else {
                        Toast.makeText(Calendarfile.this, "No contests found", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(e -> {
                    Log.e("Firestore Error", e.getMessage());
                    Toast.makeText(Calendarfile.this, "Failed to fetch contests", Toast.LENGTH_SHORT).show();
                });
    }
}
