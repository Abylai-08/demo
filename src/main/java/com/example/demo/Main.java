package com.example.demo;

import com.example.demo.Song;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        if (DBConnection.getConnection() != null) {
            System.out.println("DB connected!");
        } else {
            System.out.println("DB connection failed");
            return;
        }
        Scanner sc = new Scanner(System.in);

        Artist shiza = new Artist("SHIZA", "RAP");
        Artist lennon = new Artist("Lennon", "Rock");

        com.example.demo.Song song1 = new  com.example.demo.Song("TER", 183, shiza);
        com.example.demo.Song song2 = new  com.example.demo.Song("UADE", 253, shiza);
        com.example.demo.Song song3 = new  com.example.demo.Song("Imagine", 200, lennon);

        ArtistDAO artistDAO = new ArtistDAO();
        SongDAO songDAO = new SongDAO();

// сохраняем артистов
        int shizaId = artistDAO.saveArtist(shiza);
        int lennonId = artistDAO.saveArtist(lennon);

// сохраняем песни
        songDAO.saveSong(song1, shizaId);
        songDAO.saveSong(song2, shizaId);
        songDAO.saveSong(song3, lennonId);

// читаем песни из БД
        System.out.println("\nSongs from DB:");
        for (Song s : songDAO.getAllSongs()) {
            System.out.println(s);
        }

        Playlist playlist = new Playlist("TER", 183, shiza, "My Playlist");
        playlist.addSong(song1);
        playlist.addSong(song2);
        playlist.addSong(song3);

        System.out.println("Current playlist:");
        System.out.println(playlist.showPlaylist());

        System.out.print("Enter song number to rename: ");
        int index = sc.nextInt();
        sc.nextLine();

        if (index >= 1 && index <= playlist.getSongs().size()) {
            System.out.print("Enter new title: ");
            String newTitle = sc.nextLine();
            playlist.getSongs().get(index - 1).setTitle(newTitle);

            System.out.println("\nUpdated playlist:");
            System.out.println(playlist.showPlaylist());
        } else {
            System.out.println("Invalid song number!");
        }

        sc.close();
    }
}