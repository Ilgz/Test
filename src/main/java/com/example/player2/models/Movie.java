package com.example.player2.models;


public class Movie  {
    public String getYear() {
        return year;
    }



    private  String year;
    private String title;
    private  String description;
    private final String  thumbnail;
    private  String streamingLink;
    private   String siteLink;

    public String getSiteLink() {
        return siteLink;
    }




    public Movie(String title,  String thumbnail,String siteLink) {
        this.title = title;
        this.thumbnail = thumbnail;
        this.siteLink=siteLink;
    } public Movie(String title, String description, String thumbnail, String streamingLink, String year) {
        this.year=year;
        this.title = title;
        this.description = description;
        this.thumbnail = thumbnail;
        this.streamingLink = streamingLink;
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



    public String getThumbnail() {
        return thumbnail;
    }









    public String getStreamingLink() {
        return streamingLink;
    }






}
