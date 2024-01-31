package ir.ac.kntu.kilid.controllers;

import ir.ac.kntu.kilid.models.User;
import ir.ac.kntu.kilid.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService service;
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/user/register")
    private User create(@RequestParam String username, @RequestParam String password, @RequestParam String name,
                        @RequestParam String phone, @RequestParam String nationalId) {
        return service.create(username, password, name, phone, nationalId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user/list")
    private List<User> list(@RequestHeader String token, @RequestParam int page, @RequestParam int number) {
        return service.list(page, number);
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user/{id}")
    private User read(@PathVariable Long id, @RequestHeader String token) {
        return service.read(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/user")
    private User update(@RequestParam(required = false) String firstName,
                        @RequestParam(required = false) String newUsername,
                        @RequestParam(required = false) String newPassword,
                        @RequestHeader String token) {
        return service.update(firstName, newUsername, newPassword , token);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/user")
    private void delete(@PathVariable Long id, @RequestHeader String token) {
        service.delete(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/user/login")
    public String login(@RequestParam Long id,
                        @RequestParam String password) {
        return service.login(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/user/logout")
    public void logout(@RequestHeader String token) {
        service.logout(token);
    }
}
