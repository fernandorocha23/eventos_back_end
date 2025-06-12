package pt.iscte.event.model;


import pt.iscte.event.Entities.Event;
import pt.iscte.event.Entities.EventArtist;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class EventDTO {
    private Long id;
    private String name;
    private String description;
    private String location;
    private LocalDateTime startDateTime;
    private List<EventArtistDTO> artists;


    public EventDTO(Event event) {
        this.id = event.getId();
        this.name = event.getName();
        this.description = event.getDescription();
        this.location = event.getLocation();
        this.startDateTime = event.getDateTime();
        this.artists = event.getPerfomances().stream().map(EventArtistDTO::new).collect(Collectors.toList());
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public List<EventArtistDTO> getArtists() {
        return artists;
    }

    public void setArtists(List<EventArtistDTO> artists) {
        this.artists = artists;
    }
}
