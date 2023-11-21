package main.audioFile;

import fileio.input.SongInput;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class Song extends AudioFile {
    private String album;
    private ArrayList<String> tags;
    private String lyrics;
    private String genre;
    private Integer releaseYear;
    private String artist;

    public Song(String name, Integer duration, String type,
                String album, ArrayList<String> tags,
                String lyrics, String genre,
                Integer releaseYear, String artist) {
        super(name, duration, "song");
        this.album = album;
        this.tags = tags;
        this.lyrics = lyrics;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.artist = artist;
    }
    public Song(SongInput song) {
        super(song.getName(), song.getDuration(), "song");
        this.album = song.getAlbum();
        this.tags = song.getTags();
        this.lyrics = song.getLyrics();
        this.genre = song.getGenre();
        this.releaseYear = song.getReleaseYear();
        this.artist = song.getArtist();

    }
}
