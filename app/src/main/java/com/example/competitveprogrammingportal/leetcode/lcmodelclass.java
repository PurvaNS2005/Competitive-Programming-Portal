
package com.example.competitveprogrammingportal.leetcode;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class lcmodelclass {
    @SerializedName("totalSolved")
    @Expose
    private Integer totalSolved;
    @SerializedName("totalQuestions")
    @Expose
    private Integer totalQuestions;
    @SerializedName("easySolved")
    @Expose
    private Integer easySolved;
    @SerializedName("totalEasy")
    @Expose
    private Integer totalEasy;
    @SerializedName("mediumSolved")
    @Expose
    private Integer mediumSolved;
    @SerializedName("totalMedium")
    @Expose
    private Integer totalMedium;
    @SerializedName("hardSolved")
    @Expose
    private Integer hardSolved;
    @SerializedName("totalHard")
    @Expose
    private Integer totalHard;
    @SerializedName("ranking")
    @Expose
    private Integer ranking;
    @SerializedName("contributionPoint")
    @Expose
    private Integer contributionPoint;
    @SerializedName("reputation")
    @Expose
    private Integer reputation;
    public Integer getTotalSolved() {
        return totalSolved;
    }

    public void setTotalSolved(Integer totalSolved) {
        this.totalSolved = totalSolved;
    }
    public Integer getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(Integer totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public Integer getEasySolved() {
        return easySolved;
    }

    public void setEasySolved(Integer easySolved) {
        this.easySolved = easySolved;
    }

    public Integer getTotalEasy() {
        return totalEasy;
    }

    public void setTotalEasy(Integer totalEasy) {
        this.totalEasy = totalEasy;
    }

    public Integer getMediumSolved() {
        return mediumSolved;
    }

    public void setMediumSolved(Integer mediumSolved) {
        this.mediumSolved = mediumSolved;
    }

    public Integer getTotalMedium() {
        return totalMedium;
    }

    public void setTotalMedium(Integer totalMedium) {
        this.totalMedium = totalMedium;
    }

    public Integer getHardSolved() {
        return hardSolved;
    }

    public void setHardSolved(Integer hardSolved) {
        this.hardSolved = hardSolved;
    }

    public Integer getTotalHard() {
        return totalHard;
    }

    public void setTotalHard(Integer totalHard) {
        this.totalHard = totalHard;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public Integer getContributionPoint() {
        return contributionPoint;
    }

    public void setContributionPoint(Integer contributionPoint) {
        this.contributionPoint = contributionPoint;
    }

    public Integer getReputation() {
        return reputation;
    }

    public void setReputation(Integer reputation) {
        this.reputation = reputation;
    }

}