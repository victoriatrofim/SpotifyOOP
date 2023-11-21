package main.audioFile;

import fileio.input.SongInput;

import java.util.ArrayList;
import java.util.List;

public class SongFilter{
    private List<Song> songs;

    public SongFilter(List<Song> songs) {
        this.songs = songs;
    }

    public List<Song> filterByName(List<Song> songList, String name) {
        return songList
                .stream()
                .filter(song -> song.getName().startsWith(name))
                .toList();
    }

    public List<Song> filterByAlbum(String album) {
        List<Song> matchingAlbums = new ArrayList<Song>();
        for (Song song : songs) {
            if (song.getAlbum().equalsIgnoreCase(album)) {
                matchingAlbums.add(song);
            }
            if (matchingAlbums.size() == 5) {
                break;
            }
        }
        return matchingAlbums;
    }

    public List<Song> filterByTags(ArrayList<String> tags) {
        if (tags == null) {
            return new ArrayList<>();
        }
        List<Song> matchingTags = new ArrayList<Song>();
        for (Song song : songs) {
            if (song.getTags().containsAll(tags)) {
                matchingTags.add(song);
            }
            if (matchingTags.size() == 5) {
                break;
            }
        }
        return matchingTags;
    }

    public List<Song> filterByLyrics(String string) {
        List<Song> matchingLyrics = new ArrayList<Song>();
        for (Song song : songs) {
            if (song.getLyrics().contains(string)) {
                matchingLyrics.add(song);
            }
            if (matchingLyrics.size() == 5) {
                break;
            }
        }
        return matchingLyrics;
    }

    public List<Song> filterByGenre(String genre) {
        List<Song> matchingGenre = new ArrayList<Song>();
        for (Song song : songs) {
            if (song.getGenre().equalsIgnoreCase(genre)) {
                matchingGenre.add(song);
            }
            if (matchingGenre.size() == 5) {
                break;
            }
        }
        return matchingGenre;
    }

    public List<Song> filterByYear(String interval) {
        char symbol = interval.charAt(0);
        int referenceYear = Integer.parseInt(interval.substring(1));

        List<Song> matchingYear = new ArrayList<>();
        for (Song song : songs) {
            int songReleaseYear = song.getReleaseYear();
            if ((symbol == '<') && (songReleaseYear < referenceYear)) {
                matchingYear.add(song);
            } else if ((symbol == '>') && (songReleaseYear > referenceYear)) {
                matchingYear.add(song);
            }
            if (matchingYear.size() == 5) {
                break;
            }
        }
        return matchingYear;
    }

    public List<Song> filterByArtist(String artistName) {
        List<Song> matchingArtist = new ArrayList<Song>();
        for (Song song : songs) {
            if (song.getArtist().equalsIgnoreCase(artistName)) {
                matchingArtist.add(song);
            }
            if (matchingArtist.size() == 5) {
                break;
            }
        }
        return matchingArtist;
    }
}

