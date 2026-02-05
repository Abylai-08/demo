package com.example.demo;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/songs")
public class SongController {

    private final SongDAO songDAO = new SongDAO();

    // =====================
    // GET — получить все песни (JSON)
    // =====================
    @GetMapping
    public List<Song> getAllSongs() {
        return songDAO.getAllSongs();
    }

    // =====================
    // POST — добавить песню
    // =====================
    @PostMapping
    public String addSong(@RequestBody Song song) {
        // ВАЖНО: artist должен уже быть в БД
        // artistId берётся вручную
        int artistId = 1; // ← можно изменить
        songDAO.saveSong(song, artistId);
        return "Song added";
    }

    // =====================
    // PUT — обновить название песни
    // =====================
    @PutMapping("/title")
    public String updateSongTitle(
            @RequestParam String oldTitle,
            @RequestParam String newTitle
    ) {
        songDAO.updateSongTitle(oldTitle, newTitle);
        return "Song updated";
    }

    // =====================
    // DELETE — удалить песню по названию
    // =====================
    @DeleteMapping("/{title}")
    public String deleteSong(@PathVariable String title) {
        songDAO.deleteSong(title);
        return "Song deleted";
    }
}