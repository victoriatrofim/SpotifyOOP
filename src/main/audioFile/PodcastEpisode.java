package main.audioFile;

import fileio.input.PodcastInput;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class PodcastEpisode extends AudioFile {
    private String description;

    public PodcastEpisode(
            final String name,
            final Integer duration,
            final String type,
            final String description) {
        super(name, duration, "podcastEpisode");
        this.description = description;
    }
    public PodcastEpisode(final PodcastInput podcastInput) {
        super(podcastInput.getName(),
                podcastInput.getEpisodes() != null
                        ? podcastInput.getEpisodes()
                        .get(0).getDuration()
                        : 0, "podcastEpisode");
        this.description = podcastInput.getEpisodes() != null
                ? podcastInput.getEpisodes().get(0).getDescription()
                : null;
    }
}
