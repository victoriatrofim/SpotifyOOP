package main.colectionsAudio;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ColectionAudioFiles {
    private String name;
    private String owner;
    private String type; //playlist or podcast

    public ColectionAudioFiles(final String name, final String owner, final String type) {
        this.name = name;
        this.owner = owner;
        this.type = type;
    }
}
