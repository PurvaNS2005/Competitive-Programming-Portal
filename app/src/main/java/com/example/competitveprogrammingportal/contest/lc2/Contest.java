package com.example.competitveprogrammingportal.contest.lc2; ;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Contest {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("startTime")
    @Expose
    private Integer startTime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

}