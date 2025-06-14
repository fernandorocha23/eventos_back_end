package pt.iscte.event.model;

import pt.iscte.event.Entities.Comment;
import pt.iscte.event.Entities.User;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public class CommentDTO {
    private Long id;
    private String commentText;
    private String userName;
    private String artistName;
    private LocalDateTime dateTime;
    private int likesCount;
    private Set<Long> usersLiked;

    public CommentDTO(Comment comment) {
        this.id = comment.getId();
        this.commentText = comment.getCommentText();
        this.userName = comment.getUser().getUsername();
        this.artistName = comment.getArtist().getName();
        this.dateTime = comment.getDateTime();
        if (comment.getLikes() != null) {
            this.likesCount = comment.getLikes().size();
            this.usersLiked = comment.getLikes().stream()
                    .map(User::getId)
                    .collect(Collectors.toSet());
        } else {
            this.likesCount = 0;
            this.usersLiked = null;
        }

    }

    public Long getId() {
        return id;
    }

    public String getCommentText() {
        return commentText;
    }

    public String getUserName() {
        return userName;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }
    public int getLikesCount() {
        return likesCount;
    }
    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }
    public Set<Long> getUsersLiked() {
        return usersLiked;
    }
    public void setUsersLiked(Set<Long> usersLiked) {
        this.usersLiked = usersLiked;
    }
}