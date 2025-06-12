package pt.iscte.event.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;

import jakarta.persistence.Id;

@Entity
public class Profile {

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;
    private String lastName;
    private String avatarImageUrl;
    private String favoriteBand;

    public Profile() {}

    public Profile(String firstName, String lastName, String avatarImageUrl, String favoriteBand) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatarImageUrl = avatarImageUrl;
        this.favoriteBand = favoriteBand;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAvatarImageUrl() {
        return avatarImageUrl;
    }

    public void setAvatarImageUrl(String avatarImageUrl) {
        this.avatarImageUrl = avatarImageUrl;
    }

    public String getFavoriteBand() {
        return favoriteBand;
    }

    public void setFavoriteBand(String favoriteBand) {
        this.favoriteBand = favoriteBand;
    }
}
