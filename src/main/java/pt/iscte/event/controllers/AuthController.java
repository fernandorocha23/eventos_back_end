package pt.iscte.event.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.iscte.event.Entities.Profile;
import pt.iscte.event.Entities.User;
import pt.iscte.event.model.CommentDTO;
import pt.iscte.event.model.UserDTO;
import pt.iscte.event.services.UserService;
import pt.iscte.event.model.SignUpModel;

import java.util.List;

@RestController
public class AuthController {

    @Autowired
    UserService userService;

    @PostMapping("/user")
    public UserDTO signUp(@RequestBody SignUpModel signUpModel) {
        return userService.signUp(signUpModel);
    }

    @GetMapping("/users")
    public List<UserDTO> getUsers() {
        return userService.listOfUsers();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUSer(@PathVariable("id") Long id) {
        User user = userService.obterUser(id);
        if (user == null || user.getId() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping("/users/{id}/profile")
    public Profile getProfile(@PathVariable("id") Long id) {
        return userService.getProfile(id);
    }

    @GetMapping("user/comments")
    public List<CommentDTO> getComments() {
        return userService.getComments();
    }

    @GetMapping("/user/logado")
    public User getLoggedUser() {
        return userService.getLoggedUser();
    }
    

}
