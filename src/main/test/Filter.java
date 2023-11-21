package main.test;

import java.util.ArrayList;
import lombok.Data;

@Data
public class Filter {
    private String name;
    private String owner;
    private String genre;
    private String artist;
    private String album;
    private String lyrics;
    private String releaseYear;
    private ArrayList<String> tags;
}
