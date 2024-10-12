package com.example.competitveprogrammingportal.contest.lc2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.competitveprogrammingportal.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class lcAdapter extends RecyclerView.Adapter<lcAdapter.lcViewHolder> {
    private List<ContestHistory> contests;
    private Context context;

    public lcAdapter(List<ContestHistory> contests, Context context) {
        this.contests = contests;
        this.context = context;
    }

    @NonNull
    @Override
    public lcViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_contest, parent, false);
        return new lcViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull lcViewHolder holder, int position) {
            ContestHistory contest = contests.get(position);
            holder.titleTextView.setText(contest.getContest().getTitle());
            holder.startTimeTextView.setText("start time: "+formatUnixTime(contest.getContest().getStartTime()));
            holder.categoryTextView.setText("end time: "+formatUnixTime(contest.getFinishTimeInSeconds()));
            holder.add.setVisibility(View.GONE);

    }

    @Override
    public int getItemCount() {
        return contests.size();
    }


    public static class lcViewHolder extends RecyclerView.ViewHolder{
        TextView titleTextView;
        TextView startTimeTextView;
        TextView categoryTextView;
        Button add;

        public lcViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.contestName);
            startTimeTextView = itemView.findViewById(R.id.contestTime);
            categoryTextView = itemView.findViewById(R.id.contestStatus);
            add = itemView.findViewById(R.id.add_btn);
        }
    }
    private String formatUnixTime(long unixTime) {
        long milliseconds = unixTime * 1000;
        Date date = new Date(milliseconds);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return sdf.format(date);
    }
}
