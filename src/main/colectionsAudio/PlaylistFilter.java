package main.colectionsAudio;

import java.util.ArrayList;
import java.util.List;

public class PlaylistFilter {
    private List<Playlist> playlists;

    public PlaylistFilter(final List<Playlist> playlists) {
        this.playlists = playlists;
    }

    /**
     * @param playlistList
     * @param name
     * @return
     */
    public static List<Playlist> filterByName(final List<Playlist> playlistList,
                                              final String name) {
        if (playlistList == null) {
            return new ArrayList<>();
        } else {
            return playlistList.stream().filter(podcast -> podcast
                            .getName()
                            .startsWith(name))
                    .toList();
        }
    }

    /**
     *
     * @param ownerName
     * @return
     */
    public static final List<Playlist> filterByOwner(
            final List<Playlist> playlistList, final String ownerName) {
        if (playlistList == null) {
            return new ArrayList<>();
        } else {
            return playlistList.stream().filter(podcast -> podcast
                            .getOwner()
                            .equalsIgnoreCase(ownerName))
                    .toList();
        }
    }
}
