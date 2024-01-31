package ir.ac.kntu.kilid.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class History {
    @Id
    @GeneratedValue
    private Long id;
    private String userId;
    private String query;
    private final Date creation = new Date();
}
