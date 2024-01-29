package ir.ac.kntu.kilid.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class City {
    @Id
    private Long id;
    private String name;
    @OneToOne
    @JoinColumn(name = "provice")
    private Province province;


}
