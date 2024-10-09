
package com.example.competitveprogrammingportal.contest.lc2; ;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContestHistory {

    @SerializedName("attended")
    @Expose
    private Boolean attended;
    @SerializedName("rating")
    @Expose
    private Integer rating;
    @SerializedName("ranking")
    @Expose
    private Integer ranking;
    @SerializedName("trendDirection")
    @Expose
    private String trendDirection;
    @SerializedName("problemsSolved")
    @Expose
    private Integer problemsSolved;
    @SerializedName("totalProblems")
    @Expose
    private Integer totalProblems;
    @SerializedName("finishTimeInSeconds")
    @Expose
    private Integer finishTimeInSeconds;
    @SerializedName("contest")
    @Expose
    private Contest contest;

    public Boolean getAttended() {
        return attended;
    }

    public void setAttended(Boolean attended) {
        this.attended = attended;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public String getTrendDirection() {
        return trendDirection;
    }

    public void setTrendDirection(String trendDirection) {
        this.trendDirection = trendDirection;
    }

    public Integer getProblemsSolved() {
        return problemsSolved;
    }

    public void setProblemsSolved(Integer problemsSolved) {
        this.problemsSolved = problemsSolved;
    }

    public Integer getTotalProblems() {
        return totalProblems;
    }

    public void setTotalProblems(Integer totalProblems) {
        this.totalProblems = totalProblems;
    }

    public Integer getFinishTimeInSeconds() {
        return finishTimeInSeconds;
    }

    public void setFinishTimeInSeconds(Integer finishTimeInSeconds) {
        this.finishTimeInSeconds = finishTimeInSeconds;
    }

    public Contest getContest() {
        return contest;
    }

    public void setContest(Contest contest) {
        this.contest = contest;
    }

}