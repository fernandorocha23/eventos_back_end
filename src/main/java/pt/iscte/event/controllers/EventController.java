package pt.iscte.event.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pt.iscte.event.Entities.Artist;
import pt.iscte.event.Entities.Event;
import pt.iscte.event.Entities.EventArtist;
import pt.iscte.event.model.EventArtistDTO;
import pt.iscte.event.model.EventDTO;
import pt.iscte.event.services.EventService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/events")
    public EventDTO createEvent(@RequestBody EventDTO eventDTO) {
        return eventService.createEvent(eventDTO);
    }

    @GetMapping("/events")
    public List<EventDTO> listEvents() {
        return eventService.listEvents();
    }

    @GetMapping("/events/{id}")
    public EventDTO getEvent(@PathVariable Long id) {
        return eventService.getEvent(id);
    }

    @PostMapping("/events/{id}/artist")
    public EventArtistDTO addArtist(@PathVariable Long id,
                                    @RequestParam String nameArtist,
                                    @RequestParam String performanceDateTime) {
        return eventService.addArtistToEvent(id, nameArtist, LocalDateTime.parse(performanceDateTime));
    }
}
