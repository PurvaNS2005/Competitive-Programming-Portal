package com.example.competitveprogrammingportal.contest;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.competitveprogrammingportal.HomePage;
import com.example.competitveprogrammingportal.R;
import com.example.competitveprogrammingportal.cal.Calendarfile;
import com.example.competitveprogrammingportal.contest.ac2.acmodelclass;
import com.example.competitveprogrammingportal.contest.ac2.acAdapter;
import com.example.competitveprogrammingportal.contest.ac2.acvm;
import com.example.competitveprogrammingportal.contest.cf2.Result;
import com.example.competitveprogrammingportal.contest.cf2.cfAdapter;
import com.example.competitveprogrammingportal.contest.cf2.cfvm;
import com.example.competitveprogrammingportal.contest.lc2.ContestHistory;
import com.example.competitveprogrammingportal.contest.lc2.lc2vm;
import com.example.competitveprogrammingportal.contest.lc2.lcAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Contests extends AppCompatActivity {
    private RecyclerView ongoingRecyclerView, pastRecyclerView, upcomingRecyclerView;
    FirebaseFirestore db;
    private Button atc, cfc, lcc;
    Button add;
    private acvm viewModel;
    private cfvm codeforcesViewModel;
    private lc2vm leetcodeViewModel;
    ProgressBar progressBar;
    LayoutInflater inflater;

    FirebaseAuth auth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contests);
        //Initializing views
        auth = FirebaseAuth.getInstance();
        atc = findViewById(R.id.btnAtCoder);
        cfc = findViewById(R.id.btnCodeforces);
        lcc = findViewById(R.id.btnLeetCode);
        progressBar = findViewById(R.id.pb_contests);
        ongoingRecyclerView = findViewById(R.id.ongoingRecyclerView);
        pastRecyclerView = findViewById(R.id.pastRecyclerView);
        upcomingRecyclerView = findViewById(R.id.upcomingRecyclerView);

        // setting recycler view layout managers

        ongoingRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        pastRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        upcomingRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewModel = new ViewModelProvider(this).get(acvm.class);
        codeforcesViewModel = new ViewModelProvider(this).get(cfvm.class);
        leetcodeViewModel = new ViewModelProvider(this).get(lc2vm.class);
        db = FirebaseFirestore.getInstance();

        //highlighting the clicked buttons

        atc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                atc.setTextColor(Color.parseColor("#4B0082"));
                cfc.setTextColor(Color.parseColor("#605D5D"));
                lcc.setTextColor(Color.parseColor("#605D5D"));
                atcfetchContests();
            }
        });
        cfc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atc.setTextColor(Color.parseColor("#605D5D"));
                cfc.setTextColor(Color.parseColor("#4B0082"));
                lcc.setTextColor(Color.parseColor("#605D5D"));
                progressBar.setVisibility(View.VISIBLE);
                fetchCodeforcesContests();
            }
        });
        lcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atc.setTextColor(Color.parseColor("#605D5D"));
                cfc.setTextColor(Color.parseColor("#605D5D"));
                lcc.setTextColor(Color.parseColor("#4B0082"));
                progressBar.setVisibility(View.VISIBLE);
                fetchleetCodeContests();
            }
        });
        Toolbar toolbar = findViewById(R.id.toolbar_contests);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.calendar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
            return true;
        }else if(item.getItemId()==R.id.calendar_menu){
            startActivity(new Intent(Contests.this, Calendarfile.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void atcfetchContests() {
        resetRecyclerViewVisibility();
        viewModel.getContestsLiveData().removeObservers(this);
        viewModel.fetchContests();
        viewModel.getContestsLiveData().observe(this, new Observer<List<List<acmodelclass>>>() {
            @Override
            public void onChanged(List<List<acmodelclass>> contests) {
                inflater = LayoutInflater.from(getApplicationContext());
                progressBar.setVisibility(View.GONE);
                View view = inflater.inflate(R.layout.item_contest, upcomingRecyclerView, false);
                add = view.findViewById(R.id.add_btn);


                if (contests != null) {
                    List<acmodelclass> pastContests = contests.get(0);
                    List<acmodelclass> ongoingContests = contests.get(1);
                    List<acmodelclass> futureContests = contests.get(2);

                    String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                    acAdapter ongoingAtcoderAdapter = new acAdapter(Contests.this, ongoingContests);
                    acAdapter pastAtcoderAdapter = new acAdapter(Contests.this, pastContests);
                    acAdapter upcomingAtcoderAdapter = new acAdapter(Contests.this, futureContests);
                    upcomingAtcoderAdapter.setOnAddButtonClickListener(new acAdapter.OnAddButtonClickListener() {
                        @Override
                        public void onAddButtonClick(acmodelclass contestName) {
                            addContestToFirestore(contestName, email);
                        }
                    });
                    ongoingRecyclerView.setAdapter(ongoingAtcoderAdapter);
                    pastRecyclerView.setAdapter(pastAtcoderAdapter);
                    upcomingRecyclerView.setAdapter(upcomingAtcoderAdapter);
                    if (!ongoingContests.isEmpty()) {
                        ongoingRecyclerView.setVisibility(View.VISIBLE);
                    }
                    if (!pastContests.isEmpty()) {
                        pastRecyclerView.setVisibility(View.VISIBLE);
                    }
                    if (!futureContests.isEmpty()) {
                        upcomingRecyclerView.setVisibility(View.VISIBLE);
                    }
                } else {
                    Toast.makeText(Contests.this, "No contests found.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void fetchCodeforcesContests() {
        resetRecyclerViewVisibility();
        codeforcesViewModel.getContestsLiveData().removeObservers(this);
        codeforcesViewModel.getContestsLiveData().observe(this, new Observer<List<List<Result>>>() {
            @Override
            public void onChanged(List<List<Result>> lists) {
                progressBar.setVisibility(View.GONE);
                inflater = LayoutInflater.from(getApplicationContext());
                View view = inflater.inflate(R.layout.item_contest, upcomingRecyclerView, false);
                add = view.findViewById(R.id.add_btn);
                if (lists != null) {
                    List<Result> pastContests = lists.get(0);
                    List<Result> ongoingContests = lists.get(1);
                    List<Result> futureContests = lists.get(2);
                    String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                    cfAdapter ongoingCodeforcesAdapter = new cfAdapter(Contests.this, ongoingContests);
                    cfAdapter pastCodeforcesAdapter = new cfAdapter(Contests.this, pastContests);
                    cfAdapter upcomingCodeforcesAdapter = new cfAdapter(Contests.this, futureContests);
                    upcomingCodeforcesAdapter.setOnAddButtonClickListener(new cfAdapter.OnAddButtonClickListener() {
                        @Override
                        public void onAddButtonClick(Result contestName) {
                            addCodeforcesContestToFirestore(contestName, email);
                        }
                    });
                    ongoingRecyclerView.setAdapter(ongoingCodeforcesAdapter);
                    pastRecyclerView.setAdapter(pastCodeforcesAdapter);
                    upcomingRecyclerView.setAdapter(upcomingCodeforcesAdapter);
                    if (!ongoingContests.isEmpty()) {
                        ongoingRecyclerView.setVisibility(View.VISIBLE);
                    }
                    if (!pastContests.isEmpty()) {
                        pastRecyclerView.setVisibility(View.VISIBLE);
                    }
                    if (!futureContests.isEmpty()) {
                        upcomingRecyclerView.setVisibility(View.VISIBLE);
                    }
                } else {
                    Toast.makeText(Contests.this, "No Codeforces contests found.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    // Add necessary platforms's contest to Firestore
    public void addContestToFirestore(acmodelclass contest, String email) {
        db.collection("users").document(email).collection("Contests")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Map<String, Object> contestData = new HashMap<>();
                        contestData.put("name", contest.getTitle());
                        contestData.put("date", formatUnixTime(contest.getStartEpochSecond()));
                        contestData.put("duration", contest.getDurationSecond());
                        contestData.put("endtime", formatUnixTime(contest.getStartEpochSecond()+contest.getDurationSecond()));

                        db.collection("users").document(email).collection("Contests").add(contestData)
                                .addOnSuccessListener(documentReference -> {
                                    Toast.makeText(Contests.this, "Contest added!", Toast.LENGTH_SHORT).show();
                                })
                                .addOnFailureListener(e -> {
                                    Toast.makeText(Contests.this, "Failed to add contest", Toast.LENGTH_SHORT).show();
                                });

                    } else {
                        Toast.makeText(Contests.this, "Error checking contest", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    public void addCodeforcesContestToFirestore(Result contest, String email) {
        db.collection("users").document(email).collection("Contests")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Map<String, Object> contestData = new HashMap<>(); //changed
                        contestData.put("name", contest.getName().toString());
                        contestData.put("date", formatUnixTime(contest.getStartTimeSeconds()));
                        contestData.put("duration", formatUnixTime(contest.getDurationSeconds()));
                        contestData.put("endtime", formatUnixTime(contest.getStartTimeSeconds()+contest.getDurationSeconds()));
                        db.collection("users").document(email).collection("Contests").add(contestData)
                                .addOnSuccessListener(documentReference -> {
                                    Toast.makeText(Contests.this, "Contest added!", Toast.LENGTH_SHORT).show();
                                })
                                .addOnFailureListener(e -> {
                                    Toast.makeText(Contests.this, "Failed to add contest", Toast.LENGTH_SHORT).show();
                                });

                    } else {
                        Toast.makeText(Contests.this, "Error checking contest", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void fetchleetCodeContests(){
        resetRecyclerViewVisibility();
        leetcodeViewModel.getContestsLiveData().removeObservers(this);
        leetcodeViewModel.fetchUserContests();
        leetcodeViewModel.getContestsLiveData().observe(this, new Observer<List<ContestHistory>>() {
            @Override
            public void onChanged(List<ContestHistory> contestHistories) {
                inflater = LayoutInflater.from(getApplicationContext());
                View view = inflater.inflate(R.layout.item_contest, upcomingRecyclerView, false);
                add = view.findViewById(R.id.add_btn);
                add.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
                if (contestHistories != null) {
                    List<ContestHistory> pastContests = contestHistories;
                    String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                    lcAdapter pastLeetCodeAdapter = new lcAdapter(pastContests, Contests.this);
                    pastRecyclerView.setAdapter(pastLeetCodeAdapter);
                    if (!pastContests.isEmpty()) {
                        pastRecyclerView.setVisibility(View.VISIBLE);
                    }
                } else {
                    Toast.makeText(Contests.this, "No LeetCode contests found.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void resetRecyclerViewVisibility() {
        ongoingRecyclerView.setVisibility(View.GONE);
        pastRecyclerView.setVisibility(View.GONE);
        upcomingRecyclerView.setVisibility(View.GONE);
    }

    //helper method to format unix time
    private String formatUnixTime(long unixTime) {
        long milliseconds = unixTime * 1000;
        Date date = new Date(milliseconds);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return sdf.format(date);
    }
}
