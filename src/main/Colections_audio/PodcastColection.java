package main.Colections_audio;

import fileio.input.EpisodeInput;
import java.util.ArrayList;

public class PodcastColection extends ColectionAudioFiles {
    private String owner;
    private ArrayList<EpisodeInput> episodes;

    public PodcastColection(String name, String type, String owner, ArrayList<EpisodeInput> episodes) {
        super(name, "podcast");
        this.owner = owner;
        this.episodes = episodes;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public ArrayList<EpisodeInput> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(ArrayList<EpisodeInput> episodes) {
        this.episodes = episodes;
    }
}
