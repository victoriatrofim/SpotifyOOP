package main.audioFile;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PodcastEpisodes extends AudioFile {
    private String description;

    public PodcastEpisodes(
            final String name,
            final Integer duration,
            final String type,
            final String description) {
        super(name, duration, "podcastEpisode");
        this.description = description;
    }
}