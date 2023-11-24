package main.commands.searchBarCommands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Data;
import main.Main;
import main.UserOptions.Database;
import main.audioFile.Song;
import main.colectionsAudio.Playlist;
import main.colectionsAudio.Podcast;
import main.commands.CommandInterface;
import main.test.Instruction;
import java.util.ArrayList;
@Data
public final class SelectCommand implements CommandInterface {
    private final int limit = 5;
    private ObjectMapper objectMapper;
    private static Song selectedSong;
    private static Podcast selectedPodcast;
    private static Playlist selectedPlaylist;
    private static String isSelected;

    public static String getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(final String isSelected) {
        this.isSelected = isSelected;
    }

    public static Song getSelectedSong() {
        return selectedSong;
    }

    public static void setSelectedSong(final Song selectedSong) {
        SelectCommand.selectedSong = selectedSong;
    }

    public static Podcast getSelectedPodcast() {
        return selectedPodcast;
    }

    public static void setSelectedPodcast(final Podcast selectedPodcast) {
        SelectCommand.selectedPodcast = selectedPodcast;
    }

    public static Playlist getSelectedPlaylist() {
        return selectedPlaylist;
    }

    public static void setSelectedPlaylist(final Playlist selectedPlaylist) {
        SelectCommand.selectedPlaylist = selectedPlaylist;
    }

    public SelectCommand(final Database database) {
        objectMapper = new ObjectMapper();
    }
    @Override
    public void act(final Instruction instruction) {
        if (!Main.songListAfterSearch.equals(new ArrayList<>())) {
            selectSong(instruction);
        } else if (!Main.podcastListAfterSearch.equals(new ArrayList<>())) {
            selectPodcast(instruction);
        } else if (!Main.playlistListAfterSearch.equals(new ArrayList<>())) {
            selectPlaylist(instruction);
        } else {
            arrayNodeWithMessage(instruction,
                    "Please conduct a search before making a selection.");
        }
    }

    /**
     * @param instruction
     */
    public void selectSong(final Instruction instruction) {
        if (instruction.getItemNumber()
                <= limit && instruction.getItemNumber()
                <= Main.songListAfterSearch.size()) {
            selectedSong = Main.songListAfterSearch.get(instruction.getItemNumber() - 1);
            setIsSelected("song");
            arrayNodeWithMessage(
                    instruction,
                    "Successfully selected " + selectedSong.getName() + ".");
        } else {
            arrayNodeWithMessage(instruction, "The selected ID is too high.");
        }
    }

    /**
     * @param instruction
     */
    public void selectPodcast(final Instruction instruction) {
        if (instruction.getItemNumber()
                <= limit && instruction.getItemNumber()
                <= Main.podcastListAfterSearch.size()) {
            selectedPodcast = Main.podcastListAfterSearch.get(instruction.getItemNumber() - 1);
            setIsSelected("podcast");
            arrayNodeWithMessage(
                    instruction,
                    "Successfully selected " + selectedPodcast.getName() + ".");
        } else {
            arrayNodeWithMessage(instruction, "The selected ID is too high.");
        }
    }

    /**
     * @param instruction
     */
    public void selectPlaylist(final Instruction instruction) {
        if (instruction.getItemNumber()
                <= limit && instruction.getItemNumber()
                <= Main.playlistListAfterSearch.size()) {
            selectedPlaylist = Main.playlistListAfterSearch.get(instruction.getItemNumber() - 1);
            setIsSelected("playlist");
            arrayNodeWithMessage(
                    instruction,
                    "Successfully selected " + selectedPlaylist.getName() + ".");
        } else {
            arrayNodeWithMessage(instruction, "The selected ID is too high.");
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
        Main.songListAfterSearch = new ArrayList<>();
        Main.podcastListAfterSearch = new ArrayList<>();
        Main.playlistListAfterSearch = new ArrayList<>();
    }
}
