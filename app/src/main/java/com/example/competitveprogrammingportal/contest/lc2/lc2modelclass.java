package com.example.competitveprogrammingportal.contest.lc2; ;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class lc2modelclass {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("contestHistory")
    @Expose
    private List<ContestHistory> contestHistory;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<ContestHistory> getContestHistory() {
        return contestHistory;
    }

    public void setContestHistory(List<ContestHistory> contestHistory) {
        this.contestHistory = contestHistory;
    }

}