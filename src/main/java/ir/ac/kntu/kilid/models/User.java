package ir.ac.kntu.kilid.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    private Long id;
    private String username;
    private String password;
    private String email;
    private Date creationTime;
    private String firstName;
    private String lastName;
}
