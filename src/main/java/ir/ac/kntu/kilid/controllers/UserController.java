package ir.ac.kntu.kilid.controllers;

import ir.ac.kntu.kilid.models.User;
import ir.ac.kntu.kilid.models.input.UserInputDTO;
import ir.ac.kntu.kilid.services.UserService;
import ir.ac.kntu.kilid.utils.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService service;
    private final TokenUtils tokenUtils;
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/user/register")
    private User create(@RequestBody UserInputDTO input) {
        return service.create(input);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user/list")
    private List<User> list() {
        return service.list();
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user/{id}")
    private User read(@PathVariable Long id) {
        return service.read(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/user/{id}")
    private User update(@RequestParam(required = false) String firstName,
                        @RequestParam(required = false) String newUsername,
                        @RequestParam(required = false) String newPassword, @PathVariable Long id) {
        return service.update(firstName, newUsername, newPassword , id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/user/{id}")
    private void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/user/login")
    public String login(@RequestParam String username,
                        @RequestParam String password) {
        return service.login(username, password);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/user/logout")
    public void logout(@RequestHeader String token) {
        service.logout(token);
    }
}
