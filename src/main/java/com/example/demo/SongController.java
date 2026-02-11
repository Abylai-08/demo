package com.example.demo;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/songs")
public class SongController {

    private final SongDAO songDAO = new SongDAO();


    @GetMapping
    public List<Song> getAllSongs() {
        return songDAO.getAllSongs();
    }


    @PostMapping
    public String addSong(@RequestBody SongRequest request) {
        Song song = new Song(request.title, request.duration, null);
        songDAO.saveSong(song, request.artistId);
        return "Song added";
    }



    @PutMapping("/title")
    public String updateSongTitle(
            @RequestParam String oldTitle,
            @RequestParam String newTitle
    ) {
        songDAO.updateSongTitle(oldTitle, newTitle);
        return "Song updated";
    }


    @DeleteMapping("/{title}")
    public String deleteSong(@PathVariable String title) {
        songDAO.deleteSong(title);
        return "Song deleted";
    }
}