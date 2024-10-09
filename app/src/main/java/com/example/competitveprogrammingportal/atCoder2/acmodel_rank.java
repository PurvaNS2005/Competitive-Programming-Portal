package com.example.competitveprogrammingportal.atCoder2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class acmodel_rank {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("rank")
    @Expose
    private Integer rank;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

}