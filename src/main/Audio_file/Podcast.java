package main.Audio_file;

public class Podcast extends AudioFile {
    private String owner;
    private String description;

    public Podcast(String name, Integer duration, String type, String owner, String description) {
        super(name, duration, "podcast");
        this.owner = owner;
        this.description = description;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
