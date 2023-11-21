package main.colectionsAudio;

import java.util.List;

public class PlaylistFilter {
    private List<Playlist> playlists;

    public PlaylistFilter(final List<Playlist> playlists) {
        this.playlists = playlists;
    }

//    public List<Playlist> filterByName(String name) {
//        List<Playlist> matchingName = new ArrayList<>();
//        for (Playlist playlistIter : playlists) {
//            if (playlistIter.getName().toLowerCase().startsWith(name.toLowerCase()) ) {
//                matchingName.add(playlistIter);
//            }
//            if (matchingName.size() == 5) {
//                break;
//            }
//        }
//        return matchingName;
//    }

//    public List<Playlist> filterByOwner(String ownerName) {
//        List<Playlist> matchingUsername = new ArrayList<>();
//        for (Playlist playlistIter : playlists) {
//            if (playlistIter.getOwner().equalsIgnoreCase(ownerName) ) {
//                matchingUsername.add(playlistIter);
//            }
//            if (matchingUsername.size() == 5) {
//                break;
//            }
//        }
//        return matchingUsername;
//    }

}
