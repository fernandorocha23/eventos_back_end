package pt.iscte.event.services;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.iscte.event.Entities.*;
import pt.iscte.event.model.ArtistDTO;
import pt.iscte.event.model.CommentDTO;
import pt.iscte.event.model.EventArtistDTO;
import pt.iscte.event.model.EventDTO;
import pt.iscte.event.repositories.ArtistRepository;
import pt.iscte.event.repositories.CommentRepository;
import pt.iscte.event.repositories.EventArtistRepository;
import pt.iscte.event.repositories.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArtistServiceImpl implements ArtistService {

    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private EventService eventService;
    @Autowired
    private EventArtistRepository eventArtistRepository;

    @Override
    public List<EventArtistDTO> listArtists() {
        EventDTO event = eventService.getEvent(1L);
        return event.getArtists();
    }

    @Override
    public EventArtistDTO findArtistById(Long id) {
        //EventArtistDTO
        EventDTO event = eventService.getEvent(1L);
        return event.getArtists().stream().filter(eventArtistDTO -> eventArtistDTO.getArtist().getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public ArtistDTO createArtist(Artist artist) {
        if (artistRepository.existsByName(artist.getName()))
            throw new RuntimeException("Artist already exists");
        artistRepository.save(artist);
        return new ArtistDTO(artist);
    }

    @Override
    public List<CommentDTO> listComments(long id) {
        Artist artist = artistRepository.findById(id);
        return artist.getComments().stream().map(CommentDTO::new).collect(Collectors.toList());
    }

    @Override
    public CommentDTO createComment(long id, String commentText) {
        Artist artist = artistRepository.findById(id);
        User user = userService.getLoggedUser();
        Comment comment = new Comment(user, artist, commentText);
        artist.getComments().add(comment);
        commentRepository.save(comment);
        return new CommentDTO(comment);
    }

    @Override
    public CommentDTO addLike(Long id) {
        User user = userService.getLoggedUser();
        userRepository.save(user);
        Comment comment = commentRepository.getReferenceById(id);
        comment.addLike(user);
        commentRepository.save(comment);
        userRepository.save(user);
        return new CommentDTO(comment);
    }

    @Override
    public CommentDTO dislike(Long id) {
        User user = userService.getLoggedUser();;
        Comment comment = commentRepository.getReferenceById(id);
        userRepository.save(user);
        comment.removeLike(user);
        commentRepository.save(comment);
        return new CommentDTO(comment);
    }

    @PostConstruct
    public void init(){
        if (artistRepository.count() == 0){
            artistRepository.save(new Artist("Rosinha", "Música Popular Portuguesa", "Rosinha.jpg", "youtube.com/rosinha", "Famosa Cantora e acordeonista portuguesa"));
            artistRepository.save(new Artist("Quim Barreiros", "Pimba", "QuimBarreiros.jpeg", "youtube.com/quim_barreiros", "Lenda viva da música pimba portuguesa"));
        }
    }
}
