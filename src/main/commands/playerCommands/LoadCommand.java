package main.commands.playerCommands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.input.PodcastEpisode;
import lombok.Data;
import main.Main;
import main.UserOptions.Database;
import main.UserOptions.MusicPlayer;
import main.UserOptions.User;
import main.audioFile.Song;
import main.colectionsAudio.Playlist;
import main.colectionsAudio.Podcast;
import main.commands.CommandInterface;
import main.commands.searchBarCommands.SelectCommand;
import main.test.Instruction;
import java.util.ArrayList;

@Data
public class LoadCommand extends MusicPlayer implements CommandInterface {
    private ObjectMapper objectMapper;
    private ArrayList<Song> songArrayList;
    private ArrayList<Podcast> podcastArrayList;
    private ArrayList<Playlist> playlistArrayList;
    private ArrayList<User> userArrayList;
    private static String isAfterLoadCommand = "false";
    private Song selectedSong = SelectCommand.getSelectedSong();
    private Podcast selectedPodcast = SelectCommand.getSelectedPodcast();
    private Playlist selectedPlaylist = SelectCommand.getSelectedPlaylist();
    private String isSelected = SelectCommand.getIsSelected();

    public static String getIsAfterLoadCommand() {
        return isAfterLoadCommand;
    }

    public static void setIsLoading(final String isLoaded) {
        LoadCommand.isAfterLoadCommand = isLoaded;
    }

    /**
     * @param database
     */
    public LoadCommand(final Database database) {
        songArrayList = database.getSongs();
        podcastArrayList = database.getPodcasts();
        playlistArrayList = database.getPlaylists();
        userArrayList = database.getUsers();
        objectMapper = new ObjectMapper();
    }

    /**
     * @param instruction arrayNode of data from Json
     */
    @Override
    public void act(final Instruction instruction) {
        int startLoadTime = instruction.getTimestamp();
        switch (isSelected) {
            case "song":
                if (selectedSong != null) {
                    setIsLoading("true");
                    setSelectedSong(null);
                    loadSong(selectedSong, instruction);
                } else {
                    arrayNodeWithMessage(instruction,
                            "You can't load an empty audio collection!");
                }
                setIsSelected(null);
                break;
            case "podcast":
                if (selectedPodcast != null) {
                    setIsLoading("true");
                    setSelectedPodcast(null);
                    loadPodcast(selectedPodcast, instruction);
                } else {
                    arrayNodeWithMessage(instruction,
                            "You can't load an empty audio collection!");
                }
                setIsSelected(null);
                break;
            case "playlist":
                if (selectedPlaylist != null) {
                    setIsLoading("true");
                    setSelectedPodcast(null);
                    loadPlaylist(selectedPlaylist, instruction);
                } else {
                    arrayNodeWithMessage(instruction,
                            "You can't load an empty audio collection!");
                }
                setIsSelected(null);
                break;
            default:
                arrayNodeWithMessage(instruction,
                        "Please select a source before attempting to load.");
                break;
        }
    }

    /**
     * @param song
     * @param instruction
     */
    public void loadSong(final Song song, final Instruction instruction) {
                for (User user : userArrayList) {
                    if (user.getUsername().equalsIgnoreCase(instruction.getUsername())) {
                        MusicPlayer userMusicPlayer = user.getMusicPlayer();
                        userMusicPlayer.setCurrentTrack(song);
                        userMusicPlayer.setPlaying(true);
                        userMusicPlayer.setCurrentPosition(instruction.getTimestamp());
                        arrayNodeWithMessage(instruction,
                                "Playback loaded successfully.");
                    }
                }
            }

    /**
     * @param podcast
     * @param instruction
     */
    public void loadPodcast(final Podcast podcast, final Instruction instruction) {
            if (podcast == null) {
                return;
            }
            if (podcast.getEpisodes() == null) {
                return;
            }
        for (User user : userArrayList) {
            if (user.getUsername().equalsIgnoreCase(instruction.getUsername())) {
                MusicPlayer userMusicPlayer = user.getMusicPlayer();
                if (!podcast.getEpisodes().isEmpty()) {
                    // Check if the user has a last played episode
                    String lastPlayedEpisodeName = userMusicPlayer.
                            getLastPodcastEpisode()
                            .getName();
                    fileio.input.PodcastEpisode lastPlayedEpisode = null;

                    // Find the last played episode in the podcast
                    for (fileio.input.PodcastEpisode episode : podcast.getEpisodes()) {
                        if (episode.getName().equals(lastPlayedEpisodeName)) {
                            lastPlayedEpisode = episode;
                            break;
                        }
                    }
                    PodcastEpisode startingEpisode = (lastPlayedEpisode != null)
                            ? lastPlayedEpisode
                            : podcast.getEpisodes().get(0);
                    userMusicPlayer.setCurrentEpisode(startingEpisode);
                    userMusicPlayer.setCurrentPosition(instruction.getTimestamp());
                    userMusicPlayer.setPlaying(true);
                    arrayNodeWithMessage(instruction, "Playback loaded successfully.");
                } else {
                    arrayNodeWithMessage(instruction,
                            "You can't load an empty audio collection!");
                }
            }
        }
    }

    /**
     * @param playlist
     * @param instruction
     */
    public void loadPlaylist(final Playlist playlist, final Instruction instruction) {
        for (User user : userArrayList) {
            if (user.getUsername().equalsIgnoreCase(instruction.getUsername())) {
                MusicPlayer userMusicPlayer = user.getMusicPlayer();
                if (!playlist.getSongs().isEmpty()) {
                    userMusicPlayer.setCurrentTrack(playlist.getSongs().get(0));
                    userMusicPlayer.setPlaying(true);
                    userMusicPlayer.setCurrentPosition(instruction.getTimestamp());
                    arrayNodeWithMessage(instruction,
                            "Playback loaded successfully.");
                }
            }
        }
    }

    /**
     * @param instruction
     * @param message
     */
    private void arrayNodeWithMessage(final Instruction instruction, final String message) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.set("command", objectMapper.valueToTree(instruction.getCommand()));
        objectNode.set("user", objectMapper.valueToTree(instruction.getUsername()));
        objectNode.set("timestamp", objectMapper.valueToTree(instruction.getTimestamp()));
        objectNode.set("message", objectMapper.valueToTree(message));
        Main.output.add(objectNode);
    }
}


