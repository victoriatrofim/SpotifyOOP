package main.audioFile;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AudioFile {
    private String name;
    private Integer duration;
    private String type; // song or podcast


    public AudioFile(final String name, final Integer duration, final String type) {
        this.name = name;
        this.duration = duration;
        this.type = type;
    }
}

