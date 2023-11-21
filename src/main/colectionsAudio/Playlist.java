package main.colectionsAudio;

import lombok.Data;
import lombok.NoArgsConstructor;
import main.audioFile.Song;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Playlist extends ColectionAudioFiles {
    private boolean isPublic = true;
    private List<Song> playlist = new ArrayList<>();

    public Playlist(
            final String name,
            final String owner,
            final String type,
            final boolean isPublic,
            final List<Song> playlist
    ) {
        super(name, owner, "playlist");
        this.isPublic = isPublic;
        this.playlist = playlist;
    }

}
