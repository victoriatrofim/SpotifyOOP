package main.colectionsAudio;

import java.util.ArrayList;
import java.util.List;

final class FilterColections {
    private final int limit = 5;

    public List<ColectionAudioFiles> filterByName(
            final ArrayList<ColectionAudioFiles> audioFile,
            final String name
    ) {
            List<ColectionAudioFiles> matchingName = new ArrayList<>();
            for (ColectionAudioFiles file : audioFile) {
                if (file.getName().toLowerCase().startsWith(name.toLowerCase())) {
                    matchingName.add(file);
                }
                if (matchingName.size() == limit) {
                    break;
                }
            }
            return matchingName;
    }

    public List<ColectionAudioFiles> filterByOwner(
            final ArrayList<ColectionAudioFiles> audioFile,
            final String ownerName
    ) {
        List<ColectionAudioFiles> matchingOwnerName = new ArrayList<>();
        for (ColectionAudioFiles file : audioFile) {
            if (file.getOwner().equalsIgnoreCase(ownerName)) {
                matchingOwnerName.add(file);
            }
            if (matchingOwnerName.size() == limit) {
                break;
            }
        }
        return matchingOwnerName;
    }
}
