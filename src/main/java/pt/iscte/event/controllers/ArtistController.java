package pt.iscte.event.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pt.iscte.event.Entities.Artist;
import pt.iscte.event.model.ArtistDTO;
import pt.iscte.event.model.CommentDTO;
import pt.iscte.event.model.EventArtistDTO;
import pt.iscte.event.services.ArtistService;

import java.util.List;

@RestController
public class ArtistController {

    @Autowired
    private ArtistService artistService;

    @GetMapping("/artists")
    public List<EventArtistDTO> listArtists() {
        return artistService.listArtists();
    }

    @GetMapping("/artists/{id}")
    public EventArtistDTO getArtist(@PathVariable Long id) {
        return artistService.findArtistById(id);
    }

    @PostMapping("/artists")
    public ArtistDTO createArtist(@ModelAttribute Artist artist) {
        return artistService.createArtist(artist);
    }

    @GetMapping("/artists/{id}/comments")
    public List<CommentDTO> getComments(@PathVariable Long id) {
        return artistService.listComments(id);
    }

    @PostMapping("/artists/{id}/comments")
    public CommentDTO createComment(@PathVariable Long id, String commentText) {
        return artistService.createComment(id, commentText);
    }

    @PostMapping("/comments/{id}/like")
    public CommentDTO addLike(@PathVariable Long id) {
        return artistService.addLike(id);
    }

    @PostMapping("/comments/{id}/dislike")
    public CommentDTO dislike(@PathVariable Long id) {
        return artistService.dislike(id);
    }

}
