package pt.iscte.event.services;

import pt.iscte.event.Entities.Profile;
import pt.iscte.event.Entities.User;
import pt.iscte.event.model.CommentDTO;
import pt.iscte.event.model.LoginModel;
import pt.iscte.event.model.SignUpModel;
import pt.iscte.event.model.UserDTO;

import java.util.List;

public interface UserService {
   // User login(LoginModel loginModel);

    UserDTO signUp(SignUpModel signUpModel);

    User login(LoginModel loginModel);

    User obterUser(Long id);

    Profile getProfile(Long id);

    User getLoggedUser();

    List<CommentDTO> getComments();

    List<UserDTO> listOfUsers();
}
