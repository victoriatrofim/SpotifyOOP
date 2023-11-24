package main.colectionsAudio;

import java.util.List;

public class PodcastFilter {
    private List<Podcast> podcasts;
    private final int limit = 5;

   public PodcastFilter(final List<Podcast> podcasts) {
        this.podcasts = podcasts;
    }

    /**
     *
     * @param podcastList
     * @param name
     * @return
     */
    public List<Podcast> filterByName(final List<Podcast> podcastList, final String name) {
        return podcastList.stream().filter(podcast -> podcast
                        .getName()
                        .startsWith(name))
                .toList();
    }

    /**
     *
     * @param podcastList
     * @param ownerName
     * @return
     */
    public final List<Podcast> filterByOwner(
            final List<Podcast> podcastList, final String ownerName) {
        return podcastList.stream().filter(podcast -> podcast
                .getOwner()
                .equalsIgnoreCase(ownerName))
                .toList();
    }
}
