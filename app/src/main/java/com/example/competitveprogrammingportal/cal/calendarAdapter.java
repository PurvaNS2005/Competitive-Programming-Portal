package com.example.competitveprogrammingportal.cal;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.competitveprogrammingportal.R;
import com.example.competitveprogrammingportal.contest.Contests;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class calendarAdapter extends RecyclerView.Adapter<calendarAdapter.CalendarViewHolder> {
    private List<DocumentSnapshot> contestsList;
    private Context context;
    String daysleft = "0";
    public calendarAdapter(List<DocumentSnapshot> contestsList, Context context) {
        this.contestsList = contestsList;
        this.context = context;
    }
    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cal_item, parent, false);
        return new CalendarViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {
        DocumentSnapshot contest = contestsList.get(position);
        if(contestsList.size()==0){
            holder.titleTextView.setText("null");
        }
        String title = contest.getString("name");
        String date = contest.getString("date");
        holder.titleTextView.setText(title != null ? title : "Unnamed Contest");
        holder.startTimeTextView.setText(date != null ? date : "No start time available");
        daysleft = "" + getDaysBetweenContestAndToday(holder.startTimeTextView.getText().toString());
        holder.days.setText(daysleft + " days left");
        holder.deleteBtn.setVisibility(View.VISIBLE);
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteEnable(position);
                notifyDataSetChanged();
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, contestsList.size());
            }
        });
    }
    @Override
    public int getItemCount() {
        return contestsList.size();
    }
    public class CalendarViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView startTimeTextView;
        TextView days;
        ImageButton deleteBtn;
        public CalendarViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.calcontestName);
            startTimeTextView = itemView.findViewById(R.id.calcontestTime);
            days = itemView.findViewById(R.id.daysleft);
            deleteBtn = itemView.findViewById(R.id.delButton);
        }
    }
    public static long getDaysBetweenContestAndToday(String contestDateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        long contestTimeMillis = 0;
        try {
            Date contestDate = dateFormat.parse(contestDateString);
            if (contestDate != null) {
                contestTimeMillis = contestDate.getTime();
            }
        }catch (ParseException e) {
            e.printStackTrace();
        }
        long currentTimeMillis = Calendar.getInstance().getTimeInMillis();
        long diffInMillis = currentTimeMillis - contestTimeMillis;
        long diffInDays = TimeUnit.MILLISECONDS.toDays(diffInMillis);
        return -diffInDays;
    }
    public void deleteEnable(int position){
        if(contestsList.size()<=0){
            return;
        }
        DocumentSnapshot contest = contestsList.get(position);
        String contestId = contest.getId();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getEmail()).collection("Contests").document(contestId).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    if(contestsList.size()>0){
                        contestsList.remove(position);
                    }
                    else{
                        Toast.makeText(context, "No events found.", Toast.LENGTH_SHORT).show();
                    }
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, getItemCount());
                }
            });
    }
}
