package main.test;
import lombok.Data;
@Data
public class Instruction {
    private String command;
    private String username;
    private int timestamp;
    private String type;
    private Filter filters;
    private String playlistName;
    private int itemNumber;
    private int playlistId;
    private int seed;
}
