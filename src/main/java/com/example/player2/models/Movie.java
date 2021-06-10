package com.example.player2.models;


public class Movie  {
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    private String year;
    private String title;
    private String description;
    private String  thumbnail;
    private String studio;
    private String rating;
    private String streamingLink;
    private int coverPhotot;
 private String function;

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public Movie(String title, String description, String thumbnail, String streamingLink, String year, String function) {
       this.year=year;
        this.title = title;
        this.description = description;
        this.thumbnail = thumbnail;
        this.streamingLink = streamingLink;
        this.function=function;
    }
    public Movie(String title, String description, String thumbnail, String streamingLink, String year) {
        this.year=year;
        this.title = title;
        this.description = description;
        this.thumbnail = thumbnail;
        this.streamingLink = streamingLink;
    }

    public Movie(String title, String description, String thumbnail, int coverPhotot) {
        this.title = title;
        this.description = description;
        this.thumbnail = thumbnail;
        this.coverPhotot = coverPhotot;
    }

    public int getCoverPhotot() {
        return coverPhotot;
    }

    public void setCoverPhotot(int coverPhotot) {
        this.coverPhotot = coverPhotot;
    }

    public Movie(String title, String thumbnail, int coverPhotot) {
        this.title = title;
        this.thumbnail = thumbnail;
        this.coverPhotot = coverPhotot;
    }

    public Movie(String title, String thumbnail) {
        this.title = title;
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getStreamingLink() {
        return streamingLink;
    }

    public void setStreamingLink(String streamingLink) {
        this.streamingLink = streamingLink;
    }




}
