package pt.iscte.event.model;

import pt.iscte.event.Entities.Artist;

import java.time.LocalDate;

public class ArtistDTO {
    private Long id;
    private String name;
    private String musicStyle;
    private String imagePath;
    private String videoUrl;
    private String shortBio;

    public ArtistDTO(Artist artist) {
        this.id = artist.getId();
        this.name = artist.getName();
        this.musicStyle = artist.getMusicStyle();
        this.imagePath = artist.getImagePath();
        this.videoUrl = artist.getVideoUrl();
        this.shortBio = artist.getShortBio();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getShortBio() {
        return shortBio;
    }

    public void setShortBio(String shortBio) {
        this.shortBio = shortBio;
    }

    public String getMusicStyle() {
        return musicStyle;
    }

    public void setMusicStyle(String musicStyle) {
        this.musicStyle = musicStyle;
    }

}

