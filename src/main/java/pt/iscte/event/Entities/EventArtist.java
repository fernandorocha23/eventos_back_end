package pt.iscte.event.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
public class EventArtist {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Artist artist;

    @ManyToOne
    private Event event;

    private LocalDateTime performanceDateTime;

    public EventArtist() {
    }

    public EventArtist(Artist artist, Event event, LocalDateTime perfomanceDateTime) {
        this.artist = artist;
        this.event = event;
        this.performanceDateTime = perfomanceDateTime;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public LocalDateTime getPerformanceDateTime() {
        return performanceDateTime;
    }

    public void setPerformanceDateTime(LocalDateTime performanceDateTime) {
        this.performanceDateTime = performanceDateTime;
    }
}
