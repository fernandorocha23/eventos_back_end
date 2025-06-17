package pt.iscte.event.services;

import pt.iscte.event.Entities.Event;
import pt.iscte.event.model.EventArtistDTO;
import pt.iscte.event.model.EventDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface EventService {
    EventDTO createEvent(EventDTO eventDTO);

    List<EventDTO> listEvents();

    EventDTO getEvent(Long id);

    EventArtistDTO addArtistToEvent(Long id, String artist, LocalDateTime performanceDateTime);
}
