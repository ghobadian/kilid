package ir.ac.kntu.kilid.services;

import ir.ac.kntu.kilid.dao.UserRepository;
import ir.ac.kntu.kilid.models.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

import static ir.ac.kntu.kilid.dao.TokenRepository.TOKEN_REPOSITORY;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> list(int page, int number) {
        return userRepository.findAll();
    }

    public User create(String username, String password, String firstname, String lastName, String email) {
        User user = User.builder().username(username).password(password)//todo encode passwords
                .firstName(firstname).lastName(lastName).creationTime(new Date()).email(email).build();
        return userRepository.save(user);
    }

    public User read(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public User update(String firstName, String newUsername, String newPassword, String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        updateName(firstName, user);
        updateUsername(newUsername, username, user);
        updatePassword(newPassword, user);
        return userRepository.save(user);
    }

    private void updatePassword(String newPassword, User user) {
        if (newPassword !=null) {
            user.setPassword(newPassword);
        }
    }

    private void updateUsername(String newUsername, String username, User user) {
        if (newUsername !=null && !userRepository.existsByUsername(username)) {
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

    public String login(Long id) {
        String token = UUID.randomUUID().toString();
        return TOKEN_REPOSITORY.put(id, token);
    }

    public void logout(String token) {
        TOKEN_REPOSITORY.inverse().remove(token);
    }
}
