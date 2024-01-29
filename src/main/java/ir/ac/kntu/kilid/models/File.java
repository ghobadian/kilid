package ir.ac.kntu.kilid.models;

import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class File {
    @Id
    private Long id;
    @OneToOne
    @JoinColumn//(name = "house")
    private House house;
    @OneToOne
    @JoinColumn
    private EstateAgency estateAgency;
}
