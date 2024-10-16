package com.example.competitveprogrammingportal.contest.cf2;

import java.util.List;

import com.example.competitveprogrammingportal.contest.cf2.Result;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class cfmodelclass2 {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("result")
    @Expose
    private List<Result> result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

}