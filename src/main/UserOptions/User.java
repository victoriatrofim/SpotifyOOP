package main.UserOptions;

import fileio.input.UserInput;
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
    private int currentTimestamp;
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

    public User(final UserInput userInput) {
        this.username = userInput.getUsername();
        this.age = userInput.getAge();
        this.city = userInput.getCity();
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
    public void execute(final String command, final Song song, final int timestamp) {
        long deltaTime = timestamp - currentTimestamp;
        currentTimestamp = timestamp;

//        switch (command) {
//            case "play":
//                musicPlayer.play(song);
//                break;
//            case "pause":
//                musicPlayer.pause();
//                break;
//            case "resume":
//                musicPlayer.resume();
//                break;
//            case "progress":
//                musicPlayer.progress((int) deltaTime);
//                break;
//            default:
//                System.out.println("Unknown command");
//        }
    }

    /**
     * @param playlist
     * @param song
     */
    public void addSongToPlaylist(final Playlist playlist, final Song song) {
        playlist.getSongs().add(song);
        }
//    public void createPlaylist(final String playlistName) {
//
//    }
}
