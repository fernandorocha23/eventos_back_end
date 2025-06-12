package pt.iscte.event.services;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pt.iscte.event.Entities.Comment;
import pt.iscte.event.Entities.Profile;
import pt.iscte.event.Entities.Role;
import pt.iscte.event.Entities.User;
import pt.iscte.event.model.CommentDTO;
import pt.iscte.event.model.LoginModel;
import pt.iscte.event.model.SignUpModel;
import pt.iscte.event.model.UserDTO;
import pt.iscte.event.repositories.CommentRepository;
import pt.iscte.event.repositories.ProfileRepository;
import pt.iscte.event.repositories.RoleRepository;
import pt.iscte.event.repositories.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    ProfileRepository profileRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    CommentRepository commentRepository;

    @Override
    public UserDTO signUp(SignUpModel signUpModel) {
        User userExists = userRepository.findByUsername(signUpModel.getUsername());
        if (userExists != null && userExists.getId() != null)
            throw new RuntimeException("User already exists");
        User user = new User(signUpModel.getUsername(), passwordEncoder.encode(signUpModel.getPassword()), signUpModel.getEmail());
        Profile profile = new Profile();
        profileRepository.save(profile);
        user.setProfile(profile);
        Role role = (Role) roleRepository.findByName("USER");
        user.setRole(role);
        role.getUsers().add(user);
        userRepository.save(user);
        return new UserDTO(user);
    }

    public User login(LoginModel loginModel) {
        User user = userRepository.findByUsername(loginModel.getUsername());
        if (user == null || user.getId() == null) {
            return null;
        }
        if (!passwordEncoder.matches(loginModel.getPassword(), user.getPassword()))
            return null;
        return user;
    }

    @Override
    public User obterUser(Long id) {
        return userRepository.getReferenceById(id);
    }

    @Override
    public Profile getProfile(Long id) {
        return userRepository.getReferenceById(id).getProfile();
    }

    @Override
    public User getLoggedUser() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userRepository.findByUsername(username) == null)
            return null;
        return userRepository.findByUsername(username);
    }

    @Override
    public List<CommentDTO> getComments() {
        User user = getLoggedUser();
        return commentRepository.findByUser(user).stream()
                .map(CommentDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> listOfUsers() {
        return userRepository.findAll().stream().map(UserDTO::new).collect(Collectors.toList());
    }



    @PostConstruct
    public void init() {
        if (roleRepository.count() == 0){
            roleRepository.save(new Role("ADMIN"));
            roleRepository.save(new Role("USER"));
        }
        if (profileRepository.count() == 0){
            profileRepository.save( new Profile("admin", "admin", "https://cdn-icons-png.flaticon.com/512/2206/2206368.png", "Secos e Molhados"));
        }
        if (userRepository.count() == 0){
            Profile profile1 = profileRepository.findAll().getFirst();
            Role adminRole = roleRepository.findAll().getFirst();
            String encodedPassword = passwordEncoder.encode("admin");
            userRepository.save(new User("admin", encodedPassword, "admin@gmail.com", profile1, adminRole));
        }

    }
}

