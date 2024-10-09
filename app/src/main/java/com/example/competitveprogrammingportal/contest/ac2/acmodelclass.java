package com.example.competitveprogrammingportal.contest.ac2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class acmodelclass {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("start_epoch_second")
    @Expose
    private Long startEpochSecond;
    @SerializedName("duration_second")
    @Expose
    private Long durationSecond;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("rate_change")
    @Expose
    private String rateChange;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getStartEpochSecond() {
        return startEpochSecond;
    }

    public void setStartEpochSecond(Long startEpochSecond) {
        this.startEpochSecond = startEpochSecond;
    }

    public Long getDurationSecond() {
        return durationSecond;
    }

    public void setDurationSecond(Long durationSecond) {
        this.durationSecond = durationSecond;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRateChange() {
        return rateChange;
    }

    public void setRateChange(String rateChange) {
        this.rateChange = rateChange;
    }

}