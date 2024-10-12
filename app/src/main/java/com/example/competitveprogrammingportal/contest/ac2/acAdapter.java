package com.example.competitveprogrammingportal.contest.ac2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.competitveprogrammingportal.R;
import com.example.competitveprogrammingportal.contest.cf2.Result;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
public class acAdapter extends RecyclerView.Adapter<acAdapter.ViewHolderAC> {
    private List<acmodelclass> contests;
    private OnAddButtonClickListener onAddButtonClickListener;
    public acAdapter(Context context, List<acmodelclass> contests) {
        this.contests = contests;
    }
    public void setOnAddButtonClickListener(OnAddButtonClickListener listener) {
        this.onAddButtonClickListener = listener;
    }
    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ViewHolderAC onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contest, parent, false);
        return new ViewHolderAC(view);
    }
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull ViewHolderAC holder, int position) {
        acmodelclass contest = contests.get(position);
        holder.contestName.setText(contest.getTitle());
        holder.startTimeTextView.setText("start time: "+ formatUnixTime(contest.getStartEpochSecond()));
        holder.categoryTextView.setText("end time: "+formatUnixTime(contest.getStartEpochSecond()+contest.getDurationSecond()));

        long currentTime = System.currentTimeMillis() / 1000;
        boolean isOngoing = contest.getStartEpochSecond() <= currentTime && (contest.getStartEpochSecond() + contest.getDurationSecond()) > currentTime;
        boolean isPast = (contest.getStartEpochSecond() + contest.getDurationSecond()) <= currentTime;
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
    // returns the size of the contests list.
    @Override
    public int getItemCount() {
        return contests.size();
    }

    // Define a listener interface for the "Add" button click event
    public interface OnAddButtonClickListener {
        void onAddButtonClick(acmodelclass contestName);
    }

    public static class ViewHolderAC extends RecyclerView.ViewHolder {
        TextView contestName;
        TextView startTimeTextView;
        TextView categoryTextView;
        Button add;
        // Initialize views in the ViewHolder
        public ViewHolderAC(View itemView) {
            super(itemView);
            contestName = itemView.findViewById(R.id.contestName);
            add = itemView.findViewById(R.id.add_btn);
            startTimeTextView = itemView.findViewById(R.id.contestTime);
            categoryTextView = itemView.findViewById(R.id.contestStatus);
        }
    }
    //Helper method to convert unix time to readable format
    private String formatUnixTime(long unixTime) {
        long milliseconds = unixTime * 1000;
        Date date = new Date(milliseconds);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return sdf.format(date);
    }
}
