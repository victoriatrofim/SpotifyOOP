package main.colectionsAudio;

import fileio.input.LibraryInput;
import fileio.input.PodcastInput;

import java.util.ArrayList;
import java.util.List;

public class LibraryPodcasts {
    private static List<Podcast> podcastList;
    public LibraryPodcasts() {

    }
    /**
     * @return
     */
    public List<Podcast> getPodcastList() {
        return podcastList;
    }
    /**
     * @param podcastList
     */
    public void setPodcastList(final List<Podcast> podcastList) {
        LibraryPodcasts.podcastList = podcastList;
    }

    /**
     * @param libraryInput
     */
    public static void extractPodcasts(final LibraryInput libraryInput) {
        podcastList = new ArrayList<>();
        if ((libraryInput != null) && (libraryInput.getPodcasts() != null)) {
            for (PodcastInput inputPodcast : libraryInput.getPodcasts()) {
                Podcast podcast = new Podcast(inputPodcast);
                podcastList.add(podcast);
            }
        }
    }
}
