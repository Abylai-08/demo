package com.example.demo;

public class Song {
    private String title;
    private int duration;
    private Artist artist;

    public Song(String title, int duration, Artist artist) {
        this.title = title;
        this.duration = duration;
        this.artist = artist;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public int getDuration() { return duration; }

    public Artist getArtist() { return artist; }

    public String toString() {
        return title + " (" + duration + " sec), Artist: " + artist.getName();
    }
}