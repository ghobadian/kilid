package ir.ac.kntu.kilid.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Province {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

}
