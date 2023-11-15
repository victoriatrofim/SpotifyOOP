package main.Colections_audio;

import main.Audio_file.Song;
import java.util.ArrayList;

public class Library extends ColectionAudioFiles {
    private ArrayList<Song> songs;

    public Library(String name, String type, ArrayList<Song> songs) {
        super(name, "library");
        this.songs = songs;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }
}
