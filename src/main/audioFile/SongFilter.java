package main.audioFile;


import java.util.ArrayList;
import java.util.List;

public class SongFilter {
    private final int limit = 5;
    private List<Song> songs;

    public SongFilter(final List<Song> songs) {
        this.songs = songs;
    }

    /**
     *
     * @param songList
     * @param name
     * @return
     */
    public List<Song> filterByName(final List<Song> songList, final String name) {
        return songList
                .stream()
                .filter(song -> song.getName().startsWith(name))
                .toList();
    }

    /**
     *
     * @param songList
     * @param albumName
     * @return
     */
    public List<Song> filterByAlbum(final List<Song> songList, final String albumName) {
        return songList
                .stream()
                .filter(song -> song.getAlbum().equalsIgnoreCase(albumName))
                .toList();
    }

    /**
     *
     * @param songList
     * @param tags
     * @return
     */
    public List<Song> filterByTags(final List<Song> songList, final ArrayList<String> tags) {
        return songList
                .stream()
                .filter(song -> song.getTags().containsAll(tags))
                .toList();
    }

    /**
     *
     * @param songList
     * @param lyrics
     * @return
     */
    public List<Song> filterByLyrics(final List<Song> songList, final String lyrics) {
        return songList
                .stream()
                .filter(song -> song.getLyrics().contains(lyrics))
                .toList();
    }

    /**
     * @param songList
     * @param genre
     * @return
     */
    public List<Song> filterByGenre(final List<Song> songList, final String genre) {
        return songList
                .stream()
                .filter(song -> song.getGenre().equalsIgnoreCase(genre))
                .toList();
    }

    /**
     *
     * @param songList
     * @param interval
     * @return
     */
    public List<Song> filterByYear(final List<Song> songList, final String interval) {
        char symbol = interval.charAt(0);
        if (symbol == '<') {
            return songList
                    .stream()
                    .filter(song ->
                            (song.getReleaseYear() < Integer.parseInt(interval.substring(1))
                            ? Boolean.TRUE : Boolean.FALSE))
                    .toList();
        } else {
            return songList
                    .stream()
                    .filter(song ->
                            (song.getReleaseYear() > Integer.parseInt(interval.substring(1))
                            ? Boolean.TRUE : Boolean.FALSE))
                    .toList();
        }
    }

    /**
     *
     * @param songList
     * @param artistName
     * @return
     */
    public List<Song> filterByArtist(final List<Song> songList, final String artistName) {
        return songList
                .stream()
                .filter(song -> song.getArtist().equalsIgnoreCase(artistName))
                .toList();
    }
}

