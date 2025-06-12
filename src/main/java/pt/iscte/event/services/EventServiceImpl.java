package pt.iscte.event.services;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.iscte.event.Entities.Artist;
import pt.iscte.event.Entities.Event;
import pt.iscte.event.Entities.EventArtist;
import pt.iscte.event.model.EventArtistDTO;
import pt.iscte.event.model.EventDTO;
import pt.iscte.event.repositories.ArtistRepository;
import pt.iscte.event.repositories.EventArtistRepository;
import pt.iscte.event.repositories.EventRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {


    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private EventArtistRepository eventArtistRepository;

    @Override
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public List<EventDTO> listEvents() {
        return eventRepository.findAll().stream().map(EventDTO::new).collect(Collectors.toList());
    }

    @Override
    public EventDTO getEvent(Long id) {
        Event event = eventRepository.getEventById(id);
        return new EventDTO(event);
    }

    @Override
    public EventArtistDTO addArtistToEvent(Long id, String artist, LocalDateTime performanceDateTime) {
        Event event = eventRepository.getEventById(id);
        EventArtist eventArtist = new EventArtist();
        eventArtist.setArtist(artistRepository.findByName(artist));
        eventArtist.setEvent(event);
        eventArtist.setPerformanceDateTime(performanceDateTime);
        eventArtistRepository.save(eventArtist);
        event.getPerfomances().add(eventArtist);
        eventRepository.save(event);
        return new EventArtistDTO(eventArtist);
    }

    @PostConstruct
    public void init() {
        if (eventRepository.count() == 0) {
            eventRepository.save(new Event(
                    "Sol da Caparica",
                    "Maior Festival de Música Lusófona de Portugal",
                    "Parque Urbano, Costa da Caparica",
                    LocalDateTime.of(2025, 8, 15, 19, 30),
                    null));
        }
        if (eventArtistRepository.count() == 0) {
            Artist rosinha = artistRepository.findByName("Rosinha");
            Artist quim = artistRepository.findByName("Quim Barreiros");
            Event event = eventRepository.findByName("Sol da Caparica");
            EventArtist performance = new EventArtist(rosinha, event, LocalDateTime.of(2025, 8, 15, 21, 30));
            EventArtist performance2 = new EventArtist(quim, event, LocalDateTime.of(2025, 8, 15, 23, 30));
            eventArtistRepository.save(performance);
            eventArtistRepository.save(performance2);
        }
    }
}
