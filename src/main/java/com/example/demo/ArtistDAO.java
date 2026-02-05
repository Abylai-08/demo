package com.example.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ArtistDAO {

    public int saveArtist(Artist artist) {
        String sql = "INSERT INTO artist (name, genre) VALUES (?, ?) RETURNING id";

        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, artist.getName());
            ps.setString(2, artist.getGenre());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1); // id артиста
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}