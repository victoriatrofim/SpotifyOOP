package main.commands.playerCommands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
public class PlayPauseCommand extends MusicPlayer implements CommandInterface {
    private ObjectMapper objectMapper;
    private ArrayList<Song> librarySongs;
    private ArrayList<User> userArrayList;
    private String isAfterLoadCommand = LoadCommand.getIsAfterLoadCommand();
    private Song selectedSong = SelectCommand.getSelectedSong();
    private Podcast selectedPodcast = SelectCommand.getSelectedPodcast();
    private Playlist selectedPlaylist = SelectCommand.getSelectedPlaylist();
    /**
     * @param database
     */
    public PlayPauseCommand(final Database database) {
        librarySongs = database.getSongs();
        objectMapper = new ObjectMapper();
        userArrayList = database.getUsers();
    }
    /**
     * @param instruction arrayNode of data from Json
     */
    @Override
    public void act(final Instruction instruction) {
        for (User user : userArrayList) {
            if (user.getUsername().equalsIgnoreCase(instruction.getUsername())) {
                MusicPlayer userMusicPlayer = user.getMusicPlayer();
                if ((isAfterLoadCommand.equals("true")) && (userMusicPlayer.isPlaying())) {
                    userMusicPlayer.setPlaying(false);
                    pause(instruction);
                } else if (!userMusicPlayer.isPlaying()) {
                    userMusicPlayer.setPlaying(true);
                    play(instruction);
                } else {
                    arrayNodeWithMessage(instruction,
                            "Please load a source before "
                                    + "attempting to pause or resume playback.");
                }
            }
        }
    }

    /**
     * function set player to play
     * @param instruction
     */
    public void play(final Instruction instruction) {
        super.setPlaying(true);
        if (selectedSong != null) {
            super.setCurrentTrack(selectedSong);
            arrayNodeWithMessage(instruction,
                    "Playback resumed successfully.");
        } else if (selectedPlaylist != null) {
            super.setCurrentTrack(selectedPlaylist.getSongs().get(0));
            arrayNodeWithMessage(instruction,
                    "Playback resumed successfully.");
        } else if (selectedPodcast != null) {
            // trebuie implementat get last loaded podcast
            super.setCurrentEpisode(selectedPodcast.getEpisodes().get(0));
            arrayNodeWithMessage(instruction,
                    "Playback resumed successfully.");
        }
    }

    /**
     * function set player to pause
     * @param instruction
     */
    public void pause(final Instruction instruction) {
            super.setPlaying(false);
        arrayNodeWithMessage(instruction,
                "Playback paused successfully.");
    }

    /**
     * function for printing
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
