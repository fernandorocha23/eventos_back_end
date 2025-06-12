package pt.iscte.event.model;

import jakarta.persistence.Transient;
import pt.iscte.event.Entities.EventArtist;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class EventArtistDTO {
    private ArtistDTO artist;
    private LocalDateTime performanceDateTime;

    public EventArtistDTO(EventArtist eventArtist) {
        this.performanceDateTime = eventArtist.getPerformanceDateTime();
        this.artist = new ArtistDTO(eventArtist.getArtist());
    }


    public LocalDateTime getPerformanceDateTime() {
        return performanceDateTime;
    }

    public void setPerformanceDateTime(LocalDateTime performanceDateTime) {
        this.performanceDateTime = performanceDateTime;
    }

    public LocalDate getPerformanceDate() {
        return performanceDateTime.toLocalDate();
    }

    public LocalTime getPerformanceTime() {
        return performanceDateTime.toLocalTime();
    }

    public ArtistDTO getArtist() {
        return artist;
    }

    public void setArtist(ArtistDTO artist) {
        this.artist = artist;
    }
}


