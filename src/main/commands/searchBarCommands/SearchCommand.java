package main.commands.searchBarCommands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Data;
import main.Main;
import main.UserOptions.Database;
import main.audioFile.Song;
import main.audioFile.SongFilter;
import main.colectionsAudio.*;
import main.commands.CommandInterface;
import main.test.Instruction;
import java.util.ArrayList;
import java.util.List;

@Data
public class SearchCommand implements CommandInterface {
    private final int limit = 5;
    private SongFilter songFilter;
    private LibraryPodcasts libraryPodcasts;
    private PodcastFilter podcastFilter;
    private LibrarySongs library;
    private ObjectMapper objectMapper;
    private Instruction instruction;
    private ArrayList<Playlist> playlistArrayList;
    public SearchCommand(final Database database) {
        library = new LibrarySongs();
        songFilter = new SongFilter(library.getSongs());
        libraryPodcasts = new LibraryPodcasts();
        podcastFilter = new PodcastFilter(libraryPodcasts.getPodcastList());
        objectMapper = new ObjectMapper();
    }

    /**
     * @param commandInstructions
     */
    @Override
    public void act(final Instruction commandInstructions) {
        this.instruction = commandInstructions;
        actByType();
    }

    private void actByType() {
        switch (instruction.getType()) {
            case "song": searchSong(); break;
            case "playlist": searchPlaylist(); break;
            case "podcast": searchPodcast(); break;
            default: break;
        }
    }

    private void searchSong() {
        if (instruction.getFilters() != null) {
            List<Song> songList = library.getSongs();
            if (instruction.getFilters().getName() != null) {
                songList = songFilter.filterByName(
                                songList, instruction.getFilters().getName());
            }
            if (instruction.getFilters().getAlbum() != null) {
                songList = songFilter.filterByAlbum(
                        songList, instruction.getFilters().getAlbum());
            }
            if (instruction.getFilters().getTags() != null) {
                songList = songFilter.filterByTags(
                        songList, instruction.getFilters().getTags());
            }
            if (instruction.getFilters().getLyrics() != null) {
                songList = songFilter.filterByLyrics(
                        songList, instruction.getFilters().getLyrics());
            }
            if (instruction.getFilters().getGenre() != null) {
                songList = songFilter.filterByGenre(
                        songList, instruction.getFilters().getGenre());
            }
            if (instruction.getFilters().getReleaseYear() != null) {
                songList = songFilter.filterByYear(
                        songList, instruction.getFilters().getReleaseYear());
            }
            if (instruction.getFilters().getArtist() != null) {
                songList = songFilter.filterByArtist(
                        songList, instruction.getFilters().getArtist());
            }
            songListToArrayNode(songList);

        }
    }

    private void searchPodcast() {
        if (instruction.getFilters() != null) {
            List<Podcast> podcastList = libraryPodcasts.getPodcastList();
            if (instruction.getFilters().getName() != null) {
                podcastList = podcastFilter.filterByName(
                        podcastList, instruction.getFilters().getName());
            }
            if (instruction.getFilters().getOwner() != null) {
                podcastList = podcastFilter.filterByOwner(
                        podcastList, instruction.getFilters().getOwner());
            }
            podcastListToArrayNode(podcastList);
        }
    }
    private void searchPlaylist() {
        if (instruction.getFilters() != null) {
            List<Playlist> playlistList = playlistArrayList;
            if (instruction.getFilters().getName() != null) {
                playlistList = PlaylistFilter.filterByName(
                        playlistList, instruction.getFilters().getName());
            }
            if (instruction.getFilters().getOwner() != null) {
                playlistList = PlaylistFilter.filterByOwner(
                        playlistList, instruction.getFilters().getOwner());
            }
            playlistListToArrayNode(playlistList);
        }
    }
    private void songListToArrayNode(final List<Song> songList) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        List<String> resultNameSongList = new ArrayList<>();
        songList.forEach(song -> {
            if (resultNameSongList.size() < limit) {
                Main.songListAfterSearch.add((song));
                resultNameSongList.add(song.getName());
            }
        });
        objectNode.set("command", objectMapper.valueToTree(instruction.getCommand()));
        objectNode.set("user", objectMapper.valueToTree(instruction.getUsername()));
        objectNode.set("timestamp", objectMapper.valueToTree(instruction.getTimestamp()));
        objectNode.set("message",
                objectMapper.valueToTree(
                        "Search returned " + resultNameSongList.size() + " results"));
        objectNode.set("results", objectMapper.valueToTree(resultNameSongList));
        Main.output.add(objectNode);

    }

    private void podcastListToArrayNode(final List<Podcast> podcastList) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        List<String> resultNamePodcastList = new ArrayList<>();
        podcastList.forEach(podcast -> {
            if (resultNamePodcastList.size() < limit) {
                Main.podcastListAfterSearch.add((podcast));
                resultNamePodcastList.add(podcast.getName());
            }
        });
        objectNode.set("command", objectMapper.valueToTree(instruction.getCommand()));
        objectNode.set("user", objectMapper.valueToTree(instruction.getUsername()));
        objectNode.set("timestamp", objectMapper.valueToTree(instruction.getTimestamp()));
        objectNode.set("message",
                objectMapper.valueToTree(
                        "Search returned " + resultNamePodcastList.size() + " results"));
        objectNode.set("results", objectMapper.valueToTree(resultNamePodcastList));
        Main.output.add(objectNode);

    }
    private void playlistListToArrayNode(final List<Playlist> playlistList) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        List<String> resultNamePlaylistList = new ArrayList<>();
        playlistList.forEach(playlist -> {
            if (resultNamePlaylistList.size() < limit) {
                Main.playlistListAfterSearch.add((playlist));
                resultNamePlaylistList.add(playlist.getName());
            }
        });
        objectNode.set("command", objectMapper.valueToTree(instruction.getCommand()));
        objectNode.set("user", objectMapper.valueToTree(instruction.getUsername()));
        objectNode.set("timestamp", objectMapper.valueToTree(instruction.getTimestamp()));
        objectNode.set("message",
                objectMapper.valueToTree(
                        "Search returned " + resultNamePlaylistList.size() + " results"));
        objectNode.set("results", objectMapper.valueToTree(resultNamePlaylistList));
        Main.output.add(objectNode);

    }
}
