package pt.iscte.event.services;

import pt.iscte.event.Entities.Artist;
import pt.iscte.event.model.ArtistDTO;
import pt.iscte.event.model.CommentDTO;
import pt.iscte.event.model.EventArtistDTO;

import java.util.List;

public interface ArtistService {
    List<EventArtistDTO> listArtists();
    EventArtistDTO findArtistById(Long id);

    ArtistDTO createArtist(Artist artist);

    List<CommentDTO> listComments(long id);

    CommentDTO createComment(long id, String comment);

    CommentDTO addLike(Long id);

    CommentDTO dislike(Long id);
}
