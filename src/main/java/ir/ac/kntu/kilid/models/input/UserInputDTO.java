package ir.ac.kntu.kilid.models.input;

import lombok.Getter;

import java.util.Date;

@Getter
public class UserInputDTO {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
}
