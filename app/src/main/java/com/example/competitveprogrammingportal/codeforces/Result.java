package com.example.competitveprogrammingportal.codeforces;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("lastOnlineTimeSeconds")
    @Expose
    private Integer lastOnlineTimeSeconds;
    @SerializedName("rating")
    @Expose
    private Integer rating;
    @SerializedName("titlePhoto")
    @Expose
    private String titlePhoto;
    @SerializedName("handle")
    @Expose
    private String handle;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("contribution")
    @Expose
    private Integer contribution;
    @SerializedName("rank")
    @Expose
    private String rank;
    @SerializedName("maxRating")
    @Expose
    private Integer maxRating;
    @SerializedName("maxRank")
    @Expose
    private String maxRank;
    @SerializedName("country")
    @Expose
    private String country;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getLastOnlineTimeSeconds() {
        return lastOnlineTimeSeconds;
    }

    public void setLastOnlineTimeSeconds(Integer lastOnlineTimeSeconds) {
        this.lastOnlineTimeSeconds = lastOnlineTimeSeconds;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getTitlePhoto() {
        return titlePhoto;
    }

    public void setTitlePhoto(String titlePhoto) {
        this.titlePhoto = titlePhoto;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getContribution() {
        return contribution;
    }

    public void setContribution(Integer contribution) {
        this.contribution = contribution;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public Integer getMaxRating() {
        return maxRating;
    }

    public void setMaxRating(Integer maxRating) {
        this.maxRating = maxRating;
    }

    public String getMaxRank() {
        return maxRank;
    }

    public void setMaxRank(String maxRank) {
        this.maxRank = maxRank;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}