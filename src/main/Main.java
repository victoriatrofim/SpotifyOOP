package main;

import checker.Checker;
import checker.CheckerConstants;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.input.LibraryInput;
import lombok.Data;
import main.UserOptions.Database;
import main.audioFile.Song;
import main.colectionsAudio.LibrarySongs;
import main.colectionsAudio.LibraryPodcasts;
import main.colectionsAudio.Playlist;
import main.colectionsAudio.Podcast;
import main.commands.CommandGenerator;
import main.commands.CommandInterface;
import main.test.Instruction;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Data
/**
 * The entry point to this homework. It runs the checker that tests your implentation.
 */
public final class Main {
    static final String LIBRARY_PATH = CheckerConstants.TESTS_PATH + "library/library.json";
    public static ArrayNode output;
    public static List<Song> songListAfterSearch;
    public static List<Podcast> podcastListAfterSearch;
    public static List<Playlist> playlistListAfterSearch;

    /**
     * for coding style
     */
    private Main() {
    }

    /**
     * DO NOT MODIFY MAIN METHOD
     * Call the checker
     * @param args from command line
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void main(final String[] args) throws IOException {
        File directory = new File(CheckerConstants.TESTS_PATH);
        Path path = Paths.get(CheckerConstants.RESULT_PATH);

        if (Files.exists(path)) {
            File resultFile = new File(String.valueOf(path));
            for (File file : Objects.requireNonNull(resultFile.listFiles())) {
                file.delete();
            }
            resultFile.delete();
        }
        Files.createDirectories(path);

        for (File file : Objects.requireNonNull(directory.listFiles())) {
            if (file.getName().startsWith("library")) {
                continue;
            }

            String filepath = CheckerConstants.OUT_PATH + file.getName();
            File out = new File(filepath);
            boolean isCreated = out.createNewFile();
            if (isCreated) {
                action(file.getName(), filepath);
            }
        }

        Checker.calculateScore();
    }

    /**
     * @param filePathInput for input file
     * @param filePathOutput for output file
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void action(final String filePathInput,
                              final String filePathOutput) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        LibraryInput libraryInput
                = objectMapper.readValue(new File(LIBRARY_PATH), LibraryInput.class);

        LibrarySongs library = new LibrarySongs();
        library.extractSongs(libraryInput);
        LibraryPodcasts libraryPodcasts = new LibraryPodcasts();
        libraryPodcasts.extractPodcasts(libraryInput);
        // TODO add your implementation


   //     String inputFile = CheckerConstants.TESTS_PATH + "test02_playPause_song.json";

   //    String inputFile = CheckerConstants.TESTS_PATH + "test01_searchBar_songs_podcasts.json";
         String inputFile = CheckerConstants.TESTS_PATH + filePathInput;
        ArrayList<Instruction> instructionArrayList
                = objectMapper.readValue(
                        new File(inputFile),
                        new TypeReference<ArrayList<Instruction>>() { }
                );
        output = objectMapper.createArrayNode();
        CommandGenerator commandGenerator = new CommandGenerator();
        CommandInterface command;
        songListAfterSearch = new ArrayList<>();
        podcastListAfterSearch = new ArrayList<>();
        playlistListAfterSearch = new ArrayList<>();
        Database database = new Database(libraryInput);
        for (Instruction instruction : instructionArrayList) {
            command = commandGenerator.generate(instruction.getCommand(), database);
            command.act(instruction);
        }

        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(filePathOutput), output);
    }
}
