package ir.ac.kntu.kilid.controllers;

import ir.ac.kntu.kilid.dao.EstateAgencyRepository;
import ir.ac.kntu.kilid.dao.ManagerRepository;
import ir.ac.kntu.kilid.dao.UserRepository;
import ir.ac.kntu.kilid.models.Manager;
import ir.ac.kntu.kilid.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ManagerController {
    private final ManagerRepository managerRepository;
    private final UserRepository userRepository;
    private final EstateAgencyRepository estateAgencyRepository;
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/manager/")
    public Manager add(@RequestParam String username, @RequestParam(required = false) Long estateAgencyId) {
        User user = userRepository.findByUsername(username).orElseThrow();
        Manager manager = Manager.builder().user(user).build();
        if (estateAgencyId != null) {
            manager.setEstateAgency(estateAgencyRepository.findById(estateAgencyId).orElseThrow());
        }
        return managerRepository.save(manager);
    }
}
