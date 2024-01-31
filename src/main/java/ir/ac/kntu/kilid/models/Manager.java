package ir.ac.kntu.kilid.models;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Manager {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "estate_agency_id")
    private EstateAgency estateAgency;
    @OneToOne(cascade = CascadeType.ALL)
    private User user;
}
