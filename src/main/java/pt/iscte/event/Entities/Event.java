package pt.iscte.event.Entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Event {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String description;
    private String location;
    private LocalDateTime dateTime;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<EventArtist> perfomances = new ArrayList<>();

    public Event() {}

    public Event(String name, String description, String location, LocalDateTime dateTime, List<EventArtist> perfomances) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.dateTime = dateTime;
        this.perfomances = (perfomances != null) ? perfomances : new ArrayList<>();

    }

    public Event(String name, String description, String location, LocalDateTime dateTime) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.dateTime = dateTime;
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

    public LocalDateTime getDateTime() {
        return dateTime;
    }
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public List<EventArtist> getPerfomances() {
        return perfomances;
    }

    public void setPerfomances(List<EventArtist> perfomances) {
        this.perfomances = perfomances;
    }
}
