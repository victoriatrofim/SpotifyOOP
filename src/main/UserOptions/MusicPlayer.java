package main.UserOptions;

import main.audioFile.Song;

class MusicPlayer {
    private Song currentTrack;
    private int currentPosition;
    private boolean isPlaying;

    MusicPlayer() {
        this.currentTrack = null;
        this.currentPosition = 0;
        this.isPlaying = false;
    }

    public void play(final Song song) {
        this.currentTrack = song;
        this.currentPosition = 0;
        this.isPlaying = true;
        System.out.println("Playing: " + currentTrack + " at position: " + currentPosition);
    }

    public void pause() {
        if (isPlaying) {
            isPlaying = false;
            System.out.println("Paused: " + currentTrack + " at position: " + currentPosition);
        }
    }

    public void resume() {
        if (!isPlaying) {
            isPlaying = true;
            System.out.println("Resumed: " + currentTrack + " from position: " + currentPosition);
        }
    }

    public void progress(final int seconds) {
        if (isPlaying) {
            currentPosition += seconds;
            System.out.println("Current position: " + currentPosition);
        }
    }
}

