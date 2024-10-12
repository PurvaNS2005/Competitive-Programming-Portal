package com.example.competitveprogrammingportal.contest.cf2;

import static androidx.core.util.TimeUtils.formatDuration;

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

public class cfAdapter extends RecyclerView.Adapter<cfAdapter.ContestViewHolder> {
    private List<Result> contests;
    private Context context;
    private cfAdapter.OnAddButtonClickListener onAddButtonClickListener;
    public cfAdapter(Context context, List<Result> contests) {
        this.context = context;
        this.contests = contests;
    }

    public void setOnAddButtonClickListener(cfAdapter.OnAddButtonClickListener listener) {
        this.onAddButtonClickListener = listener;
    }

    @NonNull
    @Override
    public ContestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_contest, parent, false);
        return new ContestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContestViewHolder holder, int position) {
        if (position < contests.size()) {
            Result contest = contests.get(position);
            holder.titleTextView.setText(contest.getName());
            holder.startTimeTextView.setText("start time: "+ formatUnixTime(contest.getStartTimeSeconds()));
            holder.categoryTextView.setText("end time: "+formatUnixTime(contest.getStartTimeSeconds()+contest.getDurationSeconds()));
            long currentTime = System.currentTimeMillis() / 1000;
            boolean isOngoing = contest.getStartTimeSeconds() <= currentTime && (contest.getStartTimeSeconds() + contest.getDurationSeconds()) > currentTime;
            boolean isPast = (contest.getStartTimeSeconds() + contest.getDurationSeconds()) < currentTime;
            if (isOngoing || isPast) {
                holder.add.setVisibility(View.GONE);
            } else {
                holder.add.setVisibility(View.VISIBLE);
                holder.add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onAddButtonClickListener != null) {
                            onAddButtonClickListener.onAddButtonClick(contest);
                        }
                    }
                });
            }
        }
    }
    @Override
    public int getItemCount() {
        return contests.size();
    }
    public interface OnAddButtonClickListener {
        void onAddButtonClick(Result contestName);
    }
    public static class ContestViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView startTimeTextView;
        TextView categoryTextView, endTime;
        Button add;

        public ContestViewHolder(@NonNull View itemView) {
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
