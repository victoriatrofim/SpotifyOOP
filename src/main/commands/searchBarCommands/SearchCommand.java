package main.commands.searchBarCommands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Data;
import main.Main;
import main.audioFile.Song;
import main.audioFile.SongFilter;
import main.colectionsAudio.Library;
import main.commands.CommandInterface;
import main.test.Instruction;

import java.util.ArrayList;
import java.util.List;

@Data
public class SearchCommand implements CommandInterface {

    private SongFilter songFilter;
    private Library library;
    private ObjectMapper objectMapper;
    private Instruction instruction;
    public SearchCommand() {
        library = new Library();
        songFilter = new SongFilter(library.getSongs());
        objectMapper = new ObjectMapper();
    }

    @Override
    public void act(final Instruction instruction) {
        this.instruction = instruction;
        ActByType();
    }

    private void ActByType() {
        switch (instruction.getType()) {
            case "song": searchSong(); break;
            case "podcast": searchPodcast(); break;
            default: break;
        }
    }

    private void searchSong() {
        if (instruction.getFilters() != null) {
            if (instruction.getFilters().getName() != null) {
                List<Song> songList = songFilter.filterByName(library.getSongs(), instruction.getFilters().getName());
                SongListToArrayNode(songList);
            }
        }
    }

    private void searchPodcast() {

    }

    private void SongListToArrayNode(List<Song> songList) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        List<String> resultSongs = new ArrayList<>();
        songList.forEach(song -> {
            if (resultSongs.size() < 5) {
                resultSongs.add(song.getName());
            }
        });
        objectNode.set("command", objectMapper.valueToTree(instruction.getCommand()));
        objectNode.set("user", objectMapper.valueToTree(instruction.getUsername()));
        objectNode.set("timestamp", objectMapper.valueToTree(instruction.getTimestamp()));
        objectNode.set("message", objectMapper.valueToTree("Search returned " + resultSongs.size() + " results"));
        objectNode.set("results", objectMapper.valueToTree(resultSongs));
        Main.output.add(objectNode);
    }



}
