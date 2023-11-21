package main.UserOptions;

import lombok.Data;
import lombok.NoArgsConstructor;
import main.audioFile.Song;
import main.colectionsAudio.Playlist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
public class User {
    private String username;
    private int age;
    private String city;
    private MusicPlayer musicPlayer;
    private long currentTimestamp;
    private Map<String, Integer> playlistIndices;
    private ArrayList<Playlist> playlists = new ArrayList<>();

    public User(
            final String username,
            final int age,
            final String city,
            final MusicPlayer player
    ) {
        this.username = username;
        this.age = age;
        this.city = city;
        this.musicPlayer = new MusicPlayer();
        this.currentTimestamp = 0;
        this.playlistIndices = new HashMap<>();
        this.playlists = new ArrayList<Playlist>();
    }

    /**
     *
     * @param command
     * @param song
     * @param timestamp
     */
    public void execute(final String command, final Song song, final long timestamp) {
        long deltaTime = timestamp - currentTimestamp;
        currentTimestamp = timestamp;

        switch (command) {
            case "play":
                musicPlayer.play(song);
                break;
            case "pause":
                musicPlayer.pause();
                break;
            case "resume":
                musicPlayer.resume();
                break;
            case "progress":
                musicPlayer.progress((int) deltaTime);
                break;
            default:
                System.out.println("Unknown command");
        }
    }

    /**
     * @param playlist
     * @param user username
     * @param song
     */
    public void addToPlaylist(final Playlist playlist, final String user, final Song song) {
    if (playlist.getOwner().equalsIgnoreCase(user)) {
        playlist.getPlaylist().add(song);
        }
    }
}
