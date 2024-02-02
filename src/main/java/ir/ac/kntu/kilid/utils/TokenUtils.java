package ir.ac.kntu.kilid.utils;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import ir.ac.kntu.kilid.dao.ManagerRepository;
import ir.ac.kntu.kilid.dao.UserRepository;
import ir.ac.kntu.kilid.models.Manager;
import ir.ac.kntu.kilid.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TokenUtils {
    private static final BiMap<String, String> TOKEN_REPOSITORY = HashBiMap.create();//username, token
    private final ManagerRepository managerRepository;
    private final UserRepository userRepository;


    public void removeToken(String token) {
        TOKEN_REPOSITORY.inverse().remove(token);
    }

    public String generateToken() {
        return UUID.randomUUID().toString();
    }

    public void save(String username, String token) {
        TOKEN_REPOSITORY.put(username, token);
    }

    public String getUsername(String token) {
        return TOKEN_REPOSITORY.inverse().get(token);
    }

    public Manager getManager(String token) {
        String username = getUsername(token);
        return managerRepository.findByUser_Username(username).orElseThrow();
    }

    public User getUser(String token) {
        String username = getUsername(token);
        return userRepository.findByUsername(username).orElseThrow();
    }


}
