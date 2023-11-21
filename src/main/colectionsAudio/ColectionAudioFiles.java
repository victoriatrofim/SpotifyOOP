package main.colectionsAudio;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ColectionAudioFiles {
    private String name;
    private String owner;
    private String type; //playlist or podcast

    public ColectionAudioFiles(final String name, final String owner, final String type) {
        this.name = name;
        this.owner = owner;
        this.type = type;
    }

    /* private static List<Song> songs;
    private static List<Podcast> podcasts;
    public List<Song> extractSongs(LibraryInput library) {
        if((library != null) && (library.getSongs() != null)) {
            for(SongInput inputSong : library.getSongs()) {
                Song song = new Song(inputSong);
                songs.add(song);
            }
        }
        return songs;  !!!!! added in library
    }
    private List<Podcast> extractPodcasts(LibraryInput libraryInput) {
        List<Podcast> extractedPodcasts = new ArrayList<>();
        if (libraryInput != null && libraryInput.getPodcasts() != null) {
            for (PodcastInput inputPodcast : libraryInput.getPodcasts()) {
                Podcast podcast = new Podcast(inputPodcast);
                extractedPodcasts.add(podcast);
            }
        }
        return extractedPodcasts;
    }*/
}
