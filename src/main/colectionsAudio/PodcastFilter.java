package main.colectionsAudio;


import java.util.ArrayList;
import java.util.List;

final class PodcastFilter {
    private List<Podcast> podcasts;
    private final int limit = 5;

   PodcastFilter(final List<Podcast> podcasts) {
        this.podcasts = podcasts;
    }

    /**
     *
     * @param name
     * @return
     */
    public List<Podcast> filterByName(final String name) {
        List<Podcast> matchingName = new ArrayList<>();
        for (Podcast playlistIter : podcasts) {
            if (playlistIter.getName().toLowerCase().startsWith(name.toLowerCase())) {
                matchingName.add(playlistIter);
            }
            if (matchingName.size() == limit) {
                break;
            }
        }
        return matchingName;
    }

    public List<Podcast> filterByOwner(final String ownerName) {
        List<Podcast> matchingUsername = new ArrayList<>();
        for (Podcast playlistIter : podcasts) {
            if (playlistIter.getOwner().equalsIgnoreCase(ownerName)) {
                matchingUsername.add(playlistIter);
            }
            if (matchingUsername.size() == limit) {
                break;
            }
        }
        return matchingUsername;
    }

}

