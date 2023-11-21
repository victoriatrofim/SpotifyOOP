package main.audioFile;

public class PodcastEpisode extends AudioFile {
    private String description;

    public PodcastEpisode(String name, Integer duration, String type, String description) {
        super(name, duration, "podcastEpisode");
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
