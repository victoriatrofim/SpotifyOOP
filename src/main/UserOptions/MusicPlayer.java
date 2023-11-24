package main.UserOptions;

import lombok.Data;
import main.audioFile.PodcastEpisode;
import main.audioFile.Song;
@Data
public class MusicPlayer {
    private Song currentTrack;
    private boolean isPlaying;
    private PodcastEpisode lastPodcastEpisode;
    private fileio.input.PodcastEpisode currentEpisode;
    private int currentPosition;

    /**
     *
     * @return
     */
    public boolean isPlaying() {
        return isPlaying;
    }

    /**
     *
     * @param playing
     */
    public void setPlaying(final boolean playing) {
        isPlaying = playing;
    }
}

