package pt.iscte.event.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Artist {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String musicStyle;
    private String imagePath;
    private String videoUrl;
    private String shortBio;

    @OneToMany
    List<Comment> comments;

    @OneToMany(mappedBy = "artist")
    private List<EventArtist> eventArtists;

    public Artist(){}

    public Artist(String name, String musicStyle){
        this.name = name;
        this.musicStyle = musicStyle;
    }

    public Artist(String name, String musicStyle, String imagePath, String videoUrl, String shortBio){
        this.name = name;
        this.musicStyle = musicStyle;
        this.imagePath = imagePath;
        this.videoUrl = videoUrl;
        this.shortBio = shortBio;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMusicStyle() {
        return musicStyle;
    }

    public void setMusicStyle(String musicStyle) {
        this.musicStyle = musicStyle;
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

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Comment addComment(Comment comment) {
        comments.add(comment);
        return comment;
    }

    public List<EventArtist> getEventArtists() {
        return eventArtists;
    }

    public void setEventArtists(List<EventArtist> eventArtists) {
        this.eventArtists = eventArtists;
    }

}
