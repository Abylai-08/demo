package com.example.demo;

import com.example.demo.Song;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class SongDAO {

    // CREATE
    public void saveSong(com.example.demo.Song song, int artistId) {
        String sql = "INSERT INTO song (title, duration, artist_id) VALUES (?, ?, ?)";

        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, song.getTitle());
            ps.setInt(2, song.getDuration());
            ps.setInt(3, artistId);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // READ
    public ArrayList<com.example.demo.Song> getAllSongs() {
        ArrayList<com.example.demo.Song> songs = new ArrayList<>();

        String sql =
                """
            SELECT s.title, s.duration, a.name, a.genre
            FROM song s
            JOIN artist a ON s.artist_id = a.id
        """;


        try (Connection c = DBConnection.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Artist artist = new Artist(
                        rs.getString("name"),
                        rs.getString("genre")
                );

                com.example.demo.Song song = new Song(
                        rs.getString("title"),
                        rs.getInt("duration"),
                        artist
                );

                songs.add(song);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return songs;
    }

    // UPDATE
    public void updateSongTitle(String oldTitle, String newTitle) {
        String sql = "UPDATE song SET title=? WHERE title=?";

        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, newTitle);
            ps.setString(2, oldTitle);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void deleteSong(String title) {
        String sql = "DELETE FROM song WHERE title=?";

        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, title);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
