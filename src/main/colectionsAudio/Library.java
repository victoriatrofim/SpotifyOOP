package main.colectionsAudio;

import fileio.input.LibraryInput;
import fileio.input.SongInput;
import main.audioFile.Song;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private static List<Song> songs;

    public Library() {
    }

    /**
     * @return
     */
    public List<Song> getSongs() {
        return songs;
    }

    /**
     * @param songs
     */
    public void setSongs(final List<Song> songs) {
        Library.songs = songs;
    }

    /**
     * @param library
     */
    public void extractSongs(final LibraryInput library) {
        songs = new ArrayList<>();
        if ((library != null) && (library.getSongs() != null)) {
            for (SongInput inputSong : library.getSongs()) {
                Song song = new Song(inputSong);
                songs.add(song);
            }
        }
    }
}
