package main.Colections_audio;

import java.util.ArrayList;
import java.util.List;

public class ColectionAudioFiles {
    private String name;
    private String type; //library, playlist or podcast

    public ColectionAudioFiles(String name, String type) {
        this.name = name;
        this.type = type;
    }
}
