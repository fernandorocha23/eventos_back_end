package pt.iscte.event.Entities;

import jakarta.persistence.*;
import org.springframework.http.ResponseEntity;
import pt.iscte.event.model.SignUpModel;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;
    private String email;
    @OneToOne
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public User(String username, String password, String email, Profile profile, Role role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.profile = profile;
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(SignUpModel model) {
        this.username = model.getUsername();
        this.password = model.getPassword();
    }



    public User(){
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return getRole() != null && "ADMIN".equals(getRole().getName());
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }


}


