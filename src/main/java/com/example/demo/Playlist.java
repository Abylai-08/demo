package com.example.demo;

import com.example.demo.Song;

import java.util.ArrayList;

public class Playlist extends com.example.demo.Song {
    private String playlistName;
    private ArrayList<com.example.demo.Song> songs = new ArrayList<>();

    public Playlist(String title, int duration, Artist artist, String playlistName) {
        super(title, duration, artist);
        this.playlistName = playlistName;
    }

    public String getPlaylistName() { return playlistName; }

    public void addSong(com.example.demo.Song song) { songs.add(song); }

    public ArrayList<com.example.demo.Song> getSongs() { return songs; }

    public String showPlaylist() {
        String result = "Playlist: " + playlistName + "\n";

        for (com.example.demo.Song song : songs) {
            result += song.toString() + "\n";
        }

        return result;
    }
}