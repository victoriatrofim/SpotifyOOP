package main.UserOptions;

import fileio.input.LibraryInput;
import fileio.input.PodcastInput;
import fileio.input.SongInput;
import fileio.input.UserInput;
import lombok.Data;
import main.audioFile.Song;
import main.colectionsAudio.Playlist;
import main.colectionsAudio.Podcast;

import java.util.ArrayList;
@Data
public class Database {
    private ArrayList<Song> songs;
    private ArrayList<Podcast> podcasts;
    private ArrayList<User> users;
    private ArrayList<Playlist> playlists;
    public Database() {
        this.songs = new ArrayList<>();
        this.podcasts = new ArrayList<>();
        this.users = new ArrayList<>();
        this.playlists = new ArrayList<>();
    }
    public Database(final LibraryInput libraryInput) {
        this.songs = convertSongInputs(libraryInput.getSongs());
        this.podcasts = convertPodcastInputs(libraryInput.getPodcasts());
        this.users = convertUserInputs(libraryInput.getUsers());
        this.playlists = new ArrayList<>();
    }
    private ArrayList<Song> convertSongInputs(final ArrayList<SongInput> songInputs) {
        ArrayList<Song> convertedSongs = new ArrayList<>();
        for (SongInput songInput : songInputs) {
            convertedSongs.add(new Song(songInput));
        }
        return convertedSongs;
    }
    private ArrayList<Podcast> convertPodcastInputs(final ArrayList<PodcastInput> podcastInputs) {
        ArrayList<Podcast> convertedPodcasts = new ArrayList<>();
        for (PodcastInput podcastInput : podcastInputs) {
            convertedPodcasts.add(new Podcast(podcastInput));
        }
        return convertedPodcasts;
    }

    private ArrayList<User> convertUserInputs(final ArrayList<UserInput> userInputs) {
        ArrayList<User> convertedUsers = new ArrayList<>();
        for (UserInput userInput : userInputs) {
            convertedUsers.add(new User(userInput));
        }
        return convertedUsers;
    }


}
