package ir.ac.kntu.kilid.services;

import ir.ac.kntu.kilid.dao.UserRepository;
import ir.ac.kntu.kilid.models.User;
import ir.ac.kntu.kilid.models.input.UserInputDTO;
import ir.ac.kntu.kilid.utils.TokenUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final TokenUtils tokenUtils;

    public List<User> list() {
        return userRepository.findAll();
    }

    public User read(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public User update(String newFirstName, String newUsername, String newPassword, Long id) {
        User user = userRepository.findById(id).orElseThrow();
        updateName(newFirstName, user);
        updateUsername(newUsername, user);
        updatePassword(newPassword, user);
        return userRepository.save(user);
    }

    private void updatePassword(String newPassword, User user) {
        if (newPassword !=null) {
            user.setPassword(newPassword);
        }
    }

    private void updateUsername(String newUsername, User user) {
        if (newUsername !=null && !userRepository.existsByUsername(user.getUsername())) {
            user.setUsername(newUsername);
        }
    }

    private void updateName(String name, User user) {
        if (name !=null) {
            user.setFirstName(name);
        }
    }

    public void delete(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        userRepository.delete(user);
        log.info("User with id " + id + " deleted");
    }

    public void logout(String token) {
        tokenUtils.removeToken(token);

    }

    public User create(UserInputDTO input) {
        User user = User.builder().username(input.getUsername())
                .password(input.getPassword())
                .firstName(input.getFirstName())
                .lastName(input.getLastName())
                .creationTime(new Date())
                .email(input.getEmail()).build();
        return userRepository.save(user);
    }

    public String login(String username, String password) {
        if (!userRepository.existsByUsernameAndPassword(username, password)) {
            return "get the hell outta here";
        }

        String token = tokenUtils.generateToken();
        tokenUtils.save(username, token);
        return token;
    }
}
