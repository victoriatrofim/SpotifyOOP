package main.colectionsAudio;

import fileio.input.PodcastEpisode;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
public class Podcast extends ColectionAudioFiles {
    private ArrayList<PodcastEpisode> episodes;

    public Podcast(
            final String name,
            final String owner,
            final String type,
            final ArrayList<PodcastEpisode> episodes
    ) {
        super(name, owner, type);
        this.episodes = episodes;
    }

}
