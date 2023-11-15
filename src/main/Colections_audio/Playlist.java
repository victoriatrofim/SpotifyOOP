package main.Colections_audio;

import main.Audio_file.Song;

import java.util.ArrayList;
import java.util.List;

public class Playlist extends ColectionAudioFiles {
    private boolean isPublic = true;
    private String owner;
    List<Song> playlist = new ArrayList<>();

    public Playlist(String name, String type, boolean isPublic, String owner, List<Song> playlist) {
        super(name, "playlist");
        this.isPublic = isPublic;
        this.owner = owner;
        this.playlist = playlist;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<Song> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(List<Song> playlist) {
        this.playlist = playlist;
    }
}
